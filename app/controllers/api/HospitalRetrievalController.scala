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
          latitude =  13.7479752f
          , longitude = 100.5836296f
          , name =  "โรงพยาบาลกรุงเทพ"
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable =>
          Ok("500") as "application/json"
      }
    )
  }

  def update() = Action { implicit request =>
    UpdateHospitalRetrievalForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        hospitalRetrievalFacade.update(
          latitude = 13.735052f
          , longitude = 100.576692f
          , name = "โรงพยาบาลสมิติเวช สุขุมวิท"
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable =>
          Ok("500") as "application/json"
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
