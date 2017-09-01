package io.sudostream.zzz_old_project_for_reference.api_antagonist.kafka.serialising

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.zzz_old_project_for_reference.api_antagonist.messages.LiveActedLine
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class LiveActedLineDeserialiser extends Deserializer[LiveActedLine] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): LiveActedLine = {
    val reader = new SpecificDatumReader[LiveActedLine](LiveActedLine.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new LiveActedLine()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}
}
