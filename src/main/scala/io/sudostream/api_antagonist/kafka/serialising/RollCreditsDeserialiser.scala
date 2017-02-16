package io.sudostream.api_antagonist.kafka.serialising

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.api_antagonist.messages.RollCredits
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class RollCreditsDeserialiser extends Deserializer[RollCredits] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): RollCredits = {
    val reader = new SpecificDatumReader[RollCredits](RollCredits.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new RollCredits()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}
}
