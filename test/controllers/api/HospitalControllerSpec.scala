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
        |"name":"Hae song"
        |"url":"https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png"
        |"name":Kang Moyeon
        |"latitude":"13.7854529"
        |"longitude":"100.5736408"
        |"available_time":"10:00 - 17:00"
      """.stripMargin
    }
  }

  "GET /api/v1/hospitals" should {
    "return Hospital JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/hospitals")).get) must include
      """
       "id":0,
        |"name":"Hae song"
        |"url":"https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png"
        |"name":Kang Moyeon
        |"latitude":"13.7854529"
        |"longitude":"100.5736408"
        |"available_time":"10:00 - 17:00"
      """.stripMargin
    }
  }

}
