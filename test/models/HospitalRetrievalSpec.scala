package models

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}

/**
  * Created by Siam yimyam on 10/8/2559.
  */
class HospitalRetrievalSpec
  extends PlaySpec
    with OneAppPerTest {

  "Create new HospitalRetrieval" should {
    "return HospitalRetrieval" in {
      val correct_id = 1L
      val correct_latitude = 13.7479752f
      val correct_longitude = 100.5836296f
      val correct_name = "โรงพยาบาลกรุงเทพ"

      val testObject = new HospitalRetrieval(
        id = correct_id
        , latitude = correct_latitude
        , longitude = correct_longitude
        , name = correct_name
      )

      assert(correct_id == testObject.id)
      assert(correct_latitude == testObject.latitude)
      assert(correct_longitude == testObject.longitude)
      assert(correct_name == testObject.name)
    }
  }
}
