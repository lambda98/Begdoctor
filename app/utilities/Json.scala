package utilities

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

import scala.util.Try

/**
  * Created by champillon on 6/12/16.
  * refer to norbor #B
  */
trait Json {

  import Json._

  def toText: String = mapper.writeValueAsString(this)
}

object Json {

  private val mapper = new ObjectMapper with ScalaObjectMapper
  mapper.registerModule(JodaJacksonModule)
  mapper.registerModule(DefaultScalaModule)
  mapper.setSerializationInclusion(Include.NON_NULL)

  def toJson(obj: AnyRef): String = Try(mapper.writeValueAsString(obj)).getOrElse("")

  def toObject[T: Manifest](content: String): Option[T] = Try(Some(mapper.readValue[T](content))).getOrElse(None)

}