package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

import sttp.tapir.*

final case class Event(name: String) derives ConfiguredJsonValueCodec, Schema
