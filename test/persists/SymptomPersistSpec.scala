package persists

import entities.SymptomEntity
import models.Symptom
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import persists.postgres.SymptomPostgres

/**
  * Created by Siam yimyam on 8/7/2559.
  */
class SymptomPersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "When selectAll" should {
    "return List of SymptomEntities" in {
      val persist = app.injector.instanceOf[SymptomPostgres]

      val testObject = persist.selectAll()

      assert(testObject.isInstanceOf[List[SymptomEntity]])
    }
  }

  "When selectById" should {
    "return SymptomEntity of that ID" in {
      val persist = app.injector.instanceOf[SymptomPostgres]
      val correct_id = 0L

      val testObject = persist.selectById(correct_id)

      assert(testObject.isInstanceOf[Option[SymptomEntity]])
    }
  }
}
