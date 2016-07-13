package facades

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class Hospital_TimeFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call findById" should {
    "return Hospital_Time of that id" in {
      val correct_id = 0L
      val facade = app.injector.instanceOf[Hospital_TimeFacade]

      val testObject = facade.findById(correct_id)

      assert(correct_id == testObject.id)
    }
  }

}
