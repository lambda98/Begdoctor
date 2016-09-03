package entities

import models.HospitalTime
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by anawin on 8/20/2016 AD.
  */
class HospitalTimeEntitySpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call toModel" should {
    "return HospitalTime of that HospitalTimeEntity" in {
      val hospitaTimelEntity = HospitalTimeEntity(
        id = 1L
        , hospitalId = 1L
        , startDateTime = new DateTime()
        , finishDateTime = new DateTime()
        , available = true
      )

      val testObject = hospitaTimelEntity.toModel()

      assert(testObject.isInstanceOf[HospitalTime])
    }
  }

}
