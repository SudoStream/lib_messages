package io.sudostream.api_antagonist.messages

import java.io.File
import java.util.UUID

import org.apache.avro.file.{DataFileReader, DataFileWriter}
import org.apache.avro.io.{DatumReader, DatumWriter}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}
import org.scalatest.FunSuite

class FinalScriptTest extends FunSuite {

  test("Basic FinalScript test") {
    val httpHeader = HttpHeader("name", "value")
    val httpHeaders = List(httpHeader)
    val httpRequest = HttpRequest(
      HttpMethod.GET,
      "http://somehost.com:8080/resource123",
      httpHeaders,
      "this is the body",
      List(200, 404)
    )
    val finalScript = FinalScript(
      "ScriptNameAbc",
      UUID.randomUUID().toString,
      UUID.randomUUID().toString,
      List(httpRequest)
    )
    println("FinalScript film:\n " + finalScript.toString)

    val finalScriptFileName = "finalScript.avro"
    val finalScriptAvroFile = new File(finalScriptFileName)
    finalScriptAvroFile.delete()

    // serialize
    val speculativeScreenplayDatumWriter: DatumWriter[FinalScript] = new SpecificDatumWriter[FinalScript](finalScript.getSchema)
    val dataFileWriter: DataFileWriter[FinalScript] = new DataFileWriter[FinalScript](speculativeScreenplayDatumWriter)
    dataFileWriter.create(finalScript.getSchema(), finalScriptAvroFile)
    dataFileWriter.append(finalScript)
    dataFileWriter.close()

    // Deserialize from disk
    val speculativeScreenplayDatumReader: DatumReader[FinalScript] = new SpecificDatumReader[FinalScript](finalScript.getSchema)
    val dataFileReader: DataFileReader[FinalScript] =
      new DataFileReader[FinalScript](new File(finalScriptFileName), speculativeScreenplayDatumReader)

    var deserialisedFinalScript: FinalScript = null
    while (dataFileReader.hasNext) {
      // Reuse user object by passing it to next(). This saves us from  allocating and
      // garbage collecting many objects for files with many items.
      deserialisedFinalScript = dataFileReader.next(deserialisedFinalScript)
      println("okay: " + deserialisedFinalScript + "; dokies")
    }

    finalScriptAvroFile.delete()

    assert(finalScript.finalScriptUuid == deserialisedFinalScript.finalScriptUuid)
  }

}
