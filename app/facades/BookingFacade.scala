package facades

import javax.inject.{Inject, Singleton}

import models.{Booking, BookingList}
import persists.BookingPersist

/**
  * Created by anawin on 7/14/2016 AD.
  */
@Singleton
class BookingFacade @Inject()(persist: BookingPersist) {

  def listAllBooking: BookingList = {
    BookingList(persist.listAllBooking.map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def listBookingById(id: Long): BookingList = {
    BookingList(persist.listBookingById(id).map(
      bookingEntity => bookingEntity.toModel()
    ))
  }

  def listBookingByUserId(user_id: Long): BookingList = {
    BookingList(persist.listBookingByUserId(user_id).map(
      bookingEntity => bookingEntity.toModel()
    ))
  }
}
