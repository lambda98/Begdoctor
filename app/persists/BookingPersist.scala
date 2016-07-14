package persists

import entities.BookingEntity

/**
  * Created by anawin on 7/14/2016 AD.
  */
trait BookingPersist {

  def listAllBooking()  : List[BookingEntity]
  def listBookingById(id: Long) : List[BookingEntity]
  def listBookingByUserId(user_id: Long) : List[BookingEntity]
}
