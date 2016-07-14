package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingControllerSpec
  extends PlaySpec
    with OneAppPerTest {

  "GET /api/v1/booking/1" should {
    "return Booking JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/booking")).get) must include
      """
        |"id":"1"
        |"user_id":"1"
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }
}
