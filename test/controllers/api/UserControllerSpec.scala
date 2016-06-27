package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
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

  "POST /api/v1/user_email/" should {
    "return User JSON" in {
      contentAsString(route(app, FakeRequest(POST, "/api/v1/user_email/")).get) must include
      """
        |"email": "patient@mail.com"
      """.stripMargin
    }
  }

}
