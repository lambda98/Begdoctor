package models

import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class Hospital_TimeSpec
  extends PlaySpec
    with OneAppPerTest {

  "Create new HospitalTime" should {
    "return HospitalTime" in {
      val correct_id = 1L
      val correct_hospital_id = 1L
      val correct_start_datetime = new DateTime(2016,7,1,10,30,0,0)
      val correct_finish_datetime = new DateTime(2016,7,1,11,30,0,0)
      val correct_available = true

      val testObject = new HospitalTime(
        id = correct_id
        , hospitalId = correct_hospital_id
        , startDateTime = correct_start_datetime
        , finishDateTime = correct_finish_datetime
        , available = correct_available
      )

      assert(correct_id == testObject.id)
      assert(correct_hospital_id == testObject.hospitalId)
      assert(correct_start_datetime == testObject.startDateTime)
      assert(correct_finish_datetime == testObject.finishDateTime)
      assert(correct_available == testObject.available)
    }
  }
}
