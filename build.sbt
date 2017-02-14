name := """messages"""

organization := "io.sudostream.api-antagonist"

version := "0.0.2"

scalaVersion := "2.11.7"

sbtavrohugger.SbtAvrohugger.specificAvroSettings

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.8.1",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13",

  // test
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

