package persists

/**
  * Created by Siam yimyam on 10/8/2559.
  */
trait HospitalRetrievalPersist {

  def insert(lat: String
             , lng: String
             , name: String): Boolean

}
