package io.sudostream.api_antagonist.messages

import java.io.File
import java.util.UUID

import org.apache.avro.file.{DataFileReader, DataFileWriter}
import org.apache.avro.io.{DatumReader, DatumWriter}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}
import org.scalatest.FunSuite

class GreenlitTest extends FunSuite {

  test("Basic greenlit test") {
    val aSingleHttpQuestion = HttpQuestionForTheProtagonistAPI("/stars", HttpMethod.GET, List(200))

    val speculativeScreenplay =
      SpeculativeScreenplay(
        "apiTitle",
        Some("apiDesc"),
        Some("v1"),
        "hostname.com",
        Some("api"),
        List("http"),
        List(80),
        List(aSingleHttpQuestion)
      )

    val greenlitFilm = GreenlitFilm("Stars Film", FilmGenre.ROMANCE, UUID.randomUUID().toString, speculativeScreenplay)
    println("Greenlit film:\n " + greenlitFilm.toString)

    val greenlitFilmAvroFile = new File("greenlitFilm.avro")
    greenlitFilmAvroFile.delete()

    // serialize
    val speculativeScreenplayDatumWriter: DatumWriter[GreenlitFilm] = new SpecificDatumWriter[GreenlitFilm](greenlitFilm.getSchema)
    val dataFileWriter: DataFileWriter[GreenlitFilm] = new DataFileWriter[GreenlitFilm](speculativeScreenplayDatumWriter)
    dataFileWriter.create(greenlitFilm.getSchema(), greenlitFilmAvroFile)
    dataFileWriter.append(greenlitFilm)
    dataFileWriter.close()

    // Deserialize  from disk
    val speculativeScreenplayDatumReader: DatumReader[GreenlitFilm] = new SpecificDatumReader[GreenlitFilm](greenlitFilm.getSchema)
    val dataFileReader: DataFileReader[GreenlitFilm] =
      new DataFileReader[GreenlitFilm](new File("greenlitFilm.avro"), speculativeScreenplayDatumReader)

    var deserialisedGreenlitFilm: GreenlitFilm = null
    while (dataFileReader.hasNext) {
      // Reuse user object by passing it to next(). This saves us from  allocating and
      // garbage collecting many objects for files with many items.
      deserialisedGreenlitFilm = dataFileReader.next(deserialisedGreenlitFilm)
      println("okay: " + deserialisedGreenlitFilm + "; dokies")
    }

    greenlitFilmAvroFile.delete()

    assert(greenlitFilm.filmUuid == deserialisedGreenlitFilm.filmUuid)
  }

}
