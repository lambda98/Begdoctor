package facades

import models.{Booking, BookingList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

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

  "Call findById" should {
    "return User of that id" in {
      val correct_id = 0L
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listBookingById(correct_id)

      assert(testObject.isInstanceOf[BookingList])
    }
  }
}
