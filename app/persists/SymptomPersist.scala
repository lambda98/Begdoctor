package persists

import models.Symptom

/**
  * Created by Siam yimyam on 8/7/2559.
  */
trait SymptomPersist {

  def listAllSymptom() : List[Symptom]

}
