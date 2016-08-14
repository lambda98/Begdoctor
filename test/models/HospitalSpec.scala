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
      val correct_name = "Hae Song"
      val correct_url = "https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png"
      val correct_doctorName = "Kang Moyeon"
      val correct_latitude = 13.7854529f
      val correct_longitude = 100.5736408f
      val correct_available_time = "10:00 - 17:00"



      val testObject = new Hospital(
        id = correct_id
        , name = correct_name
        , url = correct_url
        , doctorName = correct_doctorName
        , latitude = correct_latitude
        , longitude = correct_longitude
        , availableTime = correct_available_time
      )

      assert(correct_id == testObject.id)
      assert(correct_name == testObject.name)
      assert(correct_url == testObject.url)
      assert(correct_doctorName == testObject.doctorName)
      assert(correct_latitude == testObject.latitude)
      assert(correct_longitude == testObject.longitude)
      assert(correct_available_time == testObject.availableTime)
    }
  }

}
