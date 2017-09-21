package io.sudostream.timetoteach.kafka.serializing.systemwide.responses

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.responses.IsUserIdValidResponse
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class IsUserIdValidResponseDeserializer extends Deserializer[IsUserIdValidResponseSerializer] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): IsUserIdValidResponseSerializer = {
    val reader = new SpecificDatumReader[IsUserIdValidResponseSerializer](IsUserIdValidResponse.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new IsUserIdValidResponseSerializer()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}


}
