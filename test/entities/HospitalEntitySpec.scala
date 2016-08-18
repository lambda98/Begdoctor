package entities

import models.Hospital
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by anawin on 8/16/2016 AD.
  */
class HospitalEntitySpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call toModel" should {
    "return Hospital of that HospitalEntity" in {
      val hospitalEntity = HospitalEntity(
        id = 1L
        , name = "Leukemia"
        , url = "-"
        , doctorName = "-"
        , latitude = 1F
        , longitude = 1F
        , availableTime = "-"
      )

      val testObject = hospitalEntity.toModel()

      assert(testObject.isInstanceOf[Hospital])
    }
  }

}
