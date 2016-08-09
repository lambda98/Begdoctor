package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import javax.inject.Inject

import services.UuidService
import com.github.t3hnar.bcrypt._
import entities.StaffEntity
import persists.postgres.StaffPostgres

/**
  * Created by anawin on 8/6/2016 AD.
  */
class StaffPersistSpec @Inject()(uuidService: UuidService)
  extends PlaySpec
    with OneAppPerSuite {

  "Call staff authenticate" should {
    "return staff data" in {
      val correct_username = "choiwoogeun"
      val correct_password = "password"
      val persist = app.injector.instanceOf[StaffPersist]

      val testObject = persist.selectByUserName(correct_username)

      assert(correct_username == testObject.get.username)
    }
  }

  "Call insert staff" should {
    "insert staff data successfully" in {
      val randomId = uuidService.getId
      val correct_id = randomId
      val correct_name = "Im"
      val correct_surname = "Gwang-nam"
      val correct_email = "imgwangnam@mail.com"
      val correct_username = "imgwangnam"
      val correct_password = "picolo"
      val correct_hospitalId = uuidService.getId
      val persist = app.injector.instanceOf[StaffPersist]

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
