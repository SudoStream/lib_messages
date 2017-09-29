package io.sudostream.timetoteach.kafka.serializing.systemwide.model

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.Schools
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class SchoolsSerializer extends Serializer[Schools] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: Schools): Array[Byte] = {
    val writer: DatumWriter[Schools] =
      new SpecificDatumWriter[Schools](Schools.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    out.toByteArray
  }

  override def close(): Unit = {}

}
