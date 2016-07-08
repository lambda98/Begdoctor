package facades

import javax.inject.{Inject, Singleton}

import models.{Symptom, SymptomList}
import persists.SymptomPersist

/**
  * Created by Siam yimyam on 8/7/2559.
  */
@Singleton
class SymptomFacade @Inject()(persist: SymptomPersist) {

  def listAllSymptom: SymptomList = {
    SymptomList(persist.listAllSymptom)
  }
}
