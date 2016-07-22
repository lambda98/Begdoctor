package controllers.api

import javax.inject.{Inject, Singleton}
import facades.HospitalTimeFacade
import play.api.mvc.{Action, Controller}

/**
  * Created by Administrator on 13/7/2559.
  */
@Singleton
class HospitalTimeController @Inject()(hospitalTimeFacade: HospitalTimeFacade)
  extends Controller {

  def getById(Id: Long) = Action {
    Ok(hospitalTimeFacade.findById(Id).toText)
  }

}
