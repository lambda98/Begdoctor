package actors

import akka.actor.Actor
import com.typesafe.scalalogging.LazyLogging
import services.UuidService

/**
  * Created by champillon on 7/12/16.
  * refer to norbor #B
  */
case class RandomUuidActor()
  extends Actor {

  override def receive = {
    case service: UuidService => {
      service.create
    }
  }

}