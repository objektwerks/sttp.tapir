name := "sttp.tapir"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.3.1-RC5"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.sttp.client3" %% "core" % "3.9.0",
    "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.7.2",
    "com.softwaremill.sttp.tapir" %% "tapir-jdkhttp-server" % "1.7.0",
    "com.typesafe" % "config" % "1.4.2",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "org.scalatest" %% "scalatest" % "3.2.16" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)