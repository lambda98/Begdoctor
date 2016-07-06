package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by champillon on 6/12/16.
  */
class HospitalControllerSpec
  extends PlaySpec
    with OneAppPerTest {

  "GET /api/v1/hospitals/1" should {
    "return Hospital JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/hospitals/0")).get) must include
      """
        |"id":0,
        |"name":"test hospital name"
      """.stripMargin
    }
  }

}
