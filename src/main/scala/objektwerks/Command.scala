package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

enum CommandType:
  case test

final case class Command(name: String, typeof: CommandType = CommandType.test)

object Command:
  given JsonValueCodec[Command] = JsonCodecMaker.make[Command](CodecMakerConfig.withDiscriminatorFieldName(None))