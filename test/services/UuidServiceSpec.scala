package services

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.inject.guice.GuiceApplicationBuilder

/**
  * Created by champillon on 7/12/16.
  * refer to tek #B
  */
@RunWith(classOf[JUnitRunner])
class UuidServiceSpec extends PlaySpec with OneAppPerSuite {

  implicit override lazy val app = new GuiceApplicationBuilder().build

  "When calls get" should {

    "return random UUID" in {

      val service = app.injector.instanceOf[UuidService]
      val stackSize = service.size

      assert(service.get != null)
      Thread.sleep(100)
      assert(service.size == stackSize)

    }

  }

}