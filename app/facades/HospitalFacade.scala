package facades

import javax.inject.{Inject, Singleton}

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

  def create(latitude: Float
             , longitude: Float
             , name: String): Boolean = {
    persist.insert(
      id = uuidService.getId
      , latitude = latitude
      , longitude = longitude
      , name = name
    )
  }

  def findByName(name: String): Hospital = {
    persist.selectByName(name).getOrElse(null)
  }

  def update(latitude: Float
             , longitude: Float
             , name: String): Boolean = {
    persist.update(
      id = uuidService.getId
      , latitude = latitude
      , longitude = longitude
      , name = name
    )
  }

  def createOrUpdate( latitude: Float
                      , longitude: Float
                      , name: String): Boolean = {
    val result = findByName(
      name = name)
    if(result == null){
      create(latitude
        , longitude
        , name)
    }
    else{
      update(latitude
        , longitude
        , name)
    }
  }

}
