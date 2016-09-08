package facades

import javax.inject.{Inject, Singleton}

import entities.HospitalEntity
import models.{Hospital, HospitalList}
import persists.HospitalPersist
import services.UuidService

/**
  * Created by champillon on 6/12/16.
  */
@Singleton
class HospitalFacade @Inject()(uuidService: UuidService
                               , persist: HospitalPersist) {

  def findById(id: Long): Hospital = {
    persist.selectById(id).get.toModel()
  }

  def findByName(name: String): Hospital = {
    persist.selectByName(name).get.toModel()
  }

  def listByLocation(latitude: Float, longitude: Float): HospitalList = {
    toHospitalList(
      persist.selectByLocation(latitude, longitude)
    )
  }

  def listAll: HospitalList = {
    toHospitalList(
      persist.selectAll
    )
  }

  def save(latitude: Float, longitude: Float, name: String): Boolean = {
    isCreated(name) match {
      case false => create(
        latitude
        , longitude
        , name
      )
      case true => update(
        latitude
        , longitude
        , name
      )
    }
  }

  private def create(latitude: Float
                     , longitude: Float
                     , name: String): Boolean = {
    persist.insert(
      id = uuidService.getId
      , latitude = latitude
      , longitude = longitude
      , name = name
    )
  }

  private def update(latitude: Float
                     , longitude: Float
                     , name: String): Boolean = {
    persist.update(
      id = uuidService.getId
      , latitude = latitude
      , longitude = longitude
      , name = name
    )
  }

  private def toHospitalList(hospitalEntities: List[HospitalEntity]): HospitalList = {
    HospitalList(hospitalEntities.map(
      hospitalEntity => hospitalEntity.toModel()
    ))
  }

  private def isCreated(name: String): Boolean = {
    persist.selectByName(name) match {
      case None => false
      case _ => true
    }
  }

}
