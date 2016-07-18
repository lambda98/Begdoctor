package facades

import models.{Booking, BookingList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder
import javax.inject.Inject
import services.UuidService

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingFacadeSpec @Inject()(uuidService: UuidService)
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call listAll" should {
    "return Booking " in {
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.listAll

      assert(testObject.isInstanceOf[BookingList])
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

  "Call create" should {
    "insert Booking data successfully" in {
      val randomString = uuidService.getId
      val correct_name = "Pyo"
      val correct_surname = "Ji-soo"
      val correct_email = "PyoJisoo" + randomString + "@mail.com"
      val correct_hospitalTimeId = 1L
      val facade = app.injector.instanceOf[BookingFacade]

      val testObject = facade.create(
        correct_name
        , correct_surname
        , correct_email
        , correct_hospitalTimeId)

      assert(testObject)
    }
  }
}
