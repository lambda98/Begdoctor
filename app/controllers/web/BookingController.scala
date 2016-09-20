package controllers.web

import javax.inject.{Inject, Singleton}
import facades.BookingFacade
import play.api.mvc._


/**
  * Created by champillon on 9/20/16.
  */
@Singleton
class BookingController @Inject()(bookingFacade: BookingFacade)
  extends Controller {

  def getUpComing() = Action {
    Ok(bookingFacade.listUpComping().toText)
  }

  def getBooking() = Action {
    Ok(bookingFacade.listWebBooking().toText)
  }
}
