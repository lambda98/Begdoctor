package persists

import entities.HospitalEntity
import persists.postgres.HospitalPostgres
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
      val correct_doctorName = "Kang Moyeon"
      val correct_latitude = 13.7854529f
      val correct_longitude = 100.5736408f
      val correct_availableTime = "10:00 - 17:00"

      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.selectById(correct_id)

      assert(testObject.isInstanceOf[List[HospitalEntity]])

    }
  }

  "When selectAll" should {
    "return Booking" in {
      val persist = app.injector.instanceOf[HospitalPostgres]

      val testObject = persist.selectAll()

      assert(testObject.isInstanceOf[List[HospitalEntity]])
    }
  }

}
