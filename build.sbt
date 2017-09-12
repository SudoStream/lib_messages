name := """messages"""
organization := "io.sudostream.timetoteach"
version := "0.0.10"

scalaVersion := "2.11.7"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true)}

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Ywarn-dead-code",
  "-Ywarn-infer-any",
  "-Ywarn-unused-import",
  "-Xfatal-warnings",
  "-Xlint",
  "-encoding", "utf8")

sbtavrohugger.SbtAvrohugger.specificAvroSettings

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.8.2",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13",

  // test
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)


