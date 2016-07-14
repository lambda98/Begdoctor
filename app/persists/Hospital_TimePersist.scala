package persists

import models.Hospital_Time

/**
  * Created by Administrator on 13/7/2559.
  */
trait Hospital_TimePersist {

  def findById(id: Long): Option[Hospital_Time]

}
