package controllers.api

import javax.inject._

import facades.StaffFacade
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import play.api.mvc._

/**
  * Created by anawin on 8/6/2016 AD.
  */
@Singleton
class StaffController @Inject()(staffFacade: StaffFacade)
  extends Controller {

  def authenticate() = Action { implicit request =>
    LoginStaffForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        staffFacade.authenticate(
          username = form.username
          , password = form.password
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }

  def create() = Action { implicit request =>
    CreateStaffForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        staffFacade.create(
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

case class CreateStaffForm(name: String
                           , surname: String
                           , email: String
                           , username: String
                           , password: String
                           , hospitalId: Long)
object CreateStaffForm {
  val form = Form(
    mapping(
      "name" -> of[String],
      "surname" -> of[String],
      "email" -> of[String],
      "username" -> of[String],
      "password" -> of[String],
      "hospitalId" -> of[Long]
    )(CreateStaffForm.apply)(CreateStaffForm.unapply)
  )
}

case class LoginStaffForm(username: String
                           , password: String)
object LoginStaffForm {
  val form = Form(
    mapping(
      "username" -> of[String],
      "password" -> of[String]
    )(LoginStaffForm.apply)(LoginStaffForm.unapply)
  )
}
