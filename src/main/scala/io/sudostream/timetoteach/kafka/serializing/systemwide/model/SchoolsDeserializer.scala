package io.sudostream.timetoteach.kafka.serializing.systemwide.model

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.Schools
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class SchoolsDeserializer extends Deserializer[Schools] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): Schools = {
    val reader = new SpecificDatumReader[Schools](Schools.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new Schools()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}
