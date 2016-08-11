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

  def create(lat: Float
             , lng: Float
             , name: String): Boolean = {
    persist.insert(
      id = uuidService.getId
      , lat = lat
      , lng = lng
      , name = name
    )
  }

}