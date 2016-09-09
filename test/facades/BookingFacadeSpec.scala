package facades

import models.{Booking, BookingList, UpComingBookingList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder
import javax.inject.Inject

import services.UuidService

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call listAll" should {
    "return Booking " in {
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listAll

      assert(testObject.isInstanceOf[BookingList])
      println("champ2: " + testObject.bookings.size)
    }
  }

  "Call listById" should {
    "return Booking of that id" in {
      val correct_id = 0L
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listById(correct_id)

      assert(testObject.isInstanceOf[BookingList])
    }
  }

  "Call listByUserId" should {
    "return Booking of that user" in {
      val correct_userId = 0L
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listByUserId(correct_userId)

      assert(testObject.isInstanceOf[BookingList])
    }
  }

  "Call listUpComing" should {
    "return UpComingBooking" in {
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listUpComping()

      assert(testObject.isInstanceOf[UpComingBookingList])
    }
  }

  "Call create" should {
    "insert Booking data successfully" in {
      val uuidService = app.injector.instanceOf[UuidService]
      val facade = app.injector.instanceOf[BookingFacade]
      val randomString = uuidService.getId
      val correct_name = "Pyo"
      val correct_surname = "Ji-soo"
      val correct_email = "PyoJisoo" + randomString + "@mail.com"
      val correct_hospitalTimeId = 1L
      val correct_mobile = "0891234567"


      val testObject = facade.create(
        correct_name
        , correct_surname
        , correct_email
        , correct_mobile
        , correct_hospitalTimeId)

      assert(testObject)
    }
  }
}
