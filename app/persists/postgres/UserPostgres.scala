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

  private[postgres] def parse(resultSet: ResultSet): User = User(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
    , surname = resultSet.getString("surname")
    , email = resultSet.getString("email")
  )

  private val FIND_BY_ID = "SELECT * FROM users where id = ?"

}
