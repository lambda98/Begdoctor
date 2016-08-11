package persists.postgres

import java.sql.ResultSet
import javax.inject.{Inject, Singleton}

import models.HospitalRetrieval
import persists.HospitalRetrievalPersist
import play.api.db.Database

/**
  * Created by Siam yimyam on 9/8/2559.
  */
class HospitalRetrievalPostgres @Inject() (db: Database)
  extends HospitalRetrievalPersist{

  override def insert(lat: String
                     , lng: String
                     , name: String): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(INSERT)
    preparedStatement.setString(1,lat)
    preparedStatement.setString(2,lng)
    preparedStatement.setString(3,name)
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private [postgres] def parse(resultSet: ResultSet): HospitalRetrieval = HospitalRetrieval(
    lat = resultSet.getString("lat")
    , lng = resultSet.getString("lng")
    , name = resultSet.getString("name")
  )

  private val INSERT = "INSERT INTO hospitals (lat, lng, name) VALUES (?, ?, ?)"
}
