package models

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by champillon on 6/12/16.
  */
class HospitalSpec
  extends PlaySpec
    with OneAppPerTest {

  "Create new Hospital" should {
    "return Hospital" in {
      val correct_id = 1L
      val correct_name = "test hospital name"

      val testObject = new Hospital(
        id = correct_id
        , name = correct_name
      )

      assert(correct_id == testObject.id)
      assert(correct_name == testObject.name)
    }
  }

}
