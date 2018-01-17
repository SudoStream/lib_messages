package io.sudostream.timetoteach.kafka.serializing.systemwide.classes

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.classes.ClassDetails
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class ClassDetailsDeserializer extends Deserializer[ClassDetails] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): ClassDetails = {
    val reader = new SpecificDatumReader[ClassDetails](ClassDetails.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new ClassDetails()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}
