package persists.postgres

import java.sql.ResultSet
import javax.inject.Inject

import entities.HospitalTimeEntity
import models.HospitalTime
import persists.HospitalTimePersist
import play.api.db.Database
import org.joda.time.DateTime

/**
  * Created by Administrator on 13/7/2559.
  */
class HospitalTimePostgres @Inject()(db: Database)
  extends HospitalTimePersist {

  override def selectById(id: Long): List[HospitalTimeEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    new Iterator[HospitalTimeEntity] {
      override def hasNext = resultSet.next()
      override def next() = parseList(resultSet)
    }.toList
  }

  private[postgres] def parseList(resultSet: ResultSet): HospitalTimeEntity = HospitalTimeEntity(
    id = resultSet.getLong("id")
    , hospitalId = resultSet.getLong("hospital_id")
    , startDateTime = new DateTime(resultSet.getTimestamp("start_datetime").getTime)
    , finishDateTime = new DateTime(resultSet.getTimestamp("finish_datetime").getTime)
    , available = resultSet.getBoolean("available")
  )

  private val SELECT_BY_ID = "SELECT * FROM hospitals_time where id = ?"
}
