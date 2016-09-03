package models

import org.joda.time.DateTime
import utilities.Json

/**
  * Created by Siam yimyam on 13/7/2559.
  */
case class HospitalTime(id: Long
                        , hospitalId: Long
                        , startDateTime: DateTime
                        , finishDateTime: DateTime
                        , available: Boolean)
  extends Json

case class HospitalTimeList(hospitalTimes: List[HospitalTime])
  extends Json