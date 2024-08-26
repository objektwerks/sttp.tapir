package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*

import java.util.concurrent.Executors

import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend
import sttp.tapir.*
import sttp.tapir.json.jsoniter.*
import sttp.tapir.server.jdkhttp.*

import Command.given
import Event.given

@main def runServer(): Unit =
  val commandEndpoint =
    endpoint
      .post
      .in(jsonBody[Command])
      .out(jsonBody[Event])
      .handleSuccess { command =>
        println(s"*** Endpoint command: $command")
        val event = Event("command done")
        println(s"*** Endpoint event: $event")
        event
      }

  val jdkHttpServer =
    JdkHttpServer()
      .executor(Executors.newVirtualThreadPerTaskExecutor())
      .host("localhost")
      .port(7777)
      .addEndpoint(commandEndpoint)
      .start()

  val httpClient = SimpleHttpClient().wrapBackend(Slf4jLoggingBackend(_))

  try
    val command = Command("do command")
    val commandJson = writeToString(command)
    val request = basicRequest
      .contentType("application/json")
      .body(commandJson)
      .post(uri"http://localhost:7777/command")
    val response = httpClient.send(request)
    println(s"*** Client command: $command")
    println(parseResponse(response.body))
  finally
    httpClient.close()
    jdkHttpServer.stop(0)

  def parseResponse(response: Either[String, String]): String =
    response match
      case Left(error) => s"*** Client error: $error"
      case Right(event) => s"*** Client event: $event"