package persists

import javax.inject.Inject
import services.UuidService
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
      val persist = app.injector.instanceOf[HospitalPersist]
      val correct_id = 0L

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
      val uuidService = app.injector.instanceOf[UuidService]
      val persist = app.injector.instanceOf[HospitalPersist]
      val correct_id = uuidService.getId
      val correct_latitude = 37.487996f
      val correct_longitude = 127.084419f
      val correct_name = "Gug Il Medical Center"


      val testObject = persist.insert(correct_id, correct_latitude, correct_longitude, correct_name)

      assert(testObject)
    }
  }

  "Call selectByName" should {
    "return Hospitals of that name" in {
      val correct_name = "โรงพยาบาลบำรุงราษฎร์"
      val persist = app.injector.instanceOf[HospitalPersist]

      val testObject = persist.selectByName(correct_name)

      assert(correct_name == testObject.get.name)
    }
  }

  "Call update" should {
    "update Hospital data successfully" in {
      val uuidService = app.injector.instanceOf[UuidService]
      val persist = app.injector.instanceOf[HospitalPersist]
      val correct_id = uuidService.getId
      val correct_latitude = 36.99331f
      val correct_longitude = 127.089189f
      val correct_name = "Hae Yong Medical Service"

      val testObject = persist.insert(correct_id, correct_latitude, correct_longitude, correct_name)

      assert(testObject)
    }
  }

}
