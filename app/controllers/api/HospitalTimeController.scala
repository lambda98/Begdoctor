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

  def getById(id: Long) = Action {
    Ok(hospitalTimeFacade.findById(id).toText)
  }

  def getByDate(hospitalId: Long, date: String) = Action {
    Ok(hospitalTimeFacade.findByDate(hospitalId, date).toText)
  }

}
