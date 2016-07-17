package facades

import models.{Booking, BookingList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

import scala.util.Random

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call listAllBooking" should {
    "return Booking " in {
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listAllBooking

      assert(testObject.isInstanceOf[BookingList])
    }
  }

  "Call listBookingById" should {
    "return Booking of that id" in {
      val correct_id = 0L
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listBookingById(correct_id)

      assert(testObject.isInstanceOf[BookingList])
    }
  }

  "Call listBookingByUserId" should {
    "return Booking of that user" in {
      val correct_user_id = 0L
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listBookingByUserId(correct_user_id)

      assert(testObject.isInstanceOf[BookingList])
    }
  }

  "Call insertBooking" should {
    "insert Booking data successfully" in {
      val randomString = Random.alphanumeric.take(5).mkString
      val correct_name = "patientfacadeName"
      val correct_surname = "patientfacadeSurname"
      val correct_email = "patientfacade" + randomString + "@mail.com"
      val correct_hospital_id = 1L
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.insertBooking(
        correct_name
        , correct_surname
        , correct_email
        , correct_hospital_id)

      assert(testObject)
    }
  }
}
