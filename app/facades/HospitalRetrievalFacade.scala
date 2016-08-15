package facades
import javax.inject.{Inject, Singleton}

import models.HospitalRetrieval
import persists.HospitalRetrievalPersist
import services.UuidService

/**
  * Created by Siam yimyam on 11/8/2559.
  */
@Singleton
class HospitalRetrievalFacade @Inject()(uuidService: UuidService
                                        , persist: HospitalRetrievalPersist) {

  def findById(id: Long): HospitalRetrieval = {
    persist.selectById(id).get
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

  def findByName(name: String): HospitalRetrieval = {
    persist.selectByName(name).get
  }

}