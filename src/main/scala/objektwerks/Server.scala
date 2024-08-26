package objektwerks

import java.util.concurrent.Executors

import sttp.tapir.*
import sttp.tapir.json.jsoniter.*
import sttp.tapir.server.jdkhttp.*

@main def runServer(): Unit =
  val commandEndpoint =
    endpoint
      .post
      .in(jsonBody[Command])
      .out(jsonBody[Event])
      .handleSuccess { command =>
        Event(command.name)
      }

  val jdkHttpServer =
    JdkHttpServer()
      .executor(Executors.newVirtualThreadPerTaskExecutor())
      .host("localhost")
      .port(7777)
      .addEndpoint(commandEndpoint)
      .start()