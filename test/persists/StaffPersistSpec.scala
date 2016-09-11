package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

import services.UuidService

/**
  * Created by anawin on 8/6/2016 AD.
  */
class StaffPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call selectByUsername" should {
    "return staff data" in {
      val persist = app.injector.instanceOf[StaffPersist]
      val correct_username = "choiwoogeun"

      val testObject = persist.selectByUserName(correct_username)

      assert(correct_username == testObject.get.username)
    }
  }

  "Call insert staff" should {
    "insert staff data successfully" in {
      val persist = app.injector.instanceOf[StaffPersist]
      val uuidService = app.injector.instanceOf[UuidService]
      val correct_id = uuidService.getId
      val correct_name = "Im"
      val correct_surname = "Gwang-nam"
      val correct_email = "imgwangnam@mail.com"
      val correct_username = "imgwangnam"
      val correct_password = "picolo"
      val correct_hospitalId = uuidService.getId


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
