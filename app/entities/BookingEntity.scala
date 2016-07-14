package entities

import models.Booking

/**
  * Created by anawin on 7/14/2016 AD.
  */
case class BookingEntity (id: Long
                          , user_id: Long
                          , hospital_time_id: Long) {

  def toModel(): Booking = Booking(
    id = id
    , user_id = user_id
    , hospital_time_id = hospital_time_id
  )
}
