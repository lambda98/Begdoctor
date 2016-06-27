package facades

import javax.inject.{Singleton, Inject}

import models.User
import persists.UserPersist

/**
  * Created by anawin on 6/23/2016 AD.
  */
@Singleton
class UserFacade  @Inject()(persist: UserPersist){

  def findById(id: Long): User = {
    persist.findById(id).get
  }

  def findByEmail(email: String): User = {
    persist.findByEmail(email).get
  }

}
