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
}