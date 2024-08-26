package objektwerks

import java.net.http.HttpClient
import java.util.concurrent.Executors

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.*

import sttp.client3.{basicRequest, HttpClientFutureBackend, Response, UriContext}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend

@main def runAsyncClient(): Unit =
  given ExecutionContext = ExecutionContext.fromExecutor( Executors.newVirtualThreadPerTaskExecutor() )

  val httpClient =
    HttpClient
      .newBuilder
      .executor( Executors.newVirtualThreadPerTaskExecutor() )
      .build

  val sttpBackend = Slf4jLoggingBackend( HttpClientFutureBackend.usingClient(httpClient) )

  try
    val request = basicRequest.get(uri"https://api.chucknorris.io/jokes/random")
    val response = request.send(sttpBackend)
    Await.result(response, 30.seconds) // Keep the main thread alive for 30 seconds.
    parseResponse(response).map { joke => println(joke) }
  finally sttpBackend.close()

  def parseResponse(futureResponse: Future[Response[Either[String, String]]]): Future[String] =
    for
      response <- futureResponse
    yield
      response.body match
        case Left(error) => s"*** Sttp Async Client error: $error"
        case Right(json) => s"*** Sttp Async Client response: ${parseJson(json)}"

  def parseJson(json: String): String = ujson.read(json)("value").str