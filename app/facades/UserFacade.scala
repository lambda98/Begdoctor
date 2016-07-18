package facades

import javax.inject.{Inject, Singleton}

import models.User
import persists.UserPersist
import services.UuidService

/**
  * Created by anawin on 6/23/2016 AD.
  */
@Singleton
class UserFacade @Inject()(uuidSercice: UuidService
                           , persist: UserPersist) {

  def findById(id: Long): User = {
    persist.findById(id).get
  }

  def findByEmail(email: String): User = {
    persist.findByEmail(email).get
  }

  def insertUser(name: String
                 , surname: String
                 , email: String): Boolean = {
    persist.insertUser(
      id = uuidSercice.getId
      , name = name
      , surname = surname
      , email = email
    )
  }

}
