package facades

import javax.inject.{Inject, Singleton}

import models.{Staff, StaffList}
import persists.StaffPersist
import services.UuidService
import com.github.t3hnar.bcrypt._

/**
  * Created by anawin on 8/6/2016 AD.
  */
@Singleton
class StaffFacade @Inject()(uuidService: UuidService
                            , persist: StaffPersist) {


  def authenticate(username: String, password: String): Boolean = {
    val staffEntity = persist.selectByUserName(username = username).get
    password.isBcrypted(staffEntity.password)
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
      , password = password.bcrypt
      , hospitalId = hospitalId
    )
  }

}
