package io.sudostream.timetoteach.kafka.serializing

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.ScottishEsAndOsDocument
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class ScottishEsAndOsSerializer extends Serializer[ScottishEsAndOsDocument] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: ScottishEsAndOsDocument): Array[Byte] = {
    val writer: DatumWriter[ScottishEsAndOsDocument] =
      new SpecificDatumWriter[ScottishEsAndOsDocument](ScottishEsAndOsDocument.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}

}
