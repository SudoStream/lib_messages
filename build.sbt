name := """messages"""
organization := "io.sudostream.timetoteach"
version := "0.0.11-45"

scalaVersion := "2.11.7"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true)}

publishTo := Some(Resolver.file("file",  new File( "/home/andy/.ivy2/local" )) )

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
  "-Xfatal-warnings",
  "-Xlint",
  "-encoding", "utf8")

sbtavrohugger.SbtAvrohugger.specificAvroSettings

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.8.2",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.17",

  // test
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)


