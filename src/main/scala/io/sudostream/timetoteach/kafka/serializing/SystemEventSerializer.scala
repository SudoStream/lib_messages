package io.sudostream.timetoteach.kafka.serializing

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.events.SystemEvent
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class SystemEventSerializer extends Serializer[SystemEvent] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: SystemEvent): Array[Byte] = {
    val writer: DatumWriter[SystemEvent] =
      new SpecificDatumWriter[SystemEvent](SystemEvent.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}

}

