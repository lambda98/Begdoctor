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
        , userId = 1L
        , hospitalTimeId = 1L
        , created = new DateTime()
      )

      val testObject = bookingEntity.toModel()

      assert(testObject.isInstanceOf[Booking])
    }
  }
}
