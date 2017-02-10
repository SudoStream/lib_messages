package io.sudostream.api_antagonist.kafka.serialising

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.api_antagonist.messages.SpeculativeScreenplay
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class SpeculativeScreenplaySerializer extends Serializer[SpeculativeScreenplay] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: SpeculativeScreenplay): Array[Byte] = {
    val writer: DatumWriter[SpeculativeScreenplay] =
      new SpecificDatumWriter[SpeculativeScreenplay](SpeculativeScreenplay.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}
}
