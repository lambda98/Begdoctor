package persists

import models.Hospital

/**
  * Created by champillon on 6/13/16.
  */
trait HospitalPersist {

  def findById(id: Long): Option[Hospital]

}
