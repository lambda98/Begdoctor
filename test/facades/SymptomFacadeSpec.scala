package facades

import models.{Symptom, SymptomList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by Siam yimyam on 8/7/2559.
  */
class SymptomFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call selectAll" should {
    "return Symptom " in {
      val facade = app.injector.instanceOf[SymptomFacade]

      val testObject = facade.listAll

      assert(testObject.isInstanceOf[SymptomList])
    }
  }
}
