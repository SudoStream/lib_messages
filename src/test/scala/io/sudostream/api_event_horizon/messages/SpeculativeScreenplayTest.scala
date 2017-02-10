package io.sudostream.api_event_horizon.messages

import org.scalatest.FunSuite

class SpeculativeScreenplayTest extends FunSuite {

  test("Lets play with avro") {

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

    println("Hey Ho ... \n" + speculativeScreenplay)

  }

}
