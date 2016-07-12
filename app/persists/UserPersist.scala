package persists

import models.User

/**
  * Created by anawin on 6/23/2016 AD.
  */
trait UserPersist {

  def findById(id: Long): Option[User]
  def findByEmail(email: String): Option[User]
  def insertUser(id: Long, name: String, surname: String, email: String): Boolean
}
