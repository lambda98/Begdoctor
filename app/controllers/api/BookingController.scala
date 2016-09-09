package controllers.api

import javax.inject.{Inject, Singleton}
import facades.BookingFacade
import play.api.mvc._

/**
  * Created by anawin on 7/14/2016 AD.
  */
@Singleton
class BookingController @Inject()(bookingFacade: BookingFacade)
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
          , mobile = form.mobile
          , hospitalTimeId = form.hospitalTimeId
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }

}



