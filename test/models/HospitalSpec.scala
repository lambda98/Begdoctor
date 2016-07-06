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
      val correct_url = "https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png"
      val correct_location = "Seoul Korea"
      val correct_doctorName = "Kang Moyeon"


      val testObject = new Hospital(
        id = correct_id
        , name = correct_name
        , url = correct_url
        , location = correct_location
        , doctorName = correct_doctorName
      )

      assert(correct_id == testObject.id)
      assert(correct_name == testObject.name)
      assert(correct_url == testObject.url)
      assert(correct_location == testObject.location)
      assert(correct_doctorName == testObject.doctorName)
    }
  }

}
