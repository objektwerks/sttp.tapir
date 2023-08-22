package objektwerks

import java.net.http.HttpClient
import java.util.concurrent.Executors

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.*

import sttp.client3.{basicRequest, HttpClientFutureBackend, Response, UriContext}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend

@main def runAsyncClient(): Unit =
  given executionContext: ExecutionContext = ExecutionContext.fromExecutor( Executors.newVirtualThreadPerTaskExecutor() )

  val client = HttpClient
                .newBuilder
                .executor( Executors.newVirtualThreadPerTaskExecutor() )
                .build

  val backend = Slf4jLoggingBackend( HttpClientFutureBackend.usingClient(client) )

  try
    val request = basicRequest.get(uri"https://api.chucknorris.io/jokes/random")
    val response = request.send(backend)
    Await.result(response, 30.seconds) // Keep the main thread alive for 30 seconds.
    parseResponse(response)
  finally backend.close()

  def parseResponse(response: concurrent.Future[Response[Either[String, String]]]): Unit =
    for
      r <- response
    yield
      r.body match
        case Left(error) => println( s"*** Async Client error: $error" )
        case Right(json) => println( s"*** Async Client response: ${parseJson(json)}" )

  def parseJson(json: String): String = ujson.read(json)("value").str