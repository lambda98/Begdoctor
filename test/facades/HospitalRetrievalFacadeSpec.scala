package facades

import javax.inject.Inject

import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder
import services.UuidService

/**
  * Created by Siam yimyam on 11/8/2559.
  */
class HospitalRetrievalFacadeSpec @Inject()(uuidService: UuidService)
  extends PlaySpec
    with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "Call findById" should {
    "return HospitalRetrieval of that id" in {
      val correct_id = 0L
      val facade = app.injector.instanceOf[HospitalRetrievalFacade]

      val testObject = facade.findById(correct_id)

      assert(correct_id == testObject.id)
    }
  }

  "Call create" should {
    "insert data successfully" in {
      val randomString = uuidService.getId
      val correct_latitude = 13.7479752f
      val correct_longitude = 100.5836296f
      val correct_name = "โรงพยาบาลกรุงเทพ"
      val facade = app.injector.instanceOf[HospitalRetrievalFacade]

      val testObject = facade.create(
        correct_latitude
        , correct_longitude
        , correct_name)

      assert(testObject)
    }
  }

  "Call findByName" should {
    "return HospitalRetrieval of that name" in {
      val correct_name = "โรงพยาบาลสมิติเวช ธนบุรี (Samitivej Thonburi)"
      val facade = app.injector.instanceOf[HospitalRetrievalFacade]

      val testObject = facade.findByName(correct_name)

      assert(correct_name == testObject.name)
    }
  }

  "Call update" should {
    "update data successfully" in {
      val randomString = uuidService.getId
      val correct_latitude = 13.735052f
      val correct_longitude = 100.576692f
      val correct_name = "โรงพยาบาลสมิติเวช สุขุมวิท"
      val facade = app.injector.instanceOf[HospitalRetrievalFacade]

      val testObject = facade.update(
        correct_latitude
        , correct_longitude
        , correct_name)

      assert(testObject)
    }
  }

}
