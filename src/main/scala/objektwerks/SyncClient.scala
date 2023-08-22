package objektwerks

import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}

@main def runSyncClient(): Unit =
  val client = SimpleHttpClient()
  val response = client.send( basicRequest.get(uri"https://api.chucknorris.io/jokes/random") )
  println( parseResponse( response.body ) )
  client.close()

def parseResponse(response: Either[String, String]): String =
  response match
    case Left(error) => s"*** Sync Client error: $error"
    case Right(json) => s"*** Sync Client response: ${parseJson(json)}"

def parseJson(json: String): String = ujson.read(json)("value").str