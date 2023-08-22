package objektwerks

import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}

@main def runSyncClient(): Unit =
  val client = SimpleHttpClient()
  val response = client.send(basicRequest.get(uri"https://api.chucknorris.io/jokes/random"))
  println(response.body)