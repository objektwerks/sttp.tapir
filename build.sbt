name := "sttp.tapir"
organization := "objektwerks"
version := "0.7-SNAPSHOT"
scalaVersion := "3.7.1-RC1"
libraryDependencies ++= {
  val sttpVersion = "3.11.0" // DON'T UPGRADE to Sttp 4!!!
  val tapirVersion = "1.11.28"
  val jsoniterVersion = "2.35.3"
  Seq(
    "com.softwaremill.sttp.client3" %% "core" % sttpVersion,
    "com.softwaremill.sttp.client3" %% "slf4j-backend" % sttpVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-jdkhttp-server" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-jsoniter-scala" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % jsoniterVersion,
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterVersion,
    "com.lihaoyi" %% "ujson" % "4.2.1",
    "ch.qos.logback" % "logback-classic" % "1.5.18",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
