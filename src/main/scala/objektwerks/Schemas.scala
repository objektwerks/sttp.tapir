package objektwerks

import sttp.tapir.*

object Schemas:
  given Schema[Command] = Schema.derived
  given Schema[Event] = Schema.derived