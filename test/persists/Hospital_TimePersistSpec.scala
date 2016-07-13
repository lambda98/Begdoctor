package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by Administrator on 13/7/2559.
  */
class Hospital_TimePersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call findByHospital_Id" should {
    "return Hospital of that id" in {
      val correct_id = 0L
      val correct_hospital_id = 0L
      val correct_start_datetime = "2016-07-01 10:30"
      val correct_finish_datetime = "2016-07-01 11:30"
      val correct_available = "true"

      val persist = app.injector.instanceOf[Hospital_TimePersist]

      val testObject = persist.findById(correct_id)

      assert(correct_id == testObject.get.id)

    }
  }

}
