package models

import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}


/**
  * Created by anawin on 6/23/2016 AD.
  */
class UserSpec
  extends  PlaySpec
    with  OneAppPerTest {

  "Create new User" should {
    "return User" in {
      val correct_id = 1L
      val correct_name = "patientName"
      val correct_surname = "patientSurname"
      val correct_email = "patient@mail.com"
      val correct_created_at = new DateTime()

      val testObject = new User(
        id = correct_id
        , name = correct_name
        , surname = correct_surname
        , email = correct_email
        , created_at = correct_created_at
      )

      assert(correct_id == testObject.id)
      assert(correct_name == testObject.name)
      assert(correct_surname == testObject.surname)
      assert(correct_email == testObject.email)
      assert(correct_created_at == testObject.created_at)
    }
  }

}
