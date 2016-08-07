package entities

import models.Admin
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by anawin on 8/7/2016 AD.
  */
class AdminEntitySpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call toModel" should {
    "return Admin of that AdminEntity" in {
      val adminEntity = AdminEntity(
        id = 1L
        , name = "Kim"
        , surname = "Yoona"
        , email = "kimyoona@mail.com"
        , username = "kimyoona"
        , password = "deerkim"
        , hospitalId = 1L
        , created = new DateTime()
      )

      val testObject = adminEntity.toModel()

      assert(testObject.isInstanceOf[Admin])
    }
  }

}
