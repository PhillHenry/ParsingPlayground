package uk.co.odinconsultants.scalaxml

import org.scalatest.{Matchers, WordSpec}

class XMLDemoSpec extends WordSpec with Matchers {

  import XMLDemo._

  "Xml snippet" should {
    "have a book ID" in {
      book \@ "id" shouldBe "book_id"
    }
  }

}
