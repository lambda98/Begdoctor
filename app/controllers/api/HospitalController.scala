package controllers.api

import javax.inject._

import facades.HospitalFacade
import models.Hospital
import play.api.mvc._

/**
  * Created by champillon on 6/12/16.
  */
@Singleton
class HospitalController @Inject()(hospitalFacade: HospitalFacade)
  extends Controller {

  def getHospital(Id: Long) = Action {
    Ok(new Hospital(0L,"hhhh").toText)
  }

}
