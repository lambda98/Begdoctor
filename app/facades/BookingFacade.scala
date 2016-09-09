package facades

import javax.inject.{Inject, Singleton}

import entities.{BookingEntity, UserEntity}
import models.{BookingList, UpComingBooking, UpComingBookingList}
import persists.{BookingPersist, UserPersist}
import services.UuidService

/**
  * Created by anawin on 7/14/2016 AD.
  */
@Singleton
class BookingFacade @Inject()(uuidService: UuidService
                              , bookingPersist: BookingPersist
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
    UpComingBookingList(
      bookingPersist.selectAll.map(
        bookingEntity => toUpComingBooking(bookingEntity)
      )
    )
  }

  def toUpComingBooking(bookingEntity: BookingEntity): UpComingBooking = {
    val user = findUser(bookingEntity.userId)

    UpComingBooking(
      id = bookingEntity.id
      , name = user.name
      , surname = user.surname
      , avatar = user.avatar
      , time = "09:00 - 09:30"
      , symptom = "ไข้หวัด"
      , mobile = "092-251-4661"
      , insuranceLogo = "http://logok.org/wp-content/uploads/2014/09/AIA_Logo.png"
    )
  }

  private def findUser(userId: Long): UserEntity = {
    userPersist.selectById(userId).get
  }

  def create(name: String
             , surname: String
             , email: String
             , mobile: String
             , hospitalTimeId: Long): Boolean = {

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
      , status = "confirmed"
    )
  }
}
