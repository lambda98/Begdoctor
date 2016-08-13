package models

import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingSpec
  extends PlaySpec
    with OneAppPerTest {

  "Create new Booking" should {
    "return Booking" in {
      val correct_id = 0L
      val correct_userId = 0L
      val correct_hospitalTimeId = 0L
      val correct_status = "confirmed"
      val correct_created = new DateTime()

      val testObject = new Booking(
        id = correct_id
        , userId = correct_userId
        , hospitalTimeId = correct_hospitalTimeId
        , status = correct_status
        , created = correct_created
      )

      assert(correct_id == testObject.id)
      assert(correct_userId == testObject.userId)
      assert(correct_hospitalTimeId == testObject.hospitalTimeId)
      assert(correct_status == testObject.status)
      assert(correct_created == testObject.created)
    }
  }
}

