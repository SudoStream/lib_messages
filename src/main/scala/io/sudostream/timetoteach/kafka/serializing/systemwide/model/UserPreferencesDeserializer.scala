package io.sudostream.timetoteach.kafka.serializing.systemwide.model

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.UserPreferences
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class UserPreferencesDeserializer extends Deserializer[UserPreferences] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): UserPreferences = {
    val reader = new SpecificDatumReader[UserPreferences](UserPreferences.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new UserPreferences()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}
