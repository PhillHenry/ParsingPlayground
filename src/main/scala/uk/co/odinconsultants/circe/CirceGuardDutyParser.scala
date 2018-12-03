package uk.co.odinconsultants.circe

import io.circe.{Decoder, Error, Json, ParsingFailure}
import io.circe.parser._
import uk.co.odinconsultants.aws.GuardDuty._

object CirceGuardDutyParser {

  implicit val decodeAction: Decoder[Connection] = Decoder.instance { c =>
    for {
      direction   <- c.get[String]("connectionDirection")
//      ipAddressV4 <- c.get[String]("ipAddressV4")
//      org         <- c.get[String]("org")
//      asnOrg      <- c.get[String]("asnOrg")
//      isp         <- c.get[String]("isp")
//      country     <- c.get[String]("country")
//      city        <- c.get[String]("city")
//      lat         <- c.get[Double]("lat")
//      lon         <- c.get[Double]("lon")
    } yield {
      Connection(direction) //, /*ipAddressV4, org, asnOrg, isp,*/ country, city, lat, lon)
    }
  }

  val decodeClipsParam: Decoder[Connection] = Decoder[Connection].prepare(
    _.downField("detail").downField("service").downField("action").downField("networkConnectionAction")
  )

  def jsonDoc(json: String): Either[ParsingFailure, Json] = parse(json)

  def asAction(json: String): Either[Error, Connection] = decode(json)(decodeClipsParam)

}
