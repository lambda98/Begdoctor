package controllers.api

import javax.inject._

import facades.UserFacade
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import play.api.mvc._

/**
  * Created by anawin on 6/23/2016 AD.
  */
@Singleton
class UserController @Inject()(userFacade: UserFacade)
  extends Controller {

  def getById(id: Long) = Action {
    Ok(userFacade.findById(id).toText)
  }

  def getByEmail(email: String) = Action {
    Ok(userFacade.findByEmail(email).toText)
  }
}
