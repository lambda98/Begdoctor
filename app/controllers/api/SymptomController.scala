package controllers.api

import com.google.inject.{Inject, Singleton}
import facades.SymptomFacade
import play.api.mvc._

/**
  * Created by Siam yimyam on 8/7/2559.
  */
@Singleton
class SymptomController @Inject() (symptomFacade: SymptomFacade)
  extends Controller {

  def getList = Action {
    Ok(symptomFacade.listAll.toText)
  }

}
