package io.sudostream.api_event_horizon.messages

import java.io.{File, InputStream}

import org.apache.avro.file.{DataFileReader, DataFileWriter}
import org.apache.avro.io.{DatumReader, DatumWriter}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}
import org.scalatest.FunSuite

/**
  * Created by andy on 21/11/16.
  */
class AvroSerialiseDeserialiseTests extends FunSuite {

//    val swaggerJsonHelloApi =
//      """{
//        |    "swagger": "2.0",
//        |    "info": {
//        |        "title": "Hello World App",
//        |        "description": "Says hello to people",
//        |        "version": "1.0.0"
//        |    },
//        |    "host": "hello.com",
//        |    "schemes": [
//        |        "https"
//        |    ],
//        |    "basePath": "/v1",
//        |    "produces": [
//        |        "application/json"
//        |    ],
//        |    "paths": {
//        |        "/hello": {
//        |            "get": {
//        |                "summary": "Say Hello",
//        |                "description": "Says hello to whoever\n",
//        |                "parameters": [
//        |                    {
//        |                        "name": "nameOfPerson",
//        |                        "in": "query",
//        |                        "description": "Name of the person to say hello to",
//        |                        "required": false,
//        |                        "type": "string"
//        |                    }
//        |                ],
//        |                "tags": [
//        |                    "sayingHi"
//        |                ],
//        |                "responses": {
//        |                    "200": {
//        |                        "description": "A message saying hello to the person",
//        |                        "schema": {
//        |                            "type": "string"
//        |                        }
//        |                    }
//        |                }
//        |            }
//        |        }
//        |    }
//        |}
//      """.stripMargin
//
//    val expectedShortMetaDescription =
//      """
//        |API Title : Hello World App
//        |Target Host : hello.com
//        |Target Port : 443
//        |Schemes : https
//        | """.stripMargin
//
//    test("Ensure that when the expected number of happy path tests are generated") {
//      val happyTests: GeneratedTestsEvent = generateTestsFromSwagger
//      assert(happyTests.generatedTests.size === 3)
//    }
//
//    test("Ensure the top level GET /stars test has expected detail") {
//      val happyTests: GeneratedTestsEvent = generateTestsFromSwagger
//      val foundTest = happyTests.generatedTests.filter(
//        test => HttpMethod.GET.equals(test.method) && test.uriPath == "/stars")
//
//      println(happyTests)
//      assert(foundTest.size === 1)
//    }
//
//    def generateTestsFromSwagger: GeneratedTestsEvent = {
//      val swaggerJsonHelloStream: InputStream = getClass.getResourceAsStream("/swagger-starBirth.json")
//      val swaggerHelloJson = scala.io.Source.fromInputStream(swaggerJsonHelloStream).getLines() mkString "\n"
//      val starBirthScram = new SwaggerJsonScramConverter().convertToScram(swaggerHelloJson).get
//      val happyTests = starBirthScram.generateHappyPathTests
//      happyTests
//    }
//
//    test("Lets serialise and deserialise with Avro") {
//      val happyTests: GeneratedTestsEvent = generateTestsFromSwagger
//
//      // serialize
//      val generatedTestsDatumWriter: DatumWriter[GeneratedTestsEvent] = new SpecificDatumWriter[GeneratedTestsEvent](happyTests.getSchema)
//      val dataFileWriter: DataFileWriter[GeneratedTestsEvent] = new DataFileWriter[GeneratedTestsEvent](generatedTestsDatumWriter)
//      dataFileWriter.create(happyTests.getSchema(), new File("happy.avro"))
//      dataFileWriter.append(happyTests)
//      dataFileWriter.close()
//
//      // Deserialize  from disk
//      val generatedTestsDatumReader: DatumReader[GeneratedTestsEvent] = new SpecificDatumReader[GeneratedTestsEvent](happyTests.getSchema)
//      val dataFileReader: DataFileReader[GeneratedTestsEvent] =
//        new DataFileReader[GeneratedTestsEvent](new File("happy.avro"), generatedTestsDatumReader)
//
//      var testScript: GeneratedTestsEvent = null
//      while (dataFileReader.hasNext) {
//        // Reuse user object by passing it to next(). This saves us from  allocating and
//        // garbage collecting many objects for files with many items.
//        testScript = dataFileReader.next(testScript)
//        println(testScript)
//      }
//    }
//
//  }

}
