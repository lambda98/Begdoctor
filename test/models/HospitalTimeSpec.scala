package models

import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class HospitalTimeSpec
  extends PlaySpec
    with OneAppPerTest {

  "Create new HospitalTime" should {
    "return HospitalTime" in {
      val correct_id = 1L
      val correct_hospitalId = 1L
      val correct_startDatetime = new DateTime(2016,7,1,10,30,0,0)
      val correct_finishDatetime = new DateTime(2016,7,1,11,30,0,0)
      val correct_available = true

      val testObject = new HospitalTime(
        id = correct_id
        , hospitalId = correct_hospitalId
        , startDateTime = correct_startDatetime
        , finishDateTime = correct_finishDatetime
        , available = correct_available
      )

      assert(correct_id == testObject.id)
      assert(correct_hospitalId == testObject.hospitalId)
      assert(correct_startDatetime == testObject.startDateTime)
      assert(correct_finishDatetime == testObject.finishDateTime)
      assert(correct_available == testObject.available)
    }
  }
}
