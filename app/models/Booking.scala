package models

import org.joda.time.DateTime
import utilities.Json

/**
  * Created by anawin on 7/14/2016 AD.
  */
case class Booking(id: Long
                   , userId: Long
                   , hospitalTimeId: Long
                   , status: String
                   , created: DateTime)
  extends Json

case class BookingList(bookings: List[Booking])
  extends Json
