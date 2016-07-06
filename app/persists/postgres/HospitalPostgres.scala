package persists.postgres

import java.sql.ResultSet
import javax.inject.{Inject, Singleton}

import models.Hospital
import persists.HospitalPersist
import play.api.db.Database

/**
  * Created by champillon on 6/13/16.
  */
class HospitalPostgres @Inject()(db: Database)
  extends HospitalPersist {

  override def findById(id: Long): Option[Hospital] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(FIND_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  private[postgres] def parse(resultSet: ResultSet): Hospital = Hospital(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
    , url = resultSet.getString("url")
    , location = resultSet.getString("location")
    , doctorName = resultSet.getString("doctorName")
  )

  private val FIND_BY_ID = "SELECT * FROM hospitals where id = ?"

}
