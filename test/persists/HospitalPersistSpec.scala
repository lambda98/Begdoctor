package persists

import javax.inject.Inject
import services.UuidService
import entities.HospitalEntity
import persists.postgres.HospitalPostgres
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by champillon on 6/13/16.
  */
class HospitalPersistSpec @Inject()(uuidService: UuidService)
  extends PlaySpec
    with OneAppPerSuite {

  "Call selectById" should {
    "return Hospital of that id" in {
      val correct_id = 0L
      val correct_name = "Hae Song"
      val coreect_url = "https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png"
      val correct_doctorName = "Kang Moyeon"
      val correct_latitude = 13.7854529F
      val correct_longitude = 100.5736408F
      val correct_availableTime = "10:00 - 17:00"

      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.selectById(correct_id)

      assert(correct_id == testObject.get.id)
    }
  }

  "When selectAll" should {
    "return Hospital" in {
      val persist = app.injector.instanceOf[HospitalPostgres]

      val testObject = persist.selectAll()

      assert(testObject.isInstanceOf[List[HospitalEntity]])
    }
  }

  "When selectByLocation" should {
    "return Hospital" in {
      val correct_latitude = 13.7854529F
      val correct_longitude = 100.5736408F

      val persist = app.injector.instanceOf[HospitalPostgres]

      val testObject = persist.selectByLocation(correct_latitude, correct_longitude)

      assert(testObject.isInstanceOf[List[HospitalEntity]])
    }
  }

  "Call insert" should {
    "insert Hospital data successfully" in {
      val randomId = uuidService.getId
      val correct_id = randomId
      val correct_latitude = 37.487996f
      val correct_longitude = 127.084419f
      val correct_name = "Samsung Medical Center"
      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.insert(correct_id, correct_latitude, correct_longitude, correct_name)

      assert(testObject)
    }
  }

  "Call selectByName" should {
    "return Hospitals of that name" in {
      val correct_name = "Asan Medical Center"
      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.selectByName(correct_name)

      assert(correct_name == testObject.get.name)
    }
  }

  "Call update" should {
    "update Hospital data successfully" in {
      val randomId = uuidService.getId
      val correct_id = randomId
      val correct_latitude = 36.99331f
      val correct_longitude = 127.089189f
      val correct_name = "Bagae Hospital"
      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.insert(correct_id, correct_latitude, correct_longitude, correct_name)

      assert(testObject)
    }
  }

}
