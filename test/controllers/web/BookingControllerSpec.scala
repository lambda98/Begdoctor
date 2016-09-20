package controllers.web

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by champillon on 9/20/16.
  */
class BookingControllerSpec
  extends PlaySpec
    with OneAppPerTest {

  "GET /web/v1/bookings/upcoming" should {
    "return UpComing Booking JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/web/v1/bookings/upcoming")).get) must include
      """
        |"id":"1",
        |"user_id":"1",
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }

}
