package facades

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by anawin on 6/23/2016 AD.
  */
class UserFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call findById" should {
    "return User of that id" in {
      val correct_id = 0L
      val facade = app.injector.instanceOf[UserFacade]

      val testObject = facade.findById(correct_id)

      assert(correct_id == testObject.id)
    }
  }

  "Call findByEmail" should {
    "return User of that email" in {
      val correct_email = "patient@mail.com"
      val facade = app.injector.instanceOf[UserFacade]

      val testObject = facade.findByEmail(correct_email)

      assert(correct_email == testObject.email)
    }
  }

}
