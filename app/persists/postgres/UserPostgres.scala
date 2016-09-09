package persists.postgres

import java.sql.{ResultSet, Timestamp}
import javax.inject.{Inject, Singleton}

import entities.UserEntity
import org.joda.time.DateTime
import persists.UserPersist
import play.api.db.Database

/**
  * Created by anawin on 6/23/2016 AD.
  */
class UserPostgres @Inject()(db: Database)
  extends UserPersist {

  override def selectById(id: Long): Option[UserEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def selectByEmail(email: String): Option[UserEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_EMAIL)
    preparedStatement.setString(1, email)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def insert(id: Long
                      , name: String
                      , surname: String
                      , email: String
                      , mobile: String): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(INSERT)
    preparedStatement.setLong(1, id)
    preparedStatement.setString(2, name)
    preparedStatement.setString(3, surname)
    preparedStatement.setString(4, email)
    preparedStatement.setString(5, mobile)
    preparedStatement.setTimestamp(6, new Timestamp(new DateTime().getMillis))
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private[postgres] def parse(resultSet: ResultSet): UserEntity = UserEntity(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
    , surname = resultSet.getString("surname")
    , email = resultSet.getString("email")
    , avatar = resultSet.getString("avatar")
    , mobile = resultSet.getString("mobile")
    , created = new DateTime(resultSet.getTimestamp("created").getTime)
  )

  private val SELECT_BY_ID = "SELECT * FROM users where id = ?"
  private val SELECT_BY_EMAIL = "SELECT * FROM users where email = ?"
  private val INSERT = "INSERT INTO users (id, name, surname, email, mobile, created) VALUES (?, ?, ?, ?, ?, ?)"

}
