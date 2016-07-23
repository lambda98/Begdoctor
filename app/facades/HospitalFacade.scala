package facades

import javax.inject.{Singleton, Inject}

import models.Hospital
import persists.HospitalPersist

/**
  * Created by champillon on 6/12/16.
  */
@Singleton
class HospitalFacade @Inject()(persist: HospitalPersist) {

  def findById(id: Long): Hospital = {
    persist.selectById(id).get
  }

}
