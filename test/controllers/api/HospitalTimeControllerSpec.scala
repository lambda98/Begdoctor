package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Administrator on 13/7/2559.
  */
class HospitalTimeControllerSpec
  extends PlaySpec
    with OneAppPerTest{

  "GET /api/v1/hospitals/1/time" should {
    "return HospitalTime JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/hospitals/1/time")).get) must include
      """
        |"id":1,
        |"hospitalId":0
        |"startDatetime":"2016-07-01 10:30"
        |"finishDatetime":"2016-07-01 11:30"
        |"available":"true"
      """.stripMargin
    }
  }
}
