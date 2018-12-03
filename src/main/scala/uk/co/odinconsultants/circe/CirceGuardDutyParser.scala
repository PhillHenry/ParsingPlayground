package uk.co.odinconsultants.circe

import io.circe.{Decoder, Json, ParsingFailure}
import io.circe.parser.parse
import uk.co.odinconsultants.aws.GuardDuty._

object CirceGuardDutyParser {

  implicit val decodeClip: Decoder[NetworkConnection] = Decoder.instance { c =>
    for {
      direction   <- c.get[String]("direction")
      ipAddressV4 <- c.get[String]("ipAddressV4")
      org         <- c.get[String]("org")
      asnOrg      <- c.get[String]("asnOrg")
      isp         <- c.get[String]("isp")
      country     <- c.get[String]("country")
      city        <- c.get[String]("city")
      protocol    <- c.get[String]("protocol")
      blocked     <- c.get[Boolean]("blocked")
    } yield {
      NetworkConnection(direction, org, asnOrg, isp, country, city, protocol, blocked)
    }
  }

  def jsonDoc(str: String): Either[ParsingFailure, Json] = parse(str)

}
