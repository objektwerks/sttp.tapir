name := "sttp.tapir"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.4.0-RC1"
libraryDependencies ++= {
  val sttpVersion = "3.9.1"
  val tapirVersion = "1.9.6"
  Seq(
    "com.softwaremill.sttp.client3" %% "core" % sttpVersion,
    "com.softwaremill.sttp.client3" %% "slf4j-backend" % sttpVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-jdkhttp-server" % tapirVersion,
    "com.lihaoyi" %% "ujson" % "3.1.3",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
