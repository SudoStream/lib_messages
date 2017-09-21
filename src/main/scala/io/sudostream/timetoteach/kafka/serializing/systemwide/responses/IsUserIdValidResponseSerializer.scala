package io.sudostream.timetoteach.kafka.serializing.systemwide.responses

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.systemwide.responses.IsUserIdValidResponse
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class IsUserIdValidResponseSerializer extends Serializer[IsUserIdValidResponse] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: IsUserIdValidResponse): Array[Byte] = {
    val writer: DatumWriter[IsUserIdValidResponse] =
      new SpecificDatumWriter[IsUserIdValidResponse](IsUserIdValidResponse.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}

}
