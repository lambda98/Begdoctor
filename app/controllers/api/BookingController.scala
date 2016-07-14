package controllers.api

import com.google.inject.{Inject, Singleton}
import facades.BookingFacade
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
}
