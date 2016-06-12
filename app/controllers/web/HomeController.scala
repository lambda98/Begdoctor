package controllers.web

import javax.inject._

import play.api.mvc._

/**
  * Created by champillon on 6/12/16.
  */
@Singleton
class HomeController @Inject()
  extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
