package io.sudostream.api_event_horizon.kafka.serialising

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.api_event_horizon.messages.SpeculativeScreenPlay
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class SpeculativeScreenPlaySerializer extends Serializer[SpeculativeScreenPlay] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: SpeculativeScreenPlay): Array[Byte] = {
    val writer: DatumWriter[SpeculativeScreenPlay] =
      new SpecificDatumWriter[SpeculativeScreenPlay](SpeculativeScreenPlay.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}
}