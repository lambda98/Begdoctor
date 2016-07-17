package persists.postgres

import java.sql.{ResultSet, Timestamp}

import com.google.inject.Inject
import entities.BookingEntity
import org.joda.time.DateTime
import persists.BookingPersist
import play.api.db.Database

/**
  * Created by anawin on 7/14/2016 AD.
  */
class BookingPostgres @Inject() (db: Database)
  extends BookingPersist {

  override def listAllBooking(): List[BookingEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(LIST_ALL_BOOKING)

    val resultSet = preparedStatement.executeQuery
    new Iterator[BookingEntity] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet)
    }.toList
  }

  override def listBookingById(id: Long): List[BookingEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(FIND_BOOKING_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    new Iterator[BookingEntity] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet)
    }.toList
  }

  override def listBookingByUserId(user_id: Long): List[BookingEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(FIND_BOOKING_BY_USER_ID)
    preparedStatement.setLong(1, user_id)

    val resultSet = preparedStatement.executeQuery
    new Iterator[BookingEntity] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet)
    }.toList
  }

  override def insertBooking(id: Long
                             , user_id: Long
                             , hospital_time_id: Long): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(INSERT_BOOKING_DATA)
    preparedStatement.setLong(1, id)
    preparedStatement.setLong(2, user_id)
    preparedStatement.setLong(3, hospital_time_id)
    preparedStatement.setTimestamp(4, new Timestamp(new DateTime().getMillis))
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private [postgres] def parse(resultSet: ResultSet): BookingEntity = BookingEntity(
    id = resultSet.getLong("id")
    , user_id = resultSet.getLong("user_id")
    , hospital_time_id = resultSet.getLong("hospital_time_id")
    , created_at =  new DateTime(resultSet.getTimestamp("created_at").getTime)
  )

  private val LIST_ALL_BOOKING = "SELECT * FROM booking"
  private val FIND_BOOKING_BY_ID = "SELECT * FROM booking where id = ?"
  private val FIND_BOOKING_BY_USER_ID = "SELECT * FROM booking where user_id = ?"
  private val INSERT_BOOKING_DATA = "INSERT INTO booking (id, user_id, hospital_time_id, created_at) VALUES (?, ?, ?, ?)"
}
