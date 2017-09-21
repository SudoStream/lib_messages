package io.sudostream.timetoteach.kafka.serializing.systemwide.requests

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.systemwide.requests.IsUserIdValidRequest
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class IsUserIdValidRequestDeserializer extends Deserializer[IsUserIdValidRequest] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): IsUserIdValidRequest = {
    val reader = new SpecificDatumReader[IsUserIdValidRequest](IsUserIdValidRequest.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new IsUserIdValidRequest()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}
