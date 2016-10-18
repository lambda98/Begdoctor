package definitions

/**
  * Created by champillon on 10/18/16.
  */
object BookingStatus
  extends Enumeration {
  type BookingStatus = Value

  val confirmed = Value(0)
  val upcoming = Value(1)
  val delay = Value(2)
  val done = Value(3)

}
