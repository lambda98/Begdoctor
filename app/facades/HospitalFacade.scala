package facades

import javax.inject.{Inject, Singleton}

import models.{Hospital, HospitalList}
import persists.HospitalPersist

/**
  * Created by champillon on 6/12/16.
  */
@Singleton
class HospitalFacade @Inject()(persist: HospitalPersist) {

  def findById(id: Long): Hospital = {
     persist.selectById(id).get
  }

  def listAll: HospitalList = {
    HospitalList(persist.selectAll.map(
      hospitalEntity => hospitalEntity.toModel()
    ))
  }

  def listByLocation(latitude: Float
                     , longitude: Float): HospitalList = {
    HospitalList(persist.selectByLocation(latitude, longitude).map(
      hospitalEntity => hospitalEntity.toModel()
    ))
  }

}
