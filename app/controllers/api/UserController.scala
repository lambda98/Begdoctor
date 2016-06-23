package controllers.api

import javax.inject._

import facades.UserFacade
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

}
