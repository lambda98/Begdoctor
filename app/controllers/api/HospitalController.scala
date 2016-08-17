package controllers.api

import javax.inject._

import facades.HospitalFacade

import play.api.mvc._

/**
  * Created by champillon onZ 6/12/16
  */
@Singleton
class HospitalController @Inject()(hospitalFacade: HospitalFacade)
  extends Controller {

  def getById(Id: Long) = Action {
    Ok(hospitalFacade.findById(Id).toText)
  }

  def getList = Action {
    Ok(hospitalFacade.listAll.toText)
  }

  def getByLocation(location: String) = Action {
    val lat_long  = location.split(",")
    val latitude  = lat_long(0).toFloat
    val longitude = lat_long(1).toFloat
    Ok(hospitalFacade.listByLocation(latitude, longitude).toText)
  }

}
