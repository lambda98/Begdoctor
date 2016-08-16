package entities

import models.Hospital

/**
  * Created by anawin on 8/16/2016 AD.
  */
case class HospitalEntity(id: Long
                          , name: String
                          , url: String
                          , doctorName: String
                          , latitude: Float
                          , longitude: Float
                          , availableTime: String) {

  def toModel(): Hospital = Hospital(
    id = id
    , name = name
    , url = url
    , doctorName = doctorName
    , latitude = latitude
    , longitude = longitude
    , availableTime = availableTime
  )

}
