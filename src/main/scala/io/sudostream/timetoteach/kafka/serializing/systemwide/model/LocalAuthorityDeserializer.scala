package io.sudostream.timetoteach.kafka.serializing.systemwide.model

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.LocalAuthority
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class LocalAuthorityDeserializer extends Deserializer[LocalAuthority] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): LocalAuthority = {
    val reader = new SpecificDatumReader[LocalAuthority](LocalAuthority.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new LocalAuthority()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}
