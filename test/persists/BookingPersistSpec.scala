package persists

import entities.BookingEntity
import models.Booking
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import persists.postgres.BookingPostgres

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "When listAllBooking" should {
    "return Booking" in {
      val persist = app.injector.instanceOf[BookingPostgres]

      val testObject = persist.listAllBooking()

      assert(testObject.isInstanceOf[List[BookingEntity]])
    }
  }

  "When listBookingById" should {
    "return Booking of that id" in {
      val correct_id = 0L
      val persist = app.injector.instanceOf[BookingPostgres]

      val testObject = persist.listBookingById(correct_id)

      assert(testObject.isInstanceOf[List[BookingEntity]])
    }
  }
}