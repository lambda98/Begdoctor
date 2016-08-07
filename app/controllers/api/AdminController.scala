package controllers.api

import javax.inject._

import facades.AdminFacade
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import play.api.mvc._

/**
  * Created by anawin on 8/6/2016 AD.
  */
@Singleton
class AdminController @Inject()(adminFacade: AdminFacade)
  extends Controller {

  def getByUserName(username: String) = Action {
    Ok(adminFacade.listByUserName(username).toText)
  }

  def check() = Action { implicit request =>
    LoginAdminForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        adminFacade.check(
          username = form.username
          , password = form.password
        )
        Ok(form.username) as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }

  def create() = Action { implicit request =>
    CreateAdminForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        adminFacade.create(
          name = form.name
          , surname = form.surname
          , email = form.email
          , username = form.username
          , password = form.password
          , hospitalId = form.hospitalId
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }

}

case class CreateAdminForm(name: String
                           , surname: String
                           , email: String
                           , username: String
                           , password: String
                           , hospitalId: Long)
object CreateAdminForm {
  val form = Form(
    mapping(
      "name" -> of[String],
      "surname" -> of[String],
      "email" -> of[String],
      "username" -> of[String],
      "password" -> of[String],
      "hospitalId" -> of[Long]
    )(CreateAdminForm.apply)(CreateAdminForm.unapply)
  )
}

case class LoginAdminForm(username: String
                           , password: String)
object LoginAdminForm {
  val form = Form(
    mapping(
      "username" -> of[String],
      "password" -> of[String]
    )(LoginAdminForm.apply)(LoginAdminForm.unapply)
  )
}
