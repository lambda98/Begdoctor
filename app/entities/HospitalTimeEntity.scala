package entities

import models.HospitalTime
import org.joda.time.DateTime

/**
  * Created by anawin on 8/20/2016 AD.
  */
case class HospitalTimeEntity(id: Long
                              , hospitalId: Long
                              , startDateTime: DateTime
                              , finishDateTime: DateTime
                              , available: Boolean) {

  def toModel(): HospitalTime = HospitalTime(
    id = id
    , hospitalId = hospitalId
    , startDateTime = startDateTime
    , finishDateTime = finishDateTime
    , available = available
  )
}
