package facades

import models.{Hospital, HospitalList}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder


/**
  * Created by champillon on 6/12/16.
  */
class HospitalFacadeSpec
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call findById" should {
    "return Hospital of that id" in {
      val correct_id = 0L
      val facade = app.injector.instanceOf[HospitalFacade]

      val testObject = facade.findById(correct_id)

      assert(correct_id == testObject.id)
    }
  }

  "Call listAll" should {
    "return Hospital " in {
      val facade = app.injector.instanceOf[HospitalFacade]

      val testObject = facade.listAll

      assert(testObject.isInstanceOf[HospitalList])
    }
  }

  "Call findByLocation" should {
    "return Hospital " in {
      val latitude = 13.7854529F
      val longitude = 100.5736408F
      val facade = app.injector.instanceOf[HospitalFacade]

      val testObject = facade.listByLocation(latitude, longitude)

      assert(testObject.isInstanceOf[HospitalList])
    }
  }

  "Call findByName" should {
    "return Hospital of that name" in {
      val correct_name = "โรงพยาบาลบำรุงราษฎร์"
      val facade = app.injector.instanceOf[HospitalFacade]

      val testObject = facade.findByName(correct_name)

      assert(correct_name == testObject.name)
    }
  }

  "Call save" should {
    "save data for create successfully" in {
      val correct_latitude = 17.7777f
      val correct_longitude = 100.1111f
      val correct_name = "โรงพยาบาลลาดกระบัง"
      val facade = app.injector.instanceOf[HospitalFacade]

      val testObject = facade.save(correct_latitude
        , correct_longitude
        , correct_name)

      assert(testObject)
    }
    "save data for update successfully" in {
      val correct_latitude = 17.7777f
      val correct_longitude = 100.1111f
      val correct_name = "Samsung Medical Center"
      val facade = app.injector.instanceOf[HospitalFacade]

      val testObject = facade.save(correct_latitude
        , correct_longitude
        , correct_name)

      assert(testObject)
    }
  }

}
