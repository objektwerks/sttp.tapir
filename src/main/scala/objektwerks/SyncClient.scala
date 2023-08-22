package objektwerks

import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}

object SyncClient:
  val client = SimpleHttpClient()
  val response = client.send(basicRequest.get(uri"https://api.chucknorris.io/jokes/random"))
  println(response.body)