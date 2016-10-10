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

case class HospitalTimeSlot(id: Long, time_slot: String)

case class HospitalTimeSlotList(hospital_id: Long
                                , date: String
                                , time_slots: List[HospitalTimeSlot])
  extends Json