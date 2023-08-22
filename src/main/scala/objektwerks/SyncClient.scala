package objektwerks

import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend

@main def runSyncClient(): Unit =
  val client = SimpleHttpClient().wrapBackend(Slf4jLoggingBackend(_))
  val request = basicRequest.get(uri"https://api.chucknorris.io/jokes/random")
  val response = client.send(request)
  println( parseResponse(response.body) )
  client.close()

def parseResponse(response: Either[String, String]): String =
  response match
    case Left(error) => s"*** Sync Client error: $error"
    case Right(json) => s"*** Sync Client response: ${parseJson(json)}"

def parseJson(json: String): String = ujson.read(json)("value").str