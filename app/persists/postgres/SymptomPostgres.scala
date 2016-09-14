package persists.postgres

import java.sql.ResultSet

import com.google.inject.Inject
import entities.{StaffEntity, SymptomEntity}
import persists.SymptomPersist
import play.api.db.Database

/**
  * Created by Siam yimyam on 8/7/2559.
  */
class SymptomPostgres @Inject()(db: Database)
  extends SymptomPersist {

  override def selectAll(): List[SymptomEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_ALL)

    val resultSet = preparedStatement.executeQuery
    new Iterator[SymptomEntity] {
      override def hasNext = resultSet.next()

      override def next() = parse(resultSet)
    }.toList
  }

  override def selectById(sypmtomId: Long): Option[SymptomEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_BY_ID)
    preparedStatement.setLong(1, sypmtomId)

    val resultSet = preparedStatement.executeQuery

    resultSet.next match {
      case true => Some(parse(resultSet))
      case false => None
    }
  }

  private[postgres] def parse(resultSet: ResultSet): SymptomEntity = SymptomEntity(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
  )

  private val SELECT_ALL = "SELECT * FROM symptoms"

  private val SELECT_BY_ID = "SELECT * FROM symptoms WHERE id = ?"
}
