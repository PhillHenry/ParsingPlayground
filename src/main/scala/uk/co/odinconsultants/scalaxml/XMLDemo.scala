package uk.co.odinconsultants.scalaxml

object XMLDemo {

  val book: scala.xml.Elem = <book id="b20234">Magic of scala-xml</book>

  def main(args: Array[String]): Unit = {
    println(s"Book ID = ${book \@ "id"}")
  }

}
