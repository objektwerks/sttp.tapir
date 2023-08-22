package objektwerks

import sttp.tapir._
import sttp.tapir.server.jdkhttp._

@main def runTapirEndpoint(): Unit =
  val helloEndpoint = endpoint
    .get
    .in("hello")
    .in(query[String]("name"))
    .out(stringBody)
    .handle(name => Right(s"Hello, $name!"))

  val jdkHttpServer = JdkHttpServer()
    .addEndpoint(helloEndpoint)
    .host("localhost")
    .port(7777)
    .start()

  sys.ShutdownHookThread {
    jdkHttpServer.stop(0)
  }