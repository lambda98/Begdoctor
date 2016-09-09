package facades

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder
import services.UuidService
import com.github.t3hnar.bcrypt._

/**
  * Created by anawin on 8/6/2016 AD.
  */
class StaffFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call authenticate" should {
    "return staff info" in {
      val correct_username = "choiwoogeun"
      val correct_password = "password"
      val facade = app.injector.instanceOf[StaffFacade]
      val testObject = facade.authenticate(correct_username, correct_password)

      assert(testObject)
    }
  }

  "Call create staff" should {
    "insert staff data successfully" in {
      val uuidService = app.injector.instanceOf[UuidService]
      val facade = app.injector.instanceOf[StaffFacade]
      val randomString = uuidService.getId
      val correct_name = "Song"
      val correct_surname = "Sang-hyun"
      val correct_email = "songsanghyn@mail.com"
      val correct_username = "songsanghyn"
      val correct_password = "songsang".bcrypt
      val correct_hospitalId = 1L


      val testObject = facade.create(
        correct_name
        , correct_surname
        , correct_email
        , correct_username
        , correct_password
        , correct_hospitalId)

      assert(testObject)
    }
  }

}
