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

  "GET /api/v1/users/:id" should {
    "return User JSON" in {
      contentAsString(route(
        app
        , FakeRequest(GET, "/api/v1/users/0")).get) must include
      """
        |"id":1
      """.stripMargin
    }
  }

  "GET /api/v1/users/email/:email" should {
    "return User JSON" in {
      contentAsString(route(
        app
        , FakeRequest(GET, "/api/v1/users/email/kimeunji@mail.com")).get) must include
      """
        |"email": "kimeunji@mail.com"
      """.stripMargin
    }
  }

}
