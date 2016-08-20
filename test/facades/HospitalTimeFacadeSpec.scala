package facades

import models.{HospitalTime, HospitalTimeList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class HospitalTimeFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call findById" should {
    "return HospitalTime of that id" in {
      val correct_id = 1L
      val facade = app.injector.instanceOf[HospitalTimeFacade]

      val testObject = facade.findById(correct_id)

      assert(testObject.isInstanceOf[HospitalTimeList])
    }
  }

}
