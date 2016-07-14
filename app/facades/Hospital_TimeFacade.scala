package facades

import javax.inject.{Inject, Singleton}

import models.Hospital_Time
import persists.Hospital_TimePersist

/**
  * Created by Siam yimyam on 13/7/2559.
  */
@Singleton
class Hospital_TimeFacade @Inject()(persist: Hospital_TimePersist) {

  def findById(id: Long): Hospital_Time = {
    persist.findById(id).get
  }
}
