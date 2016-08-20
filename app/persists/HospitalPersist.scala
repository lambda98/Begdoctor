package persists

import entities.HospitalEntity
import models.Hospital

/**
  * Created by champillon on 6/13/16.
  */
trait HospitalPersist {

  def selectById(id: Long): Option[Hospital]
  def selectAll():  List[HospitalEntity]
  def selectByLocation(latitude: Float
                       , longitude: Float):  List[HospitalEntity]

  def insert(id: Long
             , latitude: Float
             , longitude: Float
             , name: String): Boolean

  def selectByName( name: String): Option[Hospital]

  def update(id: Long
             , latitude: Float
             , longitude: Float
             , name: String): Boolean

}
