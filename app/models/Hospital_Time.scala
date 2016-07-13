package models

import play.data.format.Formats.DateTime
import utilities.Json

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class Hospital_Time (id: Long
                     , hospital_id: Long
                     , start_datetime: DateTime
                     , finish_datetime: DateTime
                     , available: String)
  extends Json