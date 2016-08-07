package facades

import javax.inject.{Inject, Singleton}

import models.{Admin, AdminList}
import persists.AdminPersist
import services.UuidService

/**
  * Created by anawin on 8/6/2016 AD.
  */
@Singleton
class AdminFacade @Inject()(uuidService: UuidService
                            , persist: AdminPersist) {

  def listByUserName(username: String): AdminList = {
    AdminList(persist.selectByUserName(username).map(
      adminEntity => adminEntity.toModel()
    ))
  }

  def check(username: String, password: String): Boolean = {
    persist.check(
      username = username
      , password = password
    )
  }

  def create(name: String
             , surname: String
             , email: String
             , username: String
             , password: String
             , hospitalId: Long): Boolean = {
    persist.insert(
      id = uuidService.getId
      , name = name
      , surname = surname
      , email = email
      , username = username
      , password = password
      , hospitalId = hospitalId
    )
  }

}
