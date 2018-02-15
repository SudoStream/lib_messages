package io.sudostream.timetoteach.kafka.serializing.scottish

import java.io.ByteArrayOutputStream
import java.util

import io.sudostream.timetoteach.messages.scottish.ScottishCurriculumPlanningArea
import org.apache.avro.io.{DatumWriter, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.kafka.common.serialization.Serializer

class ScottishCurriculumPlanningAreaSerializer extends Serializer[ScottishCurriculumPlanningArea]
{
  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: ScottishCurriculumPlanningArea): Array[Byte] = {
    val writer: DatumWriter[ScottishCurriculumPlanningArea] =
      new SpecificDatumWriter[ScottishCurriculumPlanningArea](ScottishCurriculumPlanningArea.SCHEMA$)

    val out = new ByteArrayOutputStream()
    val encoder = new EncoderFactory().binaryEncoder(out, null)
    writer.write(data, encoder)
    encoder.flush()
    out.toByteArray
  }

  override def close(): Unit = {}

}
