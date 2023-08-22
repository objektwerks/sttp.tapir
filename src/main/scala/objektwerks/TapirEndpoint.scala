package objektwerks

import java.util.concurrent.Executors

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
    .executor( Executors.newVirtualThreadPerTaskExecutor() )
    .host("localhost")
    .port(7777)
    .addEndpoint(helloEndpoint)
    .start()

  sys.ShutdownHookThread {
    jdkHttpServer.stop(0)
  }