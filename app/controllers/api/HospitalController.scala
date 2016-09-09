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

  def getByName(name: String) = Action {
    Ok(hospitalFacade.findByName(name).toText)
  }

  def getByLocation(location: String) = Action {
    Ok(hospitalFacade.listByLocation(
      latitude = split(location, LATITUDE)
      , longitude = split(location, LONGITUDE)
    ).toText)
  }

  def getList = Action {
    Ok(hospitalFacade.listAll.toText)
  }

  private def split(location: String, locationType: Int): Float = {
    val splittedLocation = location.split(",")

    locationType match {
      case LATITUDE => splittedLocation(LATITUDE).toFloat
      case LONGITUDE => splittedLocation(LONGITUDE).toFloat
    }
  }

  private val LATITUDE = 0
  private val LONGITUDE = 1
}