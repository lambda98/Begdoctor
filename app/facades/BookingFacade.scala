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
}
