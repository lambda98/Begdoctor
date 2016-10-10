package persists.postgres

import java.sql.{ResultSet, Timestamp}
import javax.inject.Inject

import entities.HospitalTimeEntity
import utilities.DateConverter
import persists.HospitalTimePersist
import play.api.db.Database
import org.joda.time.DateTime

/**
  * Created by Administrator on 13/7/2559.
  */
class HospitalTimePostgres @Inject()(db: Database)
  extends HospitalTimePersist {

  override def selectById(id: Long): Option[HospitalTimeEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery

    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def selectByDate(hospitalId: Long
                            , fromDate: String
                            , tilDate: String): List[HospitalTimeEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_DATE)
    val startDateFrom = new Timestamp(DateConverter.stringToDate(fromDate).getMillis)
    val startDateTil = new Timestamp(DateConverter.stringToDate(tilDate).getMillis)
    preparedStatement.setLong(1, hospitalId)
    preparedStatement.setTimestamp(2, startDateFrom)
    preparedStatement.setTimestamp(3, startDateTil)

    val resultSet = preparedStatement.executeQuery

    new Iterator[HospitalTimeEntity] {
      override def hasNext = resultSet.next()

      override def next() = parse(resultSet)
    }.toList
  }

  private[postgres] def parse(resultSet: ResultSet): HospitalTimeEntity = HospitalTimeEntity(
    id = resultSet.getLong("id")
    , hospitalId = resultSet.getLong("hospital_id")
    , startDateTime = new DateTime(resultSet.getTimestamp("start_datetime").getTime)
    , finishDateTime = new DateTime(resultSet.getTimestamp("finish_datetime").getTime)
    , available = resultSet.getBoolean("available")
  )

  private val SELECT_BY_ID =
    """
      |SELECT * FROM hospitals_time where id = ?
    """.stripMargin

  private val SELECT_BY_DATE =
    """
      | SELECT * FROM hospitals_time
      | WHERE hospital_id = ? AND start_datetime >= ? AND start_datetime < ?
      | ORDER BY start_datetime ASC
    """.stripMargin
}
