package persists.postgres

import java.sql.{ResultSet, Timestamp}

import javax.inject.{Inject, Singleton}
import entities.BookingEntity
import org.joda.time.DateTime
import persists.BookingPersist
import play.api.db.Database

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingPostgres @Inject() (db: Database)
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
                             , hospitalTimeId: Long): Boolean = db.withConnection { implicit conn =>

    val preparedStatement = conn.prepareStatement(INSERT)
    preparedStatement.setLong(1, id)
    preparedStatement.setLong(2, userId)
    preparedStatement.setLong(3, hospitalTimeId)
    preparedStatement.setTimestamp(4, new Timestamp(new DateTime().getMillis))
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private [postgres] def parse(resultSet: ResultSet): BookingEntity = BookingEntity(
    id = resultSet.getLong("id")
    , userId = resultSet.getLong("userId")
    , hospitalTimeId = resultSet.getLong("hospitalTimeId")
    , created =  new DateTime(resultSet.getTimestamp("created").getTime)
  )

  private val SELECT_ALL = "SELECT * FROM booking"
  private val SELECT_BY_ID = "SELECT * FROM booking where id = ?"
  private val SELECT_BY_USER_ID = "SELECT * FROM booking where userId = ?"
  private val INSERT = "INSERT INTO booking (id, userId, hospitalTimeId, created) VALUES (?, ?, ?, ?)"
}
