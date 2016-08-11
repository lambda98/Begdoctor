package facades

import javax.inject.Inject

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by Siam yimyam on 11/8/2559.
  */
class HospitalRetrievalFacadeSpec @Inject()
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call create" should {
    "insert data successfully" in {
      val correct_lat = "13.7479752"
      val correct_lng = "100.5836296"
      val correct_name = "โรงพยาบาลกรุงเทพ"
      val facade = app.injector.instanceOf[HospitalRetrievalFacade]

      val testObject = facade.create(
        correct_lat
        , correct_lng
        , correct_name)

      assert(testObject)
    }
  }

}
