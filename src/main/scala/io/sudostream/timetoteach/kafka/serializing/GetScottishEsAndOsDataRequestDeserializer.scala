package io.sudostream.timetoteach.kafka.serializing

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.scottish.GetScottishEsAndOsDataRequest
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class GetScottishEsAndOsDataRequestDeserializer extends Deserializer[GetScottishEsAndOsDataRequest] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): GetScottishEsAndOsDataRequest = {
    val reader = new SpecificDatumReader[GetScottishEsAndOsDataRequest](GetScottishEsAndOsDataRequest.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new GetScottishEsAndOsDataRequest()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit = {}

}

