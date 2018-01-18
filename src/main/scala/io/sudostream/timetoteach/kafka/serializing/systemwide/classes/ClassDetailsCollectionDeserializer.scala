package io.sudostream.timetoteach.kafka.serializing.systemwide.classes

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.classes.ClassDetailsCollection
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class ClassDetailsCollectionDeserializer extends Deserializer[ClassDetailsCollection] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): ClassDetailsCollection = {
    val reader = new SpecificDatumReader[ClassDetailsCollection](ClassDetailsCollection.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new ClassDetailsCollection()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}
