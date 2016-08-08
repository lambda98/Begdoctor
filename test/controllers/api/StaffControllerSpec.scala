package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by anawin on 8/6/2016 AD.
  */
class StaffControllerSpec
  extends PlaySpec
    with OneAppPerTest {

  "POST /api/v1/staffs/username/:username" should {
    "return Staff JSON" in {
      contentAsString(route(app, FakeRequest(POST, "/api/v1/staffs/username/choiwoogeun")).get) must include
      """
        |"name":"Choi"
        |"surname":"Woo-geun"
        |"hospital_id":"1"
      """.stripMargin
    }
  }

  "POST /api/v1/staffs/check" should {
    "return Staff: with Login Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/staffs/check")
          .withJsonBody(Json.parse(
            """
              |{
              |   "username": "choiwoogeun",
              |   "password": "password"
              |}
            """.stripMargin
          ))
      ).get
      status(testObject) mustBe OK
      contentType(testObject) mustBe Some("application/json")
      val contentString = contentAsString(testObject)
      contentString must include("choiwoogeun")
    }
  }

  "POST /api/v1/staffs" should {
    "return 200: with Save Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/staffs")
          .withJsonBody(Json.parse(
            """
              |{
              |   "name": "Yoon",
              |   "surname": "Gil Joon",
              |   "email": "yoongiljoon@mail.com",
              |   "username": "yoongiljoon",
              |   "password": "tiger",
              |   "hospitalId": 1
              |}
            """.stripMargin
          ))
      ).get

      status(testObject) mustBe OK
      contentType(testObject) mustBe Some("application/json")
      val contentString = contentAsString(testObject)
      contentString must include("200")
    }
  }

}
