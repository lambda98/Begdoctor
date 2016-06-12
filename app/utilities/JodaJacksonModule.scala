package utilities

import com.fasterxml.jackson.core.{JsonGenerator, JsonParser}
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer, JsonSerializer, SerializerProvider}
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
  * Created by champillon on 6/12/16.
  * refer to norbor #B
  */
object JodaJacksonModule
  extends SimpleModule {

  private val format = "yyyy-MM-dd HH:mm:ss"
  private val dtf = DateTimeFormat.forPattern(format)

  addDeserializer(classOf[DateTime], new JsonDeserializer[DateTime] {
    override def deserialize(p: JsonParser, ctxt: DeserializationContext): DateTime = {
      dtf.parseDateTime(p.getText)
    }
  })

  addSerializer(classOf[DateTime], new JsonSerializer[DateTime] {
    override def serialize(value: DateTime, gen: JsonGenerator, serializers: SerializerProvider): Unit = {
      gen.writeString(dtf.print(value))
    }
  })
}
