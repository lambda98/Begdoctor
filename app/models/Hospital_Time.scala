package models

import org.joda.time.DateTime
import utilities.Json

/**
  * Created by Siam yimyam on 13/7/2559.
  */
case class Hospital_Time (id: Long
                     , hospital_id: Long
                     , start_datetime: DateTime
                     , finish_datetime: DateTime
                     , available: Boolean)
  extends Json