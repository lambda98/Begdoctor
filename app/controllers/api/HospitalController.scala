package controllers.api

import javax.inject._

import facades.HospitalFacade
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
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

  def create() = Action { implicit request =>
    CreateHospitalForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        hospitalFacade.create(
          latitude = form.latitude
          , longitude = form.longitude
          , name = form.name
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }

  def getByName(name: String) = Action {
    Ok(hospitalFacade.findByName(name).toText)
  }

  def update() = Action { implicit request =>
    UpdateHospitalForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        hospitalFacade.update(
          latitude = form.latitude
          , longitude = form.longitude
          , name = form.name
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }
}

case class CreateHospitalForm(latitude: Float
                                       , longitude: Float
                                       , name: String)
object CreateHospitalForm {
  val form = Form(
    mapping(
      "latitude" -> of[Float],
      "longitude" -> of[Float],
      "name" -> of[String]
    ) (CreateHospitalForm.apply)(CreateHospitalForm.unapply)
  )
}

case class UpdateHospitalForm (latitude: Float
                                        , longitude: Float
                                        , name: String)
object UpdateHospitalForm {
  val form = Form(
    mapping(
      "latitude" -> of[Float],
      "longitude" -> of[Float],
      "name" -> of[String]
    ) (UpdateHospitalForm.apply) (UpdateHospitalForm.unapply)
  )
}

