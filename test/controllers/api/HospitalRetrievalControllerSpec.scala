package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.libs.json.Json
import play.api.test.Helpers._

/**
  * Created by Siam yimyam on 10/8/2559.
  */
class HospitalRetrievalControllerSpec
  extends PlaySpec
    with OneAppPerTest {

  "POST /api/v1/hospitalretrievals" should {
    "return 200: with Save Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/hospitalretrievals")
          .withJsonBody(Json.parse(
            """
              |{
              |   "lat": "13.7479752"
              |   "lng": "100.5836296"
              |   "name": "โรงพยาบาลกรุงเทพ"
              |}
            """.stripMargin
          ))
      ).get

      status(testObject) mustBe OK
      contentType(testObject) mustBe Some("application/json")
      val contentString = contentString(testObject)
      contentString must include("200")
    }
  }

}