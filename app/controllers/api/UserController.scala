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

  def getUser(Id: Long) = Action {
    Ok(userFacade.findById(Id).toText)
  }

  def getUserByEmail(email: String) = Action {
    Ok(userFacade.findByEmail(email).toText)
  }

  def saveUserData() = Action { implicit request =>
    CreateUserForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        userFacade.insertUser(
          name = form.name
          , surname = form.surname
          , email = form.email
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }
}

case class CreateUserForm(name: String
                          , surname: String
                          , email: String)
object CreateUserForm {
  val form = Form(
    mapping(
      "name" -> of[String],
      "surname" -> of[String],
      "email" -> of[String]
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  )
}
