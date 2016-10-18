package persists

import definitions.BookingStatus._
import entities.BookingEntity

/**
  * Created by anawin on 7/14/2016 AD.
  */
trait BookingPersist {

  def selectAll(): List[BookingEntity]

  def selectById(id: Long): List[BookingEntity]

  def selectByUserId(userId: Long): List[BookingEntity]

  def insert(id: Long
             , userId: Long
             , hospitalTimeId: Long
             , symptomId: Long
             , status: BookingStatus): Boolean
}
