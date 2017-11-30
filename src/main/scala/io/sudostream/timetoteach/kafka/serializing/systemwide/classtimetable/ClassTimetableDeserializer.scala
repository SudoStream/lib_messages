package io.sudostream.timetoteach.kafka.serializing.systemwide.classtimetable

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.ClassTimetable
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class ClassTimetableDeserializer extends Deserializer[ClassTimetable] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): ClassTimetable = {
    val reader = new SpecificDatumReader[ClassTimetable](ClassTimetable.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new ClassTimetable()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}
