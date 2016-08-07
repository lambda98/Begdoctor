package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import javax.inject.Inject

import services.UuidService
import com.github.t3hnar.bcrypt._
import entities.AdminEntity
import persists.postgres.AdminPostgres

/**
  * Created by anawin on 8/6/2016 AD.
  */
class AdminPersistSpec @Inject()(uuidService: UuidService)
  extends PlaySpec
    with OneAppPerSuite {

  "When selectByUserName" should {
    "return data of that username" in {
      val correct_username = "choiwoogeun"
      val persist = app.injector.instanceOf[AdminPostgres]

      val testObject = persist.selectByUserName(correct_username)

      assert(testObject.isInstanceOf[List[AdminEntity]])
    }
  }

  "Call admin check" should {
    "return admin data" in {
      val correct_username = "choiwoogeun"
      val correct_password = "password"
      val persist = app.injector.instanceOf[AdminPersist]

      val testObject = persist.check(correct_username, correct_password)

      assert(testObject)
    }
  }

  "Call insert admin" should {
    "insert admin data successfully" in {
      val randomId = uuidService.getId
      val correct_id = randomId
      val correct_name = "Im"
      val correct_surname = "Gwang-nam"
      val correct_email = "imgwangnam@mail.com"
      val correct_username = "imgwangnam"
      val correct_password = "picolo"
      val correct_hospitalId = uuidService.getId
      val persist = app.injector.instanceOf[AdminPersist]

      val testObject = persist.insert(correct_id
                                      , correct_name
                                      , correct_surname
                                      , correct_email
                                      , correct_username
                                      , correct_password
                                      , correct_hospitalId)

      assert(testObject)
    }
  }

}
