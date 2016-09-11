package utilities

import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}

/**
  * Created by champillon on 9/11/16.
  */
@RunWith(classOf[JUnitRunner])
class DateConverterSpec extends PlaySpec with OneAppPerSuite {

  "When call stringToDate" should {
    "return date of that string" in {
      val input = "2016-03-12"
      val expected = "12/03/2016"

      val result = DateConverter.stringToDate(input)

      assert(result.toString("dd/MM/yyyy") == expected)
    }
  }

  "When call timeToString" should {
    "return string of that time" in {
      val input = new DateTime(2005, 3, 26, 12, 0, 0, 0);
      val expected = "12:00"

      val result = DateConverter.timeToString(input)

      assert(result == expected)
    }
  }

}
