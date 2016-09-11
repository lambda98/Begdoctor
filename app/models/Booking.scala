package models

import org.joda.time.DateTime
import utilities.Json

/**
  * Created by anawin on 7/14/2016 AD.
  */
case class Booking(id: Long
                   , userId: Long
                   , hospitalTimeId: Long
                   , symptomId: Long
                   , status: String
                   , created: DateTime)
  extends Json

case class BookingList(bookings: List[Booking])
  extends Json

case class UpComingBooking(id: Long
                           , name: String
                           , surname: String
                           , avatar: String
                           , time: String
                           , symptom: String
                           , mobile: String
                           , insuranceLogo: String)

case class UpComingBookingList(bookings: List[UpComingBooking])
  extends Json