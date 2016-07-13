package persists.postgres

import java.sql.ResultSet
import javax.inject.{Inject, Singleton}

import models.Hospital_Time
import persists.Hospital_TimePersist
import play.api.db.Database

/**
  * Created by Administrator on 13/7/2559.
  */
class Hospital_TimePostgres @Inject() (db: Database)
  extends Hospital_TimePersist {

  override def findById(id: Long): Option[Hospital_Time] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(FIND_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  private[postgres] def parse(resultSet: ResultSet): Hospital_Time = Hospital_time(
    id = resultSet.getLong("id")
    , hospital_id = resultSet.getLong("hospital_id")
    , start_datetime = resultSet.getString("2016-07-01 10:30")
    , finish_datetime = resultSet.getString("2016-07-02 11:30")
    , vailable = resultSet.getString("true")
  )

  private val FIND_BY_ID = "SELECT * FROM hospitals_time where id = ?"
}
