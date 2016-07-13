package entities

import models.Symptom
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by champillon on 7/13/16.
  */
class SymptomEntitySpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call toModel" should {
    "return Symptom of that SymptomEntity" in {
      val symptomEntity = SymptomEntity(
        id = 1L
        , name = "Leukemia"
      )

      val testObject = symptomEntity.toModel()

      assert(testObject.isInstanceOf[Symptom])
    }
  }

}
