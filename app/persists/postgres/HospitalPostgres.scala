package persists.postgres

import java.sql.ResultSet
import javax.inject.{Inject, Singleton}

import entities.HospitalEntity
import models.Hospital
import persists.HospitalPersist
import play.api.db.Database

/**
  * Created by champillon on 6/13/16.
  */
class HospitalPostgres @Inject()(db: Database)
  extends HospitalPersist {

  override def selectById(id: Long): Option[Hospital] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def selectAll():  List[HospitalEntity] = db.withConnection { implicit conn =>

    val preparedStatement = conn.prepareStatement(SELECT_ALL)

    val resultSet = preparedStatement.executeQuery
    new Iterator[HospitalEntity] {
      override def hasNext = resultSet.next()
      override def next() = parseList(resultSet)
    }.toList
  }

  override def selectByLocation(latitude: Float
                                , longitude: Float): List[HospitalEntity] = db.withConnection { implicit conn =>

    val preparedStatement = conn.prepareStatement(SELECT_BY_LOCATION)
    preparedStatement.setFloat(1, latitude)
    preparedStatement.setFloat(2, longitude)

    val resultSet = preparedStatement.executeQuery
    new Iterator[HospitalEntity] {
      override def hasNext = resultSet.next()
      override def next() = parseList(resultSet)
    }.toList
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

  override def selectByName(name: String): Option[Hospital] = db.withConnection { implicit conn =>
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

  private[postgres] def parseList(resultSet: ResultSet): HospitalEntity = HospitalEntity(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
    , url = resultSet.getString("url")
    , doctorName = resultSet.getString("doctor_name")
    , latitude = resultSet.getFloat("latitude")
    , longitude = resultSet.getFloat("longitude")
    , availableTime = resultSet.getString("available_time")
  )

  private[postgres] def parse(resultSet: ResultSet): Hospital = Hospital(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
    , url = resultSet.getString("url")
    , doctorName = resultSet.getString("doctor_name")
    , latitude = resultSet.getFloat("latitude")
    , longitude = resultSet.getFloat("longitude")
    , availableTime = resultSet.getString("available_time")
  )

  private val SELECT_BY_ID = "SELECT * FROM hospitals where id = ?"

  private val SELECT_ALL = "SELECT * FROM hospitals"

  private val SELECT_BY_LOCATION = "SELECT * " +
    "                                      , round((point(latitude, longitude) <@> point(? , ?))::numeric, 2) as miles" +
    "                               FROM hospitals " +
    "                               ORDER by miles ASC" +
    "                               limit 3;"

  private val INSERT = "INSERT INTO hospitals (id, latitude, longitude, name) VALUES (?, ?, ?, ?)"

  private val UPDATE = "UPDATE hospitals SET latitude = ?, longitude = ? where name = ?"

  private val SELECT_BY_NAME = "SELECT * FROM hospitals where name = ?"

}