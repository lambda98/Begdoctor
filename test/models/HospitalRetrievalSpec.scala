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
      val correct_lat = 13.7479752f
      val correct_lng = 100.5836296f
      val correct_name = "โรงพยาบาลกรุงเทพ"

      val testObject = new HospitalRetrieval(
        id = correct_id
        , lat = correct_lat
        , lng = correct_lng
        , name = correct_name
      )

      assert(correct_id == testObject.id)
      assert(correct_lat == testObject.lat)
      assert(correct_lng == testObject.lng)
      assert(correct_name == testObject.name)
    }
  }
}
