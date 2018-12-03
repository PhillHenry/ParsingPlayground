package uk.co.odinconsultants.circe

import org.scalatest.{Matchers, WordSpec}
import uk.co.odinconsultants.json.SampleGuardDuty

class CirceGuardDutySpec extends WordSpec with Matchers {

  import CirceGuardDutyParser._

  "Circe" should {
    import SampleGuardDuty._
    "be able to parse JSON" in {
      val either = jsonDoc(json)
      either.fold( x => fail(s"${x.message} when parsing:\n$json"), _ => ())
    }

    "extract action from GuardDuty JSON" in {

    }
  }

}
