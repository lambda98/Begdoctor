package persists

import models.HospitalTime

/**
  * Created by Administrator on 13/7/2559.
  */
trait HospitalTimePersist {

  def selectById(id: Long): Option[HospitalTime]

}
