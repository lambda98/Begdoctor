package facades

import javax.inject.{Inject, Singleton}

import entities.HospitalTimeEntity
import models.{HospitalTime, HospitalTimeSlot, HospitalTimeSlotList}
import org.joda.time.DateTime
import persists.HospitalTimePersist
import utilities.DateConverter

/**
  * Created by Siam yimyam on 13/7/2559.
  */
@Singleton
class HospitalTimeFacade @Inject()(persist: HospitalTimePersist) {

  def findById(id: Long): HospitalTime = {
    persist.selectById(id).get.toModel()
  }

  def findByDate(hospitalId: Long, date: String): HospitalTimeSlotList = {
    toHospitalTimeSlotList(
      persist.selectByDate(hospitalId, date, plusDate(date, 1))
      , date = date
    )

  }

  private def toHospitalTimeSlotList(hospitalTimeEntities: List[HospitalTimeEntity], date: String): HospitalTimeSlotList = {
    val timeSlots = hospitalTimeEntities.map(
      hospitalTimeEntity => toHospitalTimeSlot(hospitalTimeEntity)
    )
    val hospitalTimeEntity = hospitalTimeEntities.head

    HospitalTimeSlotList(
      hospital_id = hospitalTimeEntity.hospitalId
      , date = date
      , time_slots = timeSlots
    )
  }

  private def toHospitalTimeSlot(hospitalTimeEntity: HospitalTimeEntity): HospitalTimeSlot = {
    HospitalTimeSlot(
      id = hospitalTimeEntity.id
      , time_slot = toTimeSlot(
        startDate = hospitalTimeEntity.startDateTime
        , finishDate = hospitalTimeEntity.finishDateTime
      )
    )
  }

  private def toTimeSlot(startDate: DateTime, finishDate: DateTime): String = {
    DateConverter.timeToString(startDate) + " - " + DateConverter.timeToString(finishDate)
  }

  private def plusDate(date: String, duration: Int): String = {
    val plusDate = DateConverter.stringToDate(date).plusDays(duration)
    plusDate.toString("yyyy-MM-dd")
  }

}
