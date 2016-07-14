package persists.postgres

import java.sql.ResultSet

import com.google.inject.Inject
import entities.BookingEntity
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

  private [postgres] def parse(resultSet: ResultSet): BookingEntity = BookingEntity(
    id = resultSet.getLong("id")
    , user_id = resultSet.getLong("user_id")
    , hospital_time_id = resultSet.getLong("hospital_time_id")
  )

  private val LIST_ALL_BOOKING = "SELECT * FROM booking"
}
