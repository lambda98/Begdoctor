package models

import utilities.Json


/**
  * Created by Siam yimyam on 10/8/2559.
  */
case class HospitalRetrieval(id: Long
                             , latitude: Float
                             , longitude: Float
                             , name: String)
  extends Json
