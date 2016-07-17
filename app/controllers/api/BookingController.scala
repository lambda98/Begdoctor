package controllers.api

import com.google.inject.{Inject, Singleton}
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

  def getBooking = Action {
    Ok(bookingFacade.listAllBooking.toText)
  }

  def getBookingById(id: Long) = Action {
    Ok(bookingFacade.listBookingById(id).toText)
  }

  def getBookingByUserId(user_id: Long) = Action {
    Ok(bookingFacade.listBookingByUserId(user_id).toText)
  }

  def saveBookingData() = Action { implicit request =>
    CreateBookingForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        bookingFacade.insertBooking(
          name = form.name
          , surname = form.surname
          , email = form.email
          , hospital_time_id = form.hospital_time_id
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
                             , hospital_time_id: Long)
object CreateBookingForm {
  val form = Form(
    mapping(
      "name" -> of[String],
      "surname" -> of[String],
      "email" -> of[String],
      "hospital_time_id" -> of[Long]
    )(CreateBookingForm.apply)(CreateBookingForm.unapply)
  )
}

