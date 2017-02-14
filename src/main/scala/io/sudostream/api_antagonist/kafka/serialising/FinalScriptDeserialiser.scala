package io.sudostream.api_antagonist.kafka.serialising

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.api_antagonist.messages.FinalScript
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class FinalScriptDeserialiser extends Deserializer[FinalScript] {
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): FinalScript = {
    val reader = new SpecificDatumReader[FinalScript](FinalScript.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new FinalScript()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}
}
