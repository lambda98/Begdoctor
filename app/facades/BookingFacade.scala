package facades

import javax.inject.{Inject, Singleton}

import models.{Booking, BookingList}
import persists.{BookingPersist, UserPersist}
import services.UuidService

/**
  * Created by anawin on 7/14/2016 AD.
  */
@Singleton
class BookingFacade @Inject()(uuidSercice: UuidService
                              , bookingPersist: BookingPersist
                              , userPersist: UserPersist) {

  def listAllBooking: BookingList = {
    BookingList(bookingPersist.listAllBooking.map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def listBookingById(id: Long): BookingList = {
    BookingList(bookingPersist.listBookingById(id).map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def listBookingByUserId(user_id: Long): BookingList = {
    BookingList(bookingPersist.listBookingByUserId(user_id).map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def insertBooking(name: String
                    , surname: String
                    , email: String
                    , hospital_time_id: Long): Boolean = {

    val userObject = userPersist.findByEmail(email)
    val user_id =
      if (userObject.isEmpty) {
        val created_user_id = uuidSercice.getId
        userPersist.insertUser(
          id = created_user_id
          , name = name
          , surname = surname
          , email = email
        )
        created_user_id
      } else userObject.get.id

    bookingPersist.insertBooking(
      id = uuidSercice.getId
      , user_id = user_id
      , hospital_time_id = hospital_time_id
    )
  }
}
