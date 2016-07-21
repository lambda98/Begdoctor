package controllers.api

import javax.inject._

import facades.HospitalFacade

import play.api.mvc._

/**
  * Created by champillon on 6/12/16.
  */
@Singleton
class HospitalController @Inject()(hospitalFacade: HospitalFacade)
  extends Controller {

  def getById(Id: Long) = Action {
    Ok(hospitalFacade.findById(Id).toText)
  }

}
