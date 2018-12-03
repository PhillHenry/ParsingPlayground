package uk.co.odinconsultants.circe

import org.scalatest.{Matchers, WordSpec}
import uk.co.odinconsultants.json.SampleGuardDuty
import uk.co.odinconsultants.aws.GuardDuty._

class CirceGuardDutySpec extends WordSpec with Matchers {

  import CirceGuardDutyParser._

  "Circe" should {
    import SampleGuardDuty._

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

    "be able to parse JSON" in {
      val either = jsonDoc(json)
      either.fold( x => fail(s"${x.message} when parsing:\n$json"), _ => ())
    }

    "extract action from GuardDuty JSON" in {
      asAction(json).fold(x => fail(x), _.shouldEqual(expectedAction))
    }

    "be able to use optics" in {
      import io.circe.optics.JsonPath._
      import io.circe.parser._
      parse(json) match {
        case Left(failure)  => fail(failure)
        case Right(text)    =>
          val _createdAt = root.detail.createdAt.string
          _createdAt.getOption(text) shouldBe Some("2018-05-11T14:56:39.976Z")
      }
    }
  }

}
