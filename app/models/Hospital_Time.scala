package models

import utilities.Json

/**
  * Created by Siam yimyam on 13/7/2559.
  */
class Hospital_Time (id: Long
                     , hospital_id: Long
                     , start_datetime: String
                     , finish_datetime: String
                     , available: String)
  extends Json