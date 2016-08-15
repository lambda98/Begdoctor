package entities

import models.Staff
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by anawin on 8/7/2016 AD.
  */
class StaffEntitySpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call toModel" should {
    "return Staff of that StaffEntity" in {
      val staffEntity = StaffEntity(
        id = 1L
        , name = "Kim"
        , surname = "Yoona"
        , email = "kimyoona@mail.com"
        , username = "kimyoona"
        , password = "deerkim"
        , hospitalId = 1L
        , created = new DateTime()
      )

      val testObject = staffEntity.toModel()

      assert(testObject.isInstanceOf[Staff])
    }
  }

}
