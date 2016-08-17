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

  override def selectById(id: Long): List[HospitalEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    new Iterator[HospitalEntity] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet)
    }.toList
  }

  override def selectAll():  List[HospitalEntity] = db.withConnection { implicit conn =>

    val preparedStatement = conn.prepareStatement(SELECT_ALL)

    val resultSet = preparedStatement.executeQuery
    new Iterator[HospitalEntity] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet)
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
      override def next() = parse(resultSet)
    }.toList

  }

  private[postgres] def parse(resultSet: ResultSet): HospitalEntity = HospitalEntity(
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

}