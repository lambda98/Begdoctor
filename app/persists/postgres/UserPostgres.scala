package persists.postgres

import java.sql.ResultSet
import javax.inject.{Inject, Singleton}

import models.User
import persists.UserPersist
import play.api.db.Database

/**
  * Created by anawin on 6/23/2016 AD.
  */
class UserPostgres @Inject()(db: Database)
  extends UserPersist{

  override def findById(id: Long): Option[User] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(FIND_BY_ID)
    preparedStatement.setLong(1, id)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def findByEmail(email: String): Option[User] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(FIND_BY_EMAIL)
    preparedStatement.setString(1, email)

    val resultSet = preparedStatement.executeQuery
    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  override def insertUser(name: String, surname: String, email: String): Boolean = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(INSERT_USER_DATA)
    preparedStatement.setString(1, name)
    preparedStatement.setString(2, surname)
    preparedStatement.setString(3, email)
    preparedStatement.executeUpdate() match {
      case 1 => true
      case _ => false
    }
  }

  private[postgres] def parse(resultSet: ResultSet): User = User(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
    , surname = resultSet.getString("surname")
    , email = resultSet.getString("email")
  )

  private val FIND_BY_ID = "SELECT * FROM users where id = ?"
  private val FIND_BY_EMAIL = "SELECT * FROM users where email = ?"
  private val INSERT_USER_DATA = "INSERT INTO users (name, surname, email) VALUES (?, ?, ?)"

}
