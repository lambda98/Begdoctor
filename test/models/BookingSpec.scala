package models

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
      val correct_user_id = 0L
      val correct_hospital_time_id = 0L

      val testObject = new Booking(
        id = correct_id
        , user_id = correct_user_id
        , hospital_time_id = correct_hospital_time_id
      )

      assert(correct_id == testObject.id)
      assert(correct_user_id == testObject.user_id)
      assert(correct_hospital_time_id == testObject.hospital_time_id)
    }
  }
}

