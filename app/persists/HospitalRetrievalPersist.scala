package persists

import models.HospitalRetrieval

/**
  * Created by Siam yimyam on 10/8/2559.
  */
trait HospitalRetrievalPersist {

  def selectById(id: Long): Option[HospitalRetrieval]
  def insert(id: Long
             ,lat: Float
             , lng: Float
             , name: String): Boolean

}
