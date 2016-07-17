package entities

import models.Booking
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingEntitySpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call toModel" should {
    "return booking of that BookingEntity" in {
      val bookingEntity = BookingEntity(
        id = 1L
        , user_id = 1L
        , hospital_time_id = 1L
        , created_at = new DateTime()
      )

      val testObject = bookingEntity.toModel()

      assert(testObject.isInstanceOf[Booking])
    }
  }
}
