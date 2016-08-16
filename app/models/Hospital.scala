package models

import utilities.Json

/**
  * Created by champillon on 6/12/16.
  */
case class Hospital(id: Long
                    , name: String
                    , url: String
                    , doctorName: String
                    , latitude: Float
                    , longitude: Float
                    , availableTime: String)
  extends Json

case class HospitalList(hospitals: List[Hospital])
  extends Json