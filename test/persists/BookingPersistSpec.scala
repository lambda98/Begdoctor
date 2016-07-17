package persists

import entities.BookingEntity
import models.Booking
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import persists.postgres.BookingPostgres

import scala.util.Random

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

  "When listBookingById" should {
    "return Booking of that user" in {
      val correct_user_id = 0L
      val persist = app.injector.instanceOf[BookingPostgres]

      val testObject = persist.listBookingByUserId(correct_user_id)

      assert(testObject.isInstanceOf[List[BookingEntity]])
    }
  }

  "Call insertBooking" should {
    "insert Booking data successfully" in {
      val randomId = Random.nextLong()
      val correct_id = randomId
      val correct_user_id = 1L
      val correct_hospital_time_id = 0L
      val persist = app.injector.instanceOf[BookingPersist]

      val testObject = persist.insertBooking(correct_id, correct_user_id, correct_hospital_time_id)

      assert(testObject)
    }
  }
}