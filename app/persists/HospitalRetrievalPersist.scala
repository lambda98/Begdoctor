package persists

import models.HospitalRetrieval

/**
  * Created by Siam yimyam on 10/8/2559.
  */
trait HospitalRetrievalPersist {

  def selectById(id: Long): Option[HospitalRetrieval]
  def insert(id: Long
             , latitude: Float
             , longitude: Float
             , name: String): Boolean
  def selectByName(name: String): Option[HospitalRetrieval]

}
