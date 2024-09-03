name := "sttp.tapir"
organization := "objektwerks"
version := "0.6-SNAPSHOT"
scalaVersion := "3.5.1-RC2"
libraryDependencies ++= {
  val sttpVersion = "3.9.8"
  val tapirVersion = "1.11.1"
  val jsoniterVersion = "2.30.9"
  Seq(
    "com.softwaremill.sttp.client3" %% "core" % sttpVersion,
    "com.softwaremill.sttp.client3" %% "slf4j-backend" % sttpVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-jdkhttp-server" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-jsoniter-scala" % tapirVersion,
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % jsoniterVersion,
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterVersion,
    "com.lihaoyi" %% "ujson" % "4.0.1",
    "ch.qos.logback" % "logback-classic" % "1.5.7",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
