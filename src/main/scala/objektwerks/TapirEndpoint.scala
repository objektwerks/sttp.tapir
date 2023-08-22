package objektwerks

import java.util.concurrent.Executors

import sttp.client3.{basicRequest, SimpleHttpClient, UriContext}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend
import sttp.tapir.*
import sttp.tapir.server.jdkhttp.*

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

  val client = SimpleHttpClient().wrapBackend(Slf4jLoggingBackend(_))

  try
    val request = basicRequest.get(uri"http://localhost:7777/hello?name=Fred Flintstone")
    val response = client.send(request)
    println( response.body )
  finally
    client.close()
    jdkHttpServer.stop(0)