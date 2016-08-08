package persists.postgres

import java.sql.{ResultSet, Timestamp}
import javax.inject.{Inject, Singleton}

import models.Staff
import org.joda.time.DateTime
import persists.StaffPersist
import play.api.db.Database
import com.github.t3hnar.bcrypt._
import entities.StaffEntity

/**
  * Created by anawin on 8/6/2016 AD.
  */
class StaffPostgres @Inject()(db: Database)
  extends StaffPersist{

  override def selectByUserName(username: String):  List[StaffEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_USERNAME)
    preparedStatement.setString(1, username)

    val resultSet = preparedStatement.executeQuery
    new Iterator[StaffEntity] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet)
    }.toList
  }

  override def check(username: String, password: String): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(LOGIN)
    val bcryptPassword = password.bcrypt
    preparedStatement.setString(1, username)
    preparedStatement.setString(2, bcryptPassword)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => true
      case false => false
    }
  }

  override def insert(id: Long
                      , name: String
                      , surname: String
                      , email: String
                      , username: String
                      , password: String
                      , hospitalId: Long): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(INSERT)
    val bcryptPassword = password.bcrypt
    preparedStatement.setLong(1, id)
    preparedStatement.setString(2, name)
    preparedStatement.setString(3, surname)
    preparedStatement.setString(4, email)
    preparedStatement.setString(5, username)
    preparedStatement.setString(6, bcryptPassword)
    preparedStatement.setLong(7, hospitalId)
    preparedStatement.setTimestamp(8, new Timestamp(new DateTime().getMillis))
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private[postgres] def parse(resultSet: ResultSet): StaffEntity = StaffEntity(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
    , surname = resultSet.getString("surname")
    , email = resultSet.getString("email")
    , username = resultSet.getString("username")
    , password = resultSet.getString("password")
    , hospitalId = resultSet.getLong("hospital_id")
    , created = new DateTime(resultSet.getTimestamp("created").getTime)
  )

  private val SELECT_BY_USERNAME = "SELECT * FROM staffs where username = ?"
  private val LOGIN = "SELECT * FROM staffs where username = ? AND password = ?"
  private val INSERT = "INSERT INTO staffs (id" +
                                            ", name" +
                                            ", surname" +
                                            ", email" +
                                            ", username" +
                                            ", password" +
                                            ", hospital_id" +
                                            ", created) " +
                                            "  VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

}