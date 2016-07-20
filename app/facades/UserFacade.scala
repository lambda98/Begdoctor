package facades

import javax.inject.{Inject, Singleton}

import models.User
import persists.UserPersist
import services.UuidService

/**
  * Created by anawin on 6/23/2016 AD.
  */
@Singleton
class UserFacade @Inject()(uuidService: UuidService
                           , persist: UserPersist) {

  def findById(id: Long): User = {
    persist.selectById(id).get
  }

  def findByEmail(email: String): User = {
    persist.selectByEmail(email).get
  }

  def create(name: String
             , surname: String
             , email: String): Boolean = {
    persist.insert(
      id = uuidService.getId
      , name = name
      , surname = surname
      , email = email
    )
  }

}
