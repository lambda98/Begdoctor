package facades

import javax.inject.{Inject, Singleton}

import definitions.BookingStatus
import definitions.BookingStatus.BookingStatus
import entities.{BookingEntity, HospitalTimeEntity, SymptomEntity, UserEntity}
import models._
import org.joda.time.DateTime
import persists.{BookingPersist, HospitalTimePersist, SymptomPersist, UserPersist}
import services.UuidService
import utilities.DateConverter

/**
  * Created by anawin on 7/14/2016 AD.
  */
@Singleton
class BookingFacade @Inject()(uuidService: UuidService
                              , bookingPersist: BookingPersist
                              , hospitalTimePersist: HospitalTimePersist
                              , symptomPersist: SymptomPersist
                              , userPersist: UserPersist) {

  def listAll: BookingList = {
    BookingList(bookingPersist.selectAll.map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def listById(id: Long): BookingList = {
    BookingList(bookingPersist.selectById(id).map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def listByUserId(userId: Long): BookingList = {
    BookingList(bookingPersist.selectByUserId(userId).map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def listUpComping(): UpComingBookingList = {
    val upComingList = UpComingBookingList(
      bookingPersist.selectAll.map(
        bookingEntity => toUpComingBooking(bookingEntity)
      )
    )

    UpComingBookingList(upComingList.bookings.slice(0, 4))
  }

  def listWebBooking(): ShowWebBookingList = {
    ShowWebBookingList(
      bookingPersist.selectAll.map(
        bookingEntity => toShowWebBooking(bookingEntity)
      )
    )
  }

  def create(name: String
             , surname: String
             , email: String
             , mobile: String
             , hospitalTimeId: Long
             , symptomId: Long): Boolean = {

    val booker = userPersist.selectByEmail(email)
    val userId = booker match {
      case None | null => {
        val createdBookerId = uuidService.getId
        userPersist.insert(
          id = createdBookerId
          , name = name
          , surname = surname
          , email = email
          , mobile = mobile
        )
        createdBookerId
      }
      case _ => booker.get.id
    }

    bookingPersist.insert(
      id = uuidService.getId
      , userId = userId
      , hospitalTimeId = hospitalTimeId
      , symptomId = symptomId
      , status = BookingStatus.confirmed
    )
  }

  private def toShowWebBooking(bookingEntity: BookingEntity): ShowWebBooking = {
    val user = findUser(bookingEntity.userId)
    val hospitalTime = findHospitalTime(bookingEntity.hospitalTimeId)
    val symptom = findSymptom(bookingEntity.symptomId)

    ShowWebBooking(
      id = bookingEntity.id
      , name = user.name
      , surname = user.surname
      , time = parseTime(hospitalTime)
      , symptom = symptom.name
      , mobile = user.mobile
      , status = getStatus(hospitalTime).toString
    )
  }

  private def getStatus(hospitalTimeEntity: HospitalTimeEntity): BookingStatus = {
    val now = new DateTime

    now.getMillis > hospitalTimeEntity.finishDateTime.getMillis match {
      case true => BookingStatus.delay
      case _ => BookingStatus.upcoming
    }

  }

  private def toUpComingBooking(bookingEntity: BookingEntity): UpComingBooking = {
    val user = findUser(bookingEntity.userId)
    val hospitalTime = findHospitalTime(bookingEntity.hospitalTimeId)
    val symptom = findSymptom(bookingEntity.symptomId)

    UpComingBooking(
      id = bookingEntity.id
      , name = user.name
      , surname = user.surname
      , avatar = user.avatar
      , time = parseTime(hospitalTime)
      , symptom = symptom.name
      , mobile = user.mobile
      , insuranceLogo = "http://logok.org/wp-content/uploads/2014/09/AIA_Logo.png"
    )
  }

  private def parseTime(hospitalTime: HospitalTimeEntity): String = {
    val start = DateConverter.timeToString(hospitalTime.startDateTime)
    val finish = DateConverter.timeToString(hospitalTime.finishDateTime)

    start + " - " + finish
  }

  private def findSymptom(symptomId: Long): SymptomEntity = {
    symptomPersist.selectById(symptomId).get
  }

  private def findUser(userId: Long): UserEntity = {
    userPersist.selectById(userId).get
  }

  private def findHospitalTime(hospitalTimeId: Long): HospitalTimeEntity = {
    hospitalTimePersist.selectById(hospitalTimeId).get
  }
}
