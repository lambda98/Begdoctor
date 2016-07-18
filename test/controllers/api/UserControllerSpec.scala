package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by anawin on 6/23/2016 AD.
  */
class UserControllerSpec
  extends PlaySpec
    with OneAppPerTest {

  "GET /api/v1/users/1" should {
    "return User JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/users/0")).get) must include
      """
        |"id":1
      """.stripMargin
    }
  }

  "POST /api/v1/useremail/" should {
    "return User JSON" in {
      contentAsString(route(app, FakeRequest(POST, "/api/v1/useremail/")).get) must include
      """
        |"email": "yoona@mail.com"
      """.stripMargin
    }
  }

  "POST /api/v1/users" should {
    "return 200: with Save Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/users")
          .withJsonBody(Json.parse(
            """
              |{
              |   "name": "Yuri",
              |   "surname": "Yuri",
              |   "email": "yuri@mail.com"
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
