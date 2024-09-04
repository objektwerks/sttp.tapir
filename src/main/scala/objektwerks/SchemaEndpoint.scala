package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*

import java.util.concurrent.Executors

import sttp.client3.{SimpleHttpClient, UriContext, basicRequest}
import sttp.client3.logging.slf4j.Slf4jLoggingBackend
import sttp.shared.Identity
import sttp.tapir.*
import sttp.tapir.json.jsoniter.*
import sttp.tapir.server.jdkhttp.*
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import Command.given
import Event.given
import Schemas.given

@main def runSchemaEndpoint(): Unit =
  val commandEndpoint =
    endpoint
      .post
      .in(jsonBody[Command])
      .out(jsonBody[Event])
      .handleSuccess { command =>
        println(s"*** SchemaEndpoint command: $command")
        val event = Event("command done")
        println(s"*** SchemaEndpoint event: $event")
        event
      }

  val swaggerEndpoints = SwaggerInterpreter()
    .fromServerEndpoints[Identity](List(commandEndpoint), "SchemaEndpoint", "1.0")

  val jdkHttpServer =
    JdkHttpServer()
      .executor(Executors.newVirtualThreadPerTaskExecutor())
      .host("localhost")
      .port(7777)
      .addEndpoint(commandEndpoint)
      .addEndpoints(swaggerEndpoints)
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
    println(s"*** SchemaEndpoint Client command: $command")
    println(parseResponse(response.body))
  finally
    httpClient.close()
    jdkHttpServer.stop(0)

  def parseResponse(response: Either[String, String]): String =
    response match
      case Left(error) => s"*** SchemaEndpoint Client error: $error"
      case Right(event) => s"*** SchemaEndpoint Client event: $event"