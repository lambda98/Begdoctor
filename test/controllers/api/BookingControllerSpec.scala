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

  "GET /api/v1/booking/" should {
    "return Booking JSON" in {
      contentAsString(route(app, FakeRequest(GET, "/api/v1/booking")).get) must include
      """
        |"id":"1"
        |"user_id":"1"
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }

  "POST /api/v1/bookingbyid/" should {
    "return Booking JSON" in {
      contentAsString(route(app, FakeRequest(POST, "/api/v1/bookingbyid/")).get) must include
      """
        |"id":"1"
        |"user_id":"1"
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }

  "POST /api/v1/bookingbyuserid/" should {
    "return Booking JSON" in {
      contentAsString(route(app, FakeRequest(POST, "/api/v1/bookingbyuserid/")).get) must include
      """
        |"id":"1"
        |"user_id":"1"
        |"hospital_time_id":"1"
      """.stripMargin
    }
  }

  "POST /api/v1/savebooking" should {
    "return 200: with Save Successful" in {
      val testObject = route(
        app
        , FakeRequest(POST, "/api/v1/savebooking")
          .withJsonBody(Json.parse(
            """
              |{
              |   "name": "Yuri",
              |   "surname": "Yuri",
              |   "email": "yuri@mail.com",
              |   "hospital_time_id": "1"
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
