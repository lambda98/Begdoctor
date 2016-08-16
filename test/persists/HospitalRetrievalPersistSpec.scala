package persists

import javax.inject.Inject
import services.UuidService
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by Siam yimyam on 9/8/2559.
  */
class HospitalRetrievalPersistSpec @Inject()(uuidService: UuidService)
  extends PlaySpec
    with OneAppPerSuite {

  "Call insert" should {
     "insert hospitalretrieval data successfully" in {
       val randomId = uuidService.getId
       val correct_id = randomId
       val correct_latitude = 37.487996f
       val correct_longitude = 127.084419f
       val correct_name = "Samsung Medical Center"
       val persist = app.injector.instanceOf[HospitalRetrievalPersist]

       val testObject = persist.insert(correct_id, correct_latitude, correct_longitude, correct_name)

       assert(testObject)
     }
   }

  "Call selectByName" should {
    "return HospitalRetrieval of that name" in {
      val correct_name = "Asan Medical Center"
      val persist = app.injector.instanceOf[HospitalRetrievalPersist]

      val testObject = persist.selectByName(correct_name)

      assert(correct_name == testObject.get.name)
    }
  }

  "Call update" should {
     "update hospitalretrieval data successfully" in {
       val randomId = uuidService.getId
       val correct_id = randomId
       val correct_latitude = 36.99331f
       val correct_longitude = 127.089189f
       val correct_name = "Bagae Hospital"
       val persist = app.injector.instanceOf[HospitalRetrievalPersist]

       val testObject = persist.insert(correct_id, correct_latitude, correct_longitude, correct_name)

       assert(testObject)
     }
  }
}
