package objektwerks

import java.net.http.HttpClient
import java.util.concurrent.Executors

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.*

import sttp.client3.{basicRequest, HttpClientFutureBackend, Response, UriContext}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend

@main def runSttpAsyncClient(): Unit =
  given ExecutionContext = ExecutionContext.fromExecutor( Executors.newVirtualThreadPerTaskExecutor() )

  val httpClient =
    HttpClient
      .newBuilder
      .executor( Executors.newVirtualThreadPerTaskExecutor() )
      .build

  val backend = Slf4jLoggingBackend( HttpClientFutureBackend.usingClient(httpClient) )

  try
    val request = basicRequest.get(uri"https://api.chucknorris.io/jokes/random")
    val response = request.send(backend)
    Await.result(response, 30.seconds) // Keep the main thread alive for 30 seconds.
    parseResponse(response)
  finally backend.close()

  def parseResponse(response: Future[Response[Either[String, String]]]): Unit =
    for
      r <- response
    yield
      r.body match
        case Left(error) => println( s"*** Sttp Async Client error: $error" )
        case Right(json) => println( s"*** Sttp Async Client response: ${parseJson(json)}" )

  def parseJson(json: String): String = ujson.read(json)("value").str