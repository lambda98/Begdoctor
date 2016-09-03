package persists

import entities.HospitalEntity
import models.Hospital

/**
  * Created by champillon on 6/13/16.
  */
trait HospitalPersist {

  def selectById(id: Long): Option[HospitalEntity]

  def selectByName(name: String): Option[HospitalEntity]

  def selectByLocation(latitude: Float
                       , longitude: Float): List[HospitalEntity]

  def selectAll(): List[HospitalEntity]

  def insert(id: Long
             , latitude: Float
             , longitude: Float
             , name: String): Boolean

  def update(id: Long
             , latitude: Float
             , longitude: Float
             , name: String): Boolean

}
