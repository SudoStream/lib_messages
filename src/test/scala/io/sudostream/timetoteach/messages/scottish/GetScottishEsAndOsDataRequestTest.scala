package io.sudostream.timetoteach.messages.scottish

import java.time.{Instant, LocalDateTime, ZoneId, ZoneOffset}

import io.sudostream.timetoteach.messages.systemwide.TimeToTeachApplication
import org.scalatest.FunSuite

class GetScottishEsAndOsDataRequestTest extends FunSuite {

  test("Basic ScottishEsAndOsDocumentTest") {

    val request = GetScottishEsAndOsDataRequest(
      originalUTCTimeOfRequest = Instant.now().toEpochMilli,
      requestFingerprint = java.util.UUID.randomUUID().toString,
      requestingSystem = TimeToTeachApplication.TEST_UNIT,
      requestingUsername = "Andy"
    )

    println("Request looks like:-\n" + request)
    assert(request.requestingUsername == "Andy")
    val timestamp = Instant.ofEpochMilli(request.originalUTCTimeOfRequest).atZone(ZoneId.of("UTC")).toLocalDateTime
    println("Time of request was " + timestamp + " UTC")
  }

}
