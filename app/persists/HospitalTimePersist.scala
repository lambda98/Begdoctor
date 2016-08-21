package persists

import models.HospitalTime
import entities.HospitalTimeEntity

/**
  * Created by Administrator on 13/7/2559.
  */
trait HospitalTimePersist {

  def selectById(id: Long): Option[HospitalTimeEntity]

}
