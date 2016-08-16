package controllers.api

import javax.inject.{Inject, Singleton}

import facades.HospitalRetrievalFacade
import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
  * Created by Siam yimyam on 8/8/2559.
  */
@Singleton
class HospitalRetrievalController @Inject() (hospitalRetrievalFacade: HospitalRetrievalFacade)
  extends Controller {

  def getById(id: Long) = Action {
    Ok(hospitalRetrievalFacade.findById(id).toText)
  }

  def create() = Action { implicit request =>
    CreateHospitalRetrievalForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        hospitalRetrievalFacade.create(
          latitude = form.latitude
          , longitude = form.longitude
          , name = form.name
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable =>
          println("Pokemon"+t.printStackTrace())
          Ok("500")
      }
    )
  }

  def getByName(name: String) = Action {
    Ok(hospitalRetrievalFacade.findByName(name).toText)
  }

  def update() = Action { implicit request =>
    UpdateHospitalRetrievalForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        hospitalRetrievalFacade.update(
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

case class CreateHospitalRetrievalForm(latitude: Float
                                       , longitude: Float
                                       , name: String)
object CreateHospitalRetrievalForm {
  val form = Form(
    mapping(
      "latitude" -> of[Float],
      "longitude" -> of[Float],
      "name" -> of[String]
    ) (CreateHospitalRetrievalForm.apply)(CreateHospitalRetrievalForm.unapply)
  )
}

case class UpdateHospitalRetrievalForm (latitude: Float
                                        , longitude: Float
                                        , name: String)
object UpdateHospitalRetrievalForm {
  val form = Form(
    mapping(
      "latitude" -> of[Float],
      "longitude" -> of[Float],
      "name" -> of[String]
    ) (UpdateHospitalRetrievalForm.apply) (UpdateHospitalRetrievalForm.unapply)
  )
}
