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
       val randomString = uuidService.getId
       val randomId = uuidService.getId
       val correct_id = randomId
       val correct_lat = 13.7479752f
       val correct_lng = 100.5836296f
       val correct_name = "โรงพยาบาลกรุงเทพ"
       val persist = app.injector.instanceOf[HospitalRetrievalPersist]

       val testObject = persist.insert(correct_id, correct_lat, correct_lng, correct_name)

       assert(testObject)
     }
   }
}
