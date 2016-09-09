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
    persist.selectById(id).get.toModel()
  }

  def findByEmail(email: String): User = {
    persist.selectByEmail(email).get.toModel()
  }

  def create(name: String
             , surname: String
             , email: String
             , mobile: String): Boolean = {
    persist.insert(
      id = uuidService.getId
      , name = name
      , surname = surname
      , email = email
      , mobile = mobile
    )
  }

}
