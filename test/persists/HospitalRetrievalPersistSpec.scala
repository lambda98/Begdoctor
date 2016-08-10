package persists

import javax.inject.Inject

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by Siam yimyam on 9/8/2559.
  */
class HospitalRetrievalPersistSpec @Inject()
  extends PlaySpec
    with OneAppPerSuite {

  "Call insert" should {
     "insert hospitalretrieval data successfully" in {
       val correct_lat = "13.7479752"
       val correct_lng = "100.5836296"
       val correct_name = "โรงพยาบาลกรุงเทพ"
       val persist = app.injector.instanceOf[HospitalRetrievalPersist]

       val testObject = persist.insert(correct_lat, correct_lng, correct_name)

       assert(testObject)
     }
   }
}
