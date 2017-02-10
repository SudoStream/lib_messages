package io.sudostream.api_event_horizon.kafka.serialising

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.api_event_horizon.messages.SpeculativeScreenPlay
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class SpeculativeScreenPlayDeserialiser extends Deserializer[SpeculativeScreenPlay] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): SpeculativeScreenPlay = {
    val reader = new SpecificDatumReader[SpeculativeScreenPlay](SpeculativeScreenPlay.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new SpeculativeScreenPlay()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}
}
