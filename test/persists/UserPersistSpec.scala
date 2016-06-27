package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

import scala.util.Random

/**
  * Created by anawin on 6/23/2016 AD.
  */
class UserPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call findById" should {
    "return User of that id" in {
      val correct_id = 0L
      val persist = app.injector.instanceOf[UserPersist]

      val testObject = persist.findById(correct_id)

      assert(correct_id == testObject.get.id)
    }
  }

  "Call findByEmail" should {
    "return User of that email" in {
      val correct_email = "patient@mail.com"
      val persist = app.injector.instanceOf[UserPersist]

      val testObject = persist.findByEmail(correct_email)

      assert(correct_email == testObject.get.email)
    }
  }

  "Call insertUser" should {
    "insert data successfully" in {
      val randomString = Random.alphanumeric.take(5).mkString
      val correct_name = "patientpersistName"
      val correct_surname = "patientpersistSurname"
      val correct_email = "patientpersist"+randomString+"@mail.com"
      val persist = app.injector.instanceOf[UserPersist]

      val testObject = persist.insertUser(correct_name, correct_surname, correct_email)

      assert(testObject == true)
    }
  }

}
