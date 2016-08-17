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

  override def selectById(id: Long): Option[HospitalRetrieval] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def insert(id: Long
                      , latitude: Float
                      , longitude: Float
                      , name: String): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(INSERT)
    preparedStatement.setLong(1, id)
    preparedStatement.setFloat(2, latitude)
    preparedStatement.setFloat(3, longitude)
    preparedStatement.setString(4, name)
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  override def selectByName(name: String): Option[HospitalRetrieval] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_NAME)
    preparedStatement.setString(1, name)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def update(id: Long
                      , latitude: Float
                      , longitude: Float
                      , name: String): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(UPDATE)
    preparedStatement.setFloat(1, latitude)
    preparedStatement.setFloat(2, longitude)
    preparedStatement.setString(3, name)
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private [postgres] def parse(resultSet: ResultSet): HospitalRetrieval = HospitalRetrieval(
    id = resultSet.getLong("id")
    ,latitude = resultSet.getFloat("latitude")
    , longitude = resultSet.getFloat("longitude")
    , name = resultSet.getString("name")
  )

  private val SELECT_BY_ID = "SELECT * FROM hospitals where id = ?"

  private val INSERT = "INSERT INTO hospitals (id, latitude, longitude, name) VALUES (?, ?, ?, ?)"

  private val UPDATE = "UPDATE hospitals SET latitude = ?, longitude = ? where name = ?"

  private val SELECT_BY_NAME = "SELECT * FROM hospitals where name = ?"

}
