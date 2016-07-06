package models

import utilities.Json

/**
  * Created by champillon on 6/12/16.
  */
case class Hospital(id: Long
                    , name: String
                    , url: String
                    , location: String
                    , doctorName: String)
  extends Json