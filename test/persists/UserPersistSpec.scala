package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import javax.inject.Inject
import services.UuidService

/**
  * Created by anawin on 6/23/2016 AD.
  */
class UserPersistSpec @Inject()(uuidService: UuidService)
  extends PlaySpec
    with OneAppPerSuite {

  "Call selectById" should {
    "return User of that id" in {
      val correct_id = 0L
      val persist = app.injector.instanceOf[UserPersist]

      val testObject = persist.selectById(correct_id)

      assert(correct_id == testObject.get.id)
    }
  }

  "Call selectByEmail" should {
    "return User of that email" in {
      val correct_email = "kimeunji@mail.com"
      val persist = app.injector.instanceOf[UserPersist]

      val testObject = persist.selectByEmail(correct_email)

      assert(correct_email == testObject.get.email)
    }
  }

  "Call insert" should {
    "insert user data successfully" in {
      val persist = app.injector.instanceOf[UserPersist]
      val randomString = uuidService.getId
      val randomId = uuidService.getId
      val correct_id = randomId
      val correct_name = "Jang"
      val correct_surname = "Hee-eun"
      val correct_email = "jangheeeun" + randomString + "@mail.com"
      val correct_mobile = "0921234567"


      val testObject = persist.insert(correct_id, correct_name, correct_surname, correct_email, correct_mobile)

      assert(testObject)
    }
  }

}
