package persists

import entities.BookingEntity
import models.Booking
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import persists.postgres.BookingPostgres
import javax.inject.Inject
import services.UuidService

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "When selectAll" should {
    "return Booking" in {
      val persist = app.injector.instanceOf[BookingPostgres]

      val testObject = persist.selectAll()

      assert(testObject.isInstanceOf[List[BookingEntity]])
    }
  }

  "When selectById" should {
    "return Booking of that id" in {
      val persist = app.injector.instanceOf[BookingPostgres]
      val correct_id = 0L

      val testObject = persist.selectById(correct_id)

      assert(testObject.isInstanceOf[List[BookingEntity]])
    }
  }

  "When selectByUserId" should {
    "return Booking of that user" in {
      val persist = app.injector.instanceOf[BookingPostgres]
      val correct_UserId = 0L

      val testObject = persist.selectByUserId(correct_UserId)

      assert(testObject.isInstanceOf[List[BookingEntity]])
    }
  }

  "Call insert" should {
    "insert Booking data successfully" in {
      val persist = app.injector.instanceOf[BookingPersist]
      val uuidService = app.injector.instanceOf[UuidService]
      val correct_id = uuidService.getId
      val correct_userId = 0L
      val correct_hospitalTimeId = 1L
      val correct_symptomId = 1L
      val correct_status = "confirmed"

      val testObject = persist.insert(correct_id, correct_userId, correct_hospitalTimeId, correct_symptomId, correct_status)

      assert(testObject)
    }
  }
}