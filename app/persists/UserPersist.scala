package persists

import entities.UserEntity

/**
  * Created by anawin on 6/23/2016 AD.
  */
trait UserPersist {

  def selectById(id: Long): Option[UserEntity]

  def selectByEmail(email: String): Option[UserEntity]

  def insert(id: Long
             , name: String
             , surname: String
             , email: String
             , mobile: String): Boolean
}
