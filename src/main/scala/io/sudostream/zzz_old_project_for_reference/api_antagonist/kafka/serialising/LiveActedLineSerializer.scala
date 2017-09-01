package io.sudostream.zzz_old_project_for_reference.api_antagonist.kafka.serialising

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.zzz_old_project_for_reference.api_antagonist.messages.LiveActedLine
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class LiveActedLineSerializer extends Serializer[LiveActedLine] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: LiveActedLine): Array[Byte] = {
    val writer: DatumWriter[LiveActedLine] =
      new SpecificDatumWriter[LiveActedLine](LiveActedLine.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}
}
