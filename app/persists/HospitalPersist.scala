package persists

import entities.HospitalEntity
import models.Hospital

/**
  * Created by champillon on 6/13/16.
  */
trait HospitalPersist {

  def selectById(id: Long): List[HospitalEntity]
  def selectAll():  List[HospitalEntity]
  def selectByLocation(latitude: Float
                       , longitude: Float):  List[HospitalEntity]

}
