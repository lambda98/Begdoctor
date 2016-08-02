package controllers.api

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingControllerSpec
  extends PlaySpec
    with OneAppPerTest {

  "GET /api/v1/bookings" should {
    "return Booking JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/bookings")).get) must include
      """
        |"id":"1"
        |"user_id":"1"
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }

  "GET /api/v1/bookings/:id" should {
    "return Booking JSON" in {
      contentAsString(route(app, FakeRequest(POST, "/api/v1/bookings/1")).get) must include
      """
        |"id":"1"
        |"user_id":"1"
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }

  "POST /api/v1/bookings/userid/:userId" should {
    "return Booking JSON" in {
      contentAsString(route(app, FakeRequest(POST, "/api/v1/bookings/userid/1")).get) must include
      """
        |"id":"1"
        |"user_id":"1"
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }

  "POST /api/v1/bookings" should {
    "return 200: with Save Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/bookings")
          .withJsonBody(Json.parse(
            """
              |{
              |   "name": "Yuri",
              |   "surname": "Kwan",
              |   "email": "yurikwan@mail.com",
              |   "hospitalTimeId": "1"
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
