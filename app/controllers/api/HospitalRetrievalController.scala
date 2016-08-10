package controllers.api

import javax.inject.{Inject, Singleton}

import facades.HospitalRetrievalFacade
import play.api.mvc.{Action, Controller}
import google.HttpJsonPostTestClass
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
  * Created by Siam yimyam on 8/8/2559.
  */
@Singleton
class HospitalRetrievalController @Inject() (hospitalRetrievalFacade: HospitalRetrievalFacade, googleJson: HttpJsonPostTestClass)
  extends Controller {

  val stringJsonData = googleJson.jsonData
  def create() = Action { implicit request =>
    CreateHospitalRetrievalForm.form.bindFromRequest.fold(
      formWithErrors => Ok("400")
      , form => try {
        hospitalRetrievalFacade.create(
          lat =  "13.7479752"
          , lng = "100.5836296"
          , name =  "โรงพยาบาลกรุงเทพ"
        )
        Ok("200") as "application/json"
      } catch {
        case t: Throwable => Ok("500")
      }
    )
  }
}

case class CreateHospitalRetrievalForm(lat: String
                                        , lng: String
                                        , name: String)
object CreateHospitalRetrievalForm {
  val form = Form(
    mapping(
      "lat" -> of[String],
      "lng" -> of[String],
      "name" -> of[String]
    ) (CreateHospitalRetrievalForm.apply)(CreateHospitalRetrievalForm.unapply)
  )
}
