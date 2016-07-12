package controllers.api

import javax.inject._

import facades.UserFacade
import play.api.libs.json.Json
import play.api.mvc._

/**
  * Created by anawin on 6/23/2016 AD.
  */
@Singleton
class UserController @Inject()(userFacade: UserFacade)
  extends Controller {

  def getUser(Id: Long) = Action {
    Ok(userFacade.findById(Id).toText)
  }

  def getUserByEmail(email: String) = Action {
    Ok(userFacade.findByEmail(email).toText)
  }

  def saveUser(name: String, surname: String, email: String) = Action {
    Ok(
      Json.toJson(
        userFacade.insertUser(
          name
          , surname
          , email
        )
      )
    )
  }

}
