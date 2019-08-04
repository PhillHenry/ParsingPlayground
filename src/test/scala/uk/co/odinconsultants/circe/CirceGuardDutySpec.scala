package uk.co.odinconsultants.circe

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import uk.co.odinconsultants.aws.GuardDuty._
import uk.co.odinconsultants.json.SampleGuardDuty
import uk.co.odinconsultants.json.SampleGuardDuty.createJson

@RunWith(classOf[JUnitRunner])
class CirceGuardDutySpec extends WordSpec with Matchers with SampleGuardDuty {

  import CirceGuardDutyParser._

  "Implicitly derived types" should {
    val json: String    = createJson(direction, ipAddressV4, asnOrg, isp, org, countryName, cityName, lat, lon, remotePort, localPort, protocol, blocked, createdAtStr)
    "be extracted" ignore {
//      asTags(json).fold(fail(_), _ shouldBe Tag("GeneratedFindingInstaceTag1", "GeneratedFindingInstaceValue1"))
    }
  }

  "Missing fields" should {
    "not stop parsing" in {
      val json            = createJson("", ipAddressV4, asnOrg, isp, org, countryName, cityName, lat, lon, remotePort, localPort, protocol, blocked, createdAtStr)
      val expectedAction  = Connection("", ipAddressV4, org, asnOrg, isp, countryName, cityName, lat, lon, protocol, blocked, remotePort, localPort)
      asAction(json).fold(x => fail(x), _.shouldEqual(expectedAction))
    }
  }

  "Circe" should {
    val json: String    = createJson(direction, ipAddressV4, asnOrg, isp, org, countryName, cityName, lat, lon, remotePort, localPort, protocol, blocked, createdAtStr)
    val expectedAction  = Connection(direction, ipAddressV4, org, asnOrg, isp, countryName, cityName, lat, lon, protocol, blocked, remotePort, localPort)

    "be able to parse JSON" in {
      val either = jsonDoc(json)
      either.fold( x => fail(s"${x.message} when parsing:\n$json"), _ => ())
    }

    "extract action from GuardDuty JSON" in {
      asAction(json).fold(x => fail(x), _.shouldEqual(expectedAction))
    }

    "extract detail from JSON" in {
      val expectedDetail  = Detail(expectedAction, parseDate(createdAtStr))
      asDetail(json).fold(x => fail(x), _.shouldEqual(expectedDetail))
    }

    import io.circe.optics.JsonPath._
    import io.circe.parser._
    "be able to use optics" in {
      parse(json) match {
        case Left(failure)  => fail(failure)
        case Right(text)    =>
          val _createdAt = root.detail.createdAt.string
          _createdAt.getOption(text) shouldBe Some(createdAtStr)
      }
    }
    "be able to use optics exploring arrays" in {
      parse(json) match {
        case Left(failure)  => fail(failure)
        case Right(text)    =>
          val _createdAt = root.detail.resource.instanceDetails.tags.arr
          _createdAt.getOption(text).size should be > (0)
      }
    }
  }

}
