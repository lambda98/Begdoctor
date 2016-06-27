package facades

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

import scala.util.Random

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

  "Call insertUser" should {
    "insert data successfully" in {
      val randomString = Random.alphanumeric.take(5).mkString
      val correct_name = "patientfacadeName"
      val correct_surname = "patientfacadeSurname"
      val correct_email = "patientfacade"+randomString+"@mail.com"
      val facade = app.injector.instanceOf[UserFacade]

      val testObject = facade.insertUser(correct_name, correct_surname, correct_email)

      assert(testObject == true)
    }
  }

}
