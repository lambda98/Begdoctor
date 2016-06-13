package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by champillon on 6/13/16.
  */
class HospitalPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call findById" should {
    "return Hospital of that id" in {
      val correct_id = 0L
      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.findById(correct_id)

      assert(correct_id == testObject.get.id)

    }
  }

}
