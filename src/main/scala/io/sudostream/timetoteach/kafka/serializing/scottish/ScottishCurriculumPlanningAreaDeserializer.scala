package io.sudostream.timetoteach.kafka.serializing.scottish

import java.io.{ByteArrayInputStream, InputStream}
import java.util

import io.sudostream.timetoteach.messages.scottish.ScottishCurriculumPlanningArea
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.kafka.common.serialization.Deserializer

class ScottishCurriculumPlanningAreaDeserializer extends Deserializer[ScottishCurriculumPlanningArea]
{

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit =
  {}

  override def deserialize(topic: String, data: Array[Byte]): ScottishCurriculumPlanningArea =
  {
    val reader = new SpecificDatumReader[ScottishCurriculumPlanningArea](ScottishCurriculumPlanningArea.SCHEMA$)
    val in: InputStream = new ByteArrayInputStream(data)
    val decoder: org.apache.avro.io.Decoder = new DecoderFactory().binaryDecoder(in, null)
    val outVersion = new ScottishCurriculumPlanningArea()
    reader.read(outVersion, decoder)
    outVersion
  }

  override def close(): Unit =
  {}

}
