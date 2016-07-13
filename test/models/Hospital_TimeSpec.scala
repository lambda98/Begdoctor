package models

import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class Hospital_TimeSpec
  extends PlaySpec
    with OneAppPerTest {

  "Create new Hospital_Time" should {
    "return Hospital_Time" in {
      val correct_id = 1L
      val correct_hospital_id = 1L
      val correct_start_datetime = new DateTime(2016,7,1,10,30,0,0)
      val correct_finish_datetime = new DateTime(2016,7,1,11,30,0,0)
      val correct_available = true



      val testObject = new Hospital_Time(
        id = correct_id
        , hospital_id = correct_hospital_id
        , start_datetime = correct_start_datetime
        , finish_datetime = correct_finish_datetime
        , available = correct_available
      )

      assert(correct_id == testObject.id)
      assert(correct_hospital_id == testObject.hospital_id)
      assert(correct_start_datetime == testObject.start_datetime)
      assert(correct_finish_datetime == testObject.finish_datetime)
      assert(correct_available == testObject.available)
    }
  }

}
