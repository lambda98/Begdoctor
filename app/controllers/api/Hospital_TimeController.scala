package controllers.api

import com.google.inject.{Inject, Singleton}
import facades.Hospital_TimeFacade
import play.api.mvc.{Action, Controller}

/**
  * Created by Administrator on 13/7/2559.
  */
@Singleton
class Hospital_TimeController @Inject() (hospital_timeFacade: Hospital_TimeFacade)
  extends Controller {

  def getHospital_time(Id: Long) = Action {
    Ok(hospital_timeFacade.findById(Id).toText)
  }

}
