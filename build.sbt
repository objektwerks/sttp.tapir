name := "sttp.tapir"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.3.1-RC4"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.sttp.client3" %% "core" % "3.9.0",
    "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.7.2",
    "com.softwaremill.sttp.tapir" %% "tapir-jdkhttp-server" % "1.7.0",
    "com.lihaoyi" %% "ujson" % "3.1.2",
    "org.scalatest" %% "scalatest" % "3.2.16" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)