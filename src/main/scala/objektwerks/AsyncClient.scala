package objektwerks

import java.net.http.HttpClient
import java.util.concurrent.Executors

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.jdk.FutureConverters.*

import sttp.capabilities.WebSockets
import sttp.client3.{HttpClientFutureBackend, Response, SttpBackend, UriContext, basicRequest}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend

@main def runAsyncClient(): Unit =
  given executionContext: ExecutionContext = ExecutionContext.fromExecutor( Executors.newVirtualThreadPerTaskExecutor() )

  val client = HttpClient
                .newBuilder
                .executor( Executors.newVirtualThreadPerTaskExecutor() )
                .build
  val backend = HttpClientFutureBackend.usingClient(client)

  try
    val request = basicRequest.get(uri"https://api.chucknorris.io/jokes/random")
    val response = request.send(backend)
    println( parseResponse(response) )
  finally backend.close()

  def parseResponse(response: concurrent.Future[Response[Either[String, String]]]): String =
    response match
      case Left(error) => s"*** Sync Client error: $error"
      case Right(json) => s"*** Sync Client response: ${parseJson(json)}"

  def parseJson(json: String): String = ujson.read(json)("value").str