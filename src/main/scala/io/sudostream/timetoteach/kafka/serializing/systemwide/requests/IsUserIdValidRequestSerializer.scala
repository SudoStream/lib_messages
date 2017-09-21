package io.sudostream.timetoteach.kafka.serializing.systemwide.requests

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.systemwide.requests.IsUserIdValidRequest
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class IsUserIdValidRequestSerializer extends Serializer[IsUserIdValidRequest] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: IsUserIdValidRequest): Array[Byte] = {
    val writer: DatumWriter[IsUserIdValidRequest] =
      new SpecificDatumWriter[IsUserIdValidRequest](IsUserIdValidRequest.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}

}
