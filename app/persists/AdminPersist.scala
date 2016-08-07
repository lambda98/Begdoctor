package persists

import models.Admin
import entities.AdminEntity

/**
  * Created by anawin on 8/6/2016 AD.
  */
trait AdminPersist {

  def selectByUserName(username: String) : List[AdminEntity]
  def check(username: String, password: String) : Boolean
  def insert(id: Long
             , name: String
             , surname: String
             , email: String
             , username: String
             , password: String
             , hospitalId: Long) : Boolean

}
