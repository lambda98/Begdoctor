package facades

import javax.inject.{Inject, Singleton}

import models.{Booking, BookingList}
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

  def create(name: String
             , surname: String
             , email: String
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
