package persists

import entities.HospitalTimeEntity
import org.joda.time.DateTime
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by Administrator on 13/7/2559.
  */
class HospitalTimePersistSpec
  extends PlaySpec
    with OneAppPerSuite {

  "Call selectById" should {
    "return Hospital of that id" in {
      val persist = app.injector.instanceOf[HospitalTimePersist]
      val id = 1L

      val testObject = persist.selectById(id)

      assert(testObject.isInstanceOf[Option[HospitalTimeEntity]])
    }
  }

  "Call selectByDate" should {
    "return Hospital of that hospital within that date" in {
      val persist = app.injector.instanceOf[HospitalTimePersist]
      val hospitalId = 1L
      val fromDate = "2016-07-01"
      val tilDate = "2016-07-02"

      val testObject = persist.selectByDate(
        hospitalId = hospitalId
        , fromDate = fromDate
        , tilDate = tilDate
      )

      assert(testObject.isInstanceOf[List[HospitalTimeEntity]])
    }
  }
}
