package persists

import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by Administrator on 13/7/2559.
  */
class Hospital_TimePersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call findByHospital_Id" should {
    "return Hospital of that id" in {
      val correct_id = 1L
      val persist = app.injector.instanceOf[Hospital_TimePersist]

      val testObject = persist.findById(correct_id)

      assert(correct_id == testObject.get.id)
    }
  }
}
