package persists

import entities.BookingEntity

/**
  * Created by anawin on 7/14/2016 AD.
  */
trait BookingPersist {

  def selectAll()  : List[BookingEntity]
  def selectById(id: Long) : List[BookingEntity]
  def selectByUserId(userId: Long) : List[BookingEntity]
  def insert(id: Long
             , userId: Long
             , hospitalTimeId: Long): Boolean
}
