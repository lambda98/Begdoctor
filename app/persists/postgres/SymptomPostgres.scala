package persists.postgres

import java.sql.ResultSet

import com.google.inject.Inject
import entities.SymptomEntity
import persists.SymptomPersist
import play.api.db.Database

/**
  * Created by Siam yimyam on 8/7/2559.
  */
class SymptomPostgres @Inject() (db: Database)
  extends SymptomPersist {

  override def selectAll(): List[SymptomEntity] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(SELECT_ALL)

    val resultSet = preparedStatement.executeQuery
    new Iterator[SymptomEntity] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet)
    }.toList
  }
  private [postgres] def parse(resultSet: ResultSet): SymptomEntity = SymptomEntity(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
  )

  private val SELECT_ALL = "SELECT * FROM symptoms"
}
