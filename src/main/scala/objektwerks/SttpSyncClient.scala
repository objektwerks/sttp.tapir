package objektwerks

import sttp.client3.{basicRequest, SimpleHttpClient, UriContext}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend

@main def runSttpSyncClient(): Unit =
  val client = SimpleHttpClient().wrapBackend(Slf4jLoggingBackend(_))

  try
    val request = basicRequest.get(uri"https://api.chucknorris.io/jokes/random")
    val response = client.send(request)
    println( parseResponse(response.body) )
  finally client.close()

  def parseResponse(response: Either[String, String]): String =
    response match
      case Left(error) => s"*** Sttp Sync Client error: $error"
      case Right(json) => s"*** Sttp Sync Client response: ${parseJson(json)}"

  def parseJson(json: String): String = ujson.read(json)("value").str