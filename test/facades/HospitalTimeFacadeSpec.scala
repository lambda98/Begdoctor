package facades

import models.{HospitalTime, HospitalTimeList, HospitalTimeSlotList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class HospitalTimeFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call findById" should {
    "return HospitalTime of that id" in {
      val id = 1L
      val facade = app.injector.instanceOf[HospitalTimeFacade]

      val testObject = facade.findById(id)

      assert(testObject.isInstanceOf[HospitalTime])
    }
  }

  "Call findByDate" should {
    "return HospitalTimeSlotList of that hospitalId and date" in {
      val hospitalId = 1L
      val date = "2016-07-01"
      val facade = app.injector.instanceOf[HospitalTimeFacade]

      val testObject = facade.findByDate(hospitalId, date)

      assert(testObject.isInstanceOf[HospitalTimeSlotList])
    }
  }

}
