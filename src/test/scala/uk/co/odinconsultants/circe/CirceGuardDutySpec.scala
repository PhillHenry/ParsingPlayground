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
      lon
    )

    "be able to parse JSON" in {
      val either = jsonDoc(json)
      either.fold( x => fail(s"${x.message} when parsing:\n$json"), _ => ())
    }

    "extract action from GuardDuty JSON" in {
      asAction(json).fold(x => fail(x), _.shouldEqual(expectedAction))
    }
  }

}
