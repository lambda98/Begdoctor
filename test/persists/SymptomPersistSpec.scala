package persists

import models.Symptom
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import persists.postgres.SymptomPostgres

/**
  * Created by Siam yimyam on 8/7/2559.
  */
class SymptomPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "When findById" should {
    "return Symptom" in {
      val persist = app.injector.instanceOf[SymptomPostgres]

      val testObject = persist.listAllSymptom()

      assert(testObject.isInstanceOf[List[Symptom]])

    }
  }
}
