name := """aeh-messages"""

organization := "io.sudostream.api-event-horizon"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.7"

sbtavrohugger.SbtAvrohugger.specificAvroSettings

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.8.1",


  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

