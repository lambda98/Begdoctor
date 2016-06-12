package facades

import javax.inject.Singleton

import models.Hospital

/**
  * Created by champillon on 6/12/16.
  */
@Singleton
class HospitalFacade {

  def findById(id: Long):Hospital = {
    new Hospital(id, "test hospital name")
  }

}
