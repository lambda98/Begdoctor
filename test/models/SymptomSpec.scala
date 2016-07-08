package models

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by SasimapornSmt on 7/7/2559.
  */
class SymptomSpec
  extends PlaySpec
    with OneAppPerTest {

  "Create new Symptom" should {
    "return Symptom" in {
      val correct_id = 1L
      val correct_name = "Leukemia"


      val testObject = new Symptom(
        id = correct_id
        , name = correct_name
      )

      assert(correct_id == testObject.id)
      assert(correct_name == testObject.name)
    }
  }
}
