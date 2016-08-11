package facades
import javax.inject.{Inject, Singleton}

import models.HospitalRetrieval
import persists.HospitalRetrievalPersist

/**
  * Created by Siam yimyam on 11/8/2559.
  */
@Singleton
class HospitalRetrievalFacade @Inject()(persist: HospitalRetrievalPersist) {

  def create(lat: String
             , lng: String
             , name: String): Boolean = {
    persist.insert(
      lat = lat
      , lng = lng
      , name = name
    )
  }

}