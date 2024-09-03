package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

enum EventType:
  case test

final case class Event(name: String, typeof: EventType = EventType.test)

object Event:
  given JsonValueCodec[Event] = JsonCodecMaker.make[Event](CodecMakerConfig.withDiscriminatorFieldName(None))