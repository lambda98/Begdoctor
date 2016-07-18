package controllers.api

import javax.inject.{Inject, Singleton}
import facades.BookingFacade
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.mvc._

/**
  * Created by anawin on 7/14/2016 AD.
  */
@Singleton
class BookingController @Inject() (bookingFacade: BookingFacade)
  extends Controller {

  def getList = Action {
    Ok(bookingFacade.listAll.toText)
  }

  def getById(id: Long) = Action {
    Ok(bookingFacade.listById(id).toText)
  }

  def getByUserId(userId: Long) = Action {
    Ok(bookingFacade.listByUserId(userId).toText)
  }

  def create() = Action { implicit request =>
    CreateBookingForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        bookingFacade.create(
          name = form.name
          , surname = form.surname
          , email = form.email
          , hospitalTimeId = form.hospitalTimeId
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }
}

case class CreateBookingForm(name: String
                             , surname: String
                             , email: String
                             , hospitalTimeId: Long)
object CreateBookingForm {
  val form = Form(
    mapping(
      "name" -> of[String],
      "surname" -> of[String],
      "email" -> of[String],
      "hospitalTimeId" -> of[Long]
    )(CreateBookingForm.apply)(CreateBookingForm.unapply)
  )
}

