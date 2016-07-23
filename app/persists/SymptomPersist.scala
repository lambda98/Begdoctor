package persists

import entities.SymptomEntity

/**
  * Created by Siam yimyam on 8/7/2559.
  */
trait SymptomPersist {

  def selectAll() : List[SymptomEntity]
}
