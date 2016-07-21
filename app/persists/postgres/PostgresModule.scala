package persists.postgres

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import persists._

/**
  * Created by champillon on 6/13/16.
  */
case class PostgresModule()
  extends AbstractModule
    with ScalaModule {

  protected def configure() {
    bind[HospitalPersist].to[HospitalPostgres]
    bind[UserPersist].to[UserPostgres]
    bind[BookingPersist].to[BookingPostgres]
    bind[SymptomPersist].to[SymptomPostgres]
    bind[HospitalTimePersist].to[HospitalTimePostgres]
  }
}
