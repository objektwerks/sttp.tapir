name := "sttp.tapir"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.3.1"
libraryDependencies ++= {
  Seq(
    "com.softwaremill.sttp.client3" %% "core" % "3.9.0",
    "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.8.2",
    "com.softwaremill.sttp.tapir" %% "tapir-jdkhttp-server" % "1.8.2",
    "com.softwaremill.sttp.client3" %% "slf4j-backend" % "3.9.0",
    "com.lihaoyi" %% "ujson" % "3.1.3",
    "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
