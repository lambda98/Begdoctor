package persists

import models.Staff
import entities.StaffEntity

/**
  * Created by anawin on 8/6/2016 AD.
  */
trait StaffPersist {

  def selectByUserName(username: String) : Option[StaffEntity]
  def insert(id: Long
             , name: String
             , surname: String
             , email: String
             , username: String
             , password: String
             , hospitalId: Long) : Boolean

}
