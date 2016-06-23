package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

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

}
