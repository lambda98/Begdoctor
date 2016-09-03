package persists

import entities.HospitalTimeEntity
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by Administrator on 13/7/2559.
  */
class HospitalTimePersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call findByHospital_Id" should {
    "return Hospital of that id" in {
      val correct_id = 1L
      val persist = app.injector.instanceOf[HospitalTimePersist]

      val testObject = persist.selectById(correct_id)

      assert(testObject.isInstanceOf[Option[HospitalTimeEntity]])
    }
  }
}
