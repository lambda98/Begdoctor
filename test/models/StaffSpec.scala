package models

import org.joda.time.DateTime
import com.github.t3hnar.bcrypt._
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by anawin on 8/6/2016 AD.
  */
class StaffSpec
  extends  PlaySpec
    with  OneAppPerTest {

  "Create new Staff" should {
    "return staff" in {
      val correct_id = 1L
      val correct_name = "Lee"
      val correct_surname = "Chi-hoon"
      val correct_email = "leechihoon@mail.com"
      val correct_username = "leechihoon"
      val correct_password = "goku".bcrypt
      val correct_hospitalId = 1L
      val correct_created = new DateTime()

      val testObject = new Staff(
        id = correct_id
        , name = correct_name
        , surname = correct_surname
        , email = correct_email
        , username = correct_username
        , password = correct_password
        , hospitalId = correct_hospitalId
        , created = correct_created
      )

      assert(correct_id == testObject.id)
      assert(correct_name == testObject.name)
      assert(correct_surname == testObject.surname)
      assert(correct_email == testObject.email)
      assert(correct_username == testObject.username)
      assert(correct_password == testObject.password)
      assert(correct_hospitalId == testObject.hospitalId)
      assert(correct_created == testObject.created)
    }
  }

}
