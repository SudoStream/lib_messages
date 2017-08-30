package io.sudostream.timetoteach.kafka.serializing

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.ScottishEsAndOs
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class ScottishEsAndOsSerializer extends Serializer[ScottishEsAndOs] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: ScottishEsAndOs): Array[Byte] = {
    val writer: DatumWriter[ScottishEsAndOs] =
      new SpecificDatumWriter[ScottishEsAndOs](ScottishEsAndOs.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}

}
