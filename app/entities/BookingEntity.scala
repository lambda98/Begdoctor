package entities

import models.Booking
import org.joda.time.DateTime

/**
  * Created by anawin on 7/14/2016 AD.
  */
case class BookingEntity(id: Long
                         , userId: Long
                         , hospitalTimeId: Long
                         , symptomId: Long
                         , status: String
                         , created: DateTime) {

  def toModel(): Booking = Booking(
    id = id
    , userId = userId
    , hospitalTimeId = hospitalTimeId
    , symptomId = symptomId
    , status = status
    , created = created
  )
}
