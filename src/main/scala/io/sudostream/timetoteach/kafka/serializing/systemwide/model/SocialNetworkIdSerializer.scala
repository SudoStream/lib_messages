package io.sudostream.timetoteach.kafka.serializing.systemwide.model

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.systemwide.model.SocialNetworkId
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class SocialNetworkIdSerializer extends Serializer[SocialNetworkId] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: SocialNetworkId): Array[Byte] = {
    val writer: DatumWriter[SocialNetworkId] =
      new SpecificDatumWriter[SocialNetworkId](SocialNetworkId.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    new Array[Byte](1024)
    out.toByteArray
  }

  override def close(): Unit = {}

}
