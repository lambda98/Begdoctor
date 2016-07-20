package persists

import models.User

/**
  * Created by anawin on 6/23/2016 AD.
  */
trait UserPersist {

  def selectById(id: Long): Option[User]
  def selectByEmail(email: String): Option[User]
  def insert(id: Long
             , name: String
             , surname: String
             , email: String): Boolean
}
