package services

import java.util.UUID
import javax.inject.{Inject, Singleton}

import actors.RandomUuidActor
import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinPool
import play.api.Configuration

import scala.collection.mutable

/**
  * Created by champillon on 7/12/16.
  * refer to norbor #B
  */
@Singleton
class UuidService @Inject()(system: ActorSystem
                            , config: Configuration) {

  private val maxPool = config.getInt("actor.randomuuidactor.pool").getOrElse(16)

  private val actor = system.actorOf(
    RoundRobinPool(maxPool).props(Props[RandomUuidActor]), "randomUuidActor")

  private val stack = mutable.Stack[UUID]()

  List.range(1, maxPool).par.foreach(i => create)

  def get: UUID = try {
    actor ! this
    stack.pop()
  } catch {
    case e: NoSuchElementException => UUID.randomUUID()
  }

  def getId = Math.abs(get.getLeastSignificantBits)

  def create = stack.push(UUID.randomUUID())

  def size = stack.size

}