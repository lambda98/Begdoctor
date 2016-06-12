package facades

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by champillon on 6/12/16.
  */
class HospitalFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call findById" should {
    "return Hospital of that id" in {
      val correct_id = 1L
      val facade = app.injector.instanceOf[HospitalFacade]

      val testObject = facade.findById(1L)

      assert(correct_id == testObject.id)
    }
  }

}
