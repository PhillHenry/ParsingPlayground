package uk.co.odinconsultants.circe

import org.scalatest.{Matchers, WordSpec}
import uk.co.odinconsultants.aws.GuardDuty._
import uk.co.odinconsultants.json.SampleGuardDuty
import uk.co.odinconsultants.json.SampleGuardDuty.createJson

class CirceGuardDutySpec extends WordSpec with Matchers with SampleGuardDuty {

  import CirceGuardDutyParser._

  "Missing fields" should {
    "not stop parsing" in {
      val json            = createJson("", ipAddressV4, asnOrg, isp, org, countryName, cityName, lat, lon, remotePort, localPort, protocol, blocked, createdAtStr)
      val expectedAction  = Connection("", ipAddressV4, org, asnOrg, isp, countryName, cityName, lat, lon, protocol, blocked, remotePort, localPort)
      asAction(json).fold(x => fail(x), _.shouldEqual(expectedAction))
    }
  }

  "Circe" should {
    val json: String  = createJson(direction,  ipAddressV4, asnOrg, isp, org, countryName, cityName, lat, lon, remotePort, localPort, protocol, blocked, createdAtStr)

    val expectedAction = Connection(
      direction,
      ipAddressV4,
      org,
      asnOrg,
      isp,
      countryName,
      cityName,
      lat,
      lon,
      protocol,
      blocked,
      remotePort,
      localPort
    )

    val expectedDetail = Detail(expectedAction, parseDate(createdAtStr))

    "be able to parse JSON" in {
      val either = jsonDoc(json)
      either.fold( x => fail(s"${x.message} when parsing:\n$json"), _ => ())
    }

    "extract action from GuardDuty JSON" in {
      asAction(json).fold(x => fail(x), _.shouldEqual(expectedAction))
    }

    "extract detail from JSON" in {
      asDetail(json).fold(x => fail(x), _.shouldEqual(expectedDetail))
    }

    "be able to use optics" in {
      import io.circe.optics.JsonPath._
      import io.circe.parser._
      parse(json) match {
        case Left(failure)  => fail(failure)
        case Right(text)    =>
          val _createdAt = root.detail.createdAt.string
          _createdAt.getOption(text) shouldBe Some(createdAtStr)
      }
    }
  }

}
