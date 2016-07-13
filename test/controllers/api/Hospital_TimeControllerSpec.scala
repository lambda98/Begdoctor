package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by Administrator on 13/7/2559.
  */
class Hospital_TimeControllerSpec
  extends PlaySpec
    with OneAppPerTest{

  "GET /api/v1/hospital_time/1" should {
    "return Hospital_Time JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/hospital_time/0")).get) must include
      """
      |"id":0,
      |"hospital_id":0
      |"start_datetime":"2016-07-01 10:30"
      |"finish_datetime":"2016-07-01 11:30"
      |"available":"true"
    """.stripMargin
    }
  }
}
