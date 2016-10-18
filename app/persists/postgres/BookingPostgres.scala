package persists.postgres

import java.sql.{ResultSet, Timestamp}
import javax.inject.{Inject, Singleton}

import definitions.BookingStatus.BookingStatus
import entities.BookingEntity
import org.joda.time.DateTime
import persists.BookingPersist
import play.api.db.Database

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingPostgres @Inject()(db: Database)
  extends BookingPersist {

  override def selectAll(): List[BookingEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_ALL)

    val resultSet = preparedStatement.executeQuery
    new Iterator[BookingEntity] {
      override def hasNext = resultSet.next()

      override def next() = parse(resultSet)
    }.toList
  }

  override def selectById(id: Long): List[BookingEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    new Iterator[BookingEntity] {
      override def hasNext = resultSet.next()

      override def next() = parse(resultSet)
    }.toList
  }

  override def selectByUserId(userId: Long): List[BookingEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_USER_ID)
    preparedStatement.setLong(1, userId)

    val resultSet = preparedStatement.executeQuery
    new Iterator[BookingEntity] {
      override def hasNext = resultSet.next()

      override def next() = parse(resultSet)
    }.toList
  }

  override def insert(id: Long
                      , userId: Long
                      , hospitalTimeId: Long
                      , symptomId: Long
                      , status: BookingStatus): Boolean = db.withConnection { implicit conn =>

    val preparedStatement = conn.prepareStatement(INSERT)
    preparedStatement.setLong(1, id)
    preparedStatement.setLong(2, userId)
    preparedStatement.setLong(3, hospitalTimeId)
    preparedStatement.setLong(4, symptomId)
    preparedStatement.setInt(5, status.id)
    preparedStatement.setTimestamp(6, new Timestamp(new DateTime().getMillis))
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private[postgres] def parse(resultSet: ResultSet): BookingEntity = BookingEntity(
    id = resultSet.getLong("id")
    , userId = resultSet.getLong("user_id")
    , hospitalTimeId = resultSet.getLong("hospital_time_id")
    , symptomId = resultSet.getLong("symptom_id")
    , status = resultSet.getString("status")
    , created = new DateTime(resultSet.getTimestamp("created").getTime)
  )

  private val SELECT_ALL =
    """
      | SELECT * FROM booking ORDER BY created
    """.stripMargin

  private val SELECT_BY_ID =
    """
      | SELECT * FROM booking where id = ?
    """.stripMargin

  private val SELECT_BY_USER_ID =
    """
      | SELECT * FROM booking where user_id = ? ORDER BY created
    """.stripMargin

  private val INSERT =
    """
      | INSERT INTO booking (id, user_id, hospital_time_id, symptom_id, status, created)
      | VALUES (?, ?, ?, ?, ?, ?)
    """.stripMargin
}
