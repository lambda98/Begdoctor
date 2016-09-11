package utilities

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
  * Created by champillon on 9/11/16.
  */
object DateConverter {

  def stringToDate(date: String) = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(date)

  def timeToString(dateTime: DateTime) = DateTimeFormat.forPattern("HH:mm").print(dateTime)

}
