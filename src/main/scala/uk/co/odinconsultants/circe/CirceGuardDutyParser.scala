package uk.co.odinconsultants.circe

import java.text.SimpleDateFormat
import java.util.Date

import io.circe.{Decoder, Error, Json, ParsingFailure}
import io.circe._, io.circe.generic.semiauto._
import io.circe.parser._
import uk.co.odinconsultants.aws.GuardDuty._

/**
  * @see https://stackoverflow.com/questions/52302080/circe-list-deserialization-with-best-attempt-and-error-reporting
  * @see https://stackoverflow.com/questions/42165460/how-to-decode-an-adt-with-circe-without-disambiguating-objects
  * @see https://github.com/circe/circe/issues/541 for semi-automatic deserialization
  */
object CirceGuardDutyParser {

  implicit val decodeAction: Decoder[Connection] = Decoder.instance { c =>
    val remoteIpDetails = c.downField("remoteIpDetails")
    val organization    = remoteIpDetails.downField("organization")
    for {
      direction   <- c.get[String]("connectionDirection")
      ipAddressV4 <- remoteIpDetails.get[String]("ipAddressV4")
      org         <- organization.get[String]("org")
      asnOrg      <- organization.get[String]("asnOrg")
      isp         <- organization.get[String]("isp")
      country     <- remoteIpDetails.downField("country").get[String]("countryName")
      city        <- remoteIpDetails.downField("city").get[String]("cityName")
      lat         <- remoteIpDetails.downField("geoLocation").get[Double]("lat")
      lon         <- remoteIpDetails.downField("geoLocation").get[Double]("lon")
      remotePort  <- c.downField("remotePortDetails").get[Int]("port")
      localPort   <- c.downField("localPortDetails").get[Int]("port")
      protocol    <- c.get[String]("protocol")
      blocked     <- c.get[Boolean]("blocked")
    } yield {
      Connection(direction, ipAddressV4, org, asnOrg, isp, country, city, lat, lon, protocol, blocked, remotePort, localPort)
    }
  }

  def parseDate(x: String): Date = {
    val dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    dateFormatter.parse(x)
  }

  implicit val decodeTag: Decoder[Tag] = deriveDecoder[Tag]
//  implicit val decodeTags: Decoder[List[Tag]] = deriveDecoder[List[Tag]]
  val decodeClipsParam = Decoder[List[Tag]].prepare(
    _.downField("detail").downField("resource").downField("instanceDetails").downField("tags").downArray
  )

//  implicit val decodeTags: Decoder[List[Tag]] =
//    deriveDecoder[List[Tag]].prepare(
//      _.downField("detail").downField("resource").downField("instanceDetails").downField("tags")
//    )

  implicit val dateTimeDecoder: Decoder[Date] = Decoder.instance(a => a.as[String].map(parseDate))

  implicit val decodeDetail: Decoder[Detail] = Decoder.instance { c=>
    for {
      connection <- c.downField("service").downField("action").downField("networkConnectionAction").as[Connection]
      createdAt  <- c.get[Date]("createdAt")
    } yield {
      Detail(connection, createdAt)
    }
  }

  val decodeNetworkConnectionParam: Decoder[Connection] = Decoder[Connection].prepare(
    _.downField("detail").downField("service").downField("action").downField("networkConnectionAction")
  )

  val decodeDetailParam: Decoder[Detail] = Decoder[Detail].prepare(
    _.downField("detail")
  )

  def jsonDoc(json: String): Either[ParsingFailure, Json] = parse(json)

  def asAction(json: String): Either[Error, Connection] = decode(json)(decodeNetworkConnectionParam)

  def asDetail(json: String): Either[Error, Detail] = decode(json)(decodeDetailParam)

  def asTags(json: String): Either[Error, List[Tag]]  = decode[List[Tag]](json)(decodeClipsParam)
//  def asTags(json: String): Either[Error, List[Tag]]  = decode[List[Tag]](json) // Caused by: DecodingFailure(C[A], List()) or DecodingFailure(CNil, List())
//  def asTags(json: String): Either[Error, Tag]  = decode[Tag](json) // Caused by: DecodingFailure(Attempt to decode value on failed cursor, List(DownField(key)))

}
