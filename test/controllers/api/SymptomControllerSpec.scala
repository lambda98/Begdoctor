package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Siam yimyam on 8/7/2559.
  */
class SymptomControllerSpec
  extends PlaySpec
    with OneAppPerTest {

   "GET /api/v1/symptoms/1" should {
     "return Hospital JSON" in {
       contentAsString(route(app, FakeRequest(GET, "/api/v1/symptom/0")).get) must include
       """
         |"name":"Leukemia"
       """.stripMargin
     }
   }
}

