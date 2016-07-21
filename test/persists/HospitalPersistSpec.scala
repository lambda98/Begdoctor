package persists

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by champillon on 6/13/16.
  */
class HospitalPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call selectById" should {
    "return Hospital of that id" in {
      val correct_id = 0L
      val correct_name = "Hae Song"
      val coreect_url = "https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png"
      val correct_location = "Seoul Korea"
      val correct_doctor_name = "Kang Moyeon"
      val correct_types = "Hospital"
      val correct_latitude = "13.7854529"
      val correct_longitude = "100.5736408"
      val correct_available_time = "10:00 - 17:00"

      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.selectById(correct_id)

      assert(correct_id == testObject.get.id)
      assert(correct_name == testObject.get.name)

    }
  }

}
