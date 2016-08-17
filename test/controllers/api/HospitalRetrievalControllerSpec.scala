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

  "GET /api/v1/hospitalretrievals/:id" should {
    "return HospitalRetrieval JSON" in {
      contentAsString(route(
        app
        , FakeRequest(GET, "/api/v1/hospitalretrievals/0")).get) must include
      """
        |"id":1
      """.stripMargin
    }
  }

  "POST /api/v1/hospitalRetrievals" should {
    "return 200: with Save Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/hospitalRetrievals")
          .withJsonBody(Json.parse(
            """
              |{
              |   "latitude": "13.74797555",
              |   "longitude": "100.583629336",
              |   "name": "โรงพยาบาลรามา"
              |}
            """.stripMargin
          ))
      ).get
      status(testObject) mustBe OK
      val contentString = contentAsString(testObject)
      contentType(testObject) mustBe Some("application/json")
      contentString must include("200")
    }
  }

  "POST /api/v1/hospitalRetrievals" should {
    "return 200: with Update Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/hospitalRetrievals")
          .withJsonBody(Json.parse(
            """
              |{
              |   "latitude": "13.735052",
              |   "longitude": "100.576692",
              |   "name": "โรงพยาบาลสมิติเวช สุขุมวิท"
              |}
            """.stripMargin
          ))
      ).get
      status(testObject) mustBe OK
      val contentString = contentAsString(testObject)
      contentType(testObject) mustBe Some("application/json")
      contentString must include("200")
    }
  }

  "GET /api/v1/hospitalRetrievals/name/:name" should {
    "return HospitalRetrieval JSON" in {
      contentAsString(route(
        app
        , FakeRequest(GET, "/api/v1/hospitalRetrievals/name/โรงพยาบาลสมิติเวช ธนบุรี (Samitivej Thonburi)")).get) must include
      """
        |"name": "โรงพยาบาลสมิติเวช ธนบุรี (Samitivej Thonburi)"
      """.stripMargin
    }
  }

}
