package persists.postgres

import java.sql.ResultSet

import com.google.inject.Inject
import models.Symptom
import persists.SymptomPersist
import play.api.db.Database

/**
  * Created by Siam yimyam on 8/7/2559.
  */
class SymptomPostgres @Inject() (db: Database)
  extends SymptomPersist {

  override def listAllSymptom(): List[Symptom] = db.withConnection { implicit conn =>
    val preparedStatement = conn.prepareStatement(LIST_ALL_SYMPTOM)


    val resultSet = preparedStatement.executeQuery
    new Iterator[Symptom] {
      override def hasNext = resultSet.next()
      override def next() = parse(resultSet )
    }.toList
  }
  private [postgres] def parse(resultSet: ResultSet): Symptom = Symptom(
    id = resultSet.getLong("id")
    , name = resultSet.getString("name")
  )

  private val LIST_ALL_SYMPTOM = "SELECT * FROM symptoms"
}
