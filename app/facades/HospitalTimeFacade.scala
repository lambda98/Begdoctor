package facades

import javax.inject.{Inject, Singleton}

import models.HospitalTime
import persists.HospitalTimePersist

/**
  * Created by Siam yimyam on 13/7/2559.
  */
@Singleton
class HospitalTimeFacade @Inject()(persist: HospitalTimePersist) {

  def findById(id: Long): HospitalTime = {
    persist.selectById(id).get
  }
}
