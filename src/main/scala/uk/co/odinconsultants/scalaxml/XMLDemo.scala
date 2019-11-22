package uk.co.odinconsultants.scalaxml

/**
 * From Gitter:
 *

PhillHenry @PhillHenry Nov 21 16:13
Been handed some XML parsing work.
What's the most FP-oriented library out there these days?
Speed is unimportant.

Rob Norris @tpolecat Nov 21 16:27
There exists a circe-xml but I haven't used it. It's one source file so it seems promising.

Gavin Bisesi @Daenyth Nov 21 16:55
@PhillHenry I don't think there's a good FP xml library. I recently had to do some XML work and I used scala-xml + writing helper functions with cats Validated (or Either+Parallel depending on the case)

 */
object XMLDemo {

  /**
   * https://github.com/scala/scala-xml/wiki/Getting-started
   */
  val book: scala.xml.Elem = <book id="b20234">Magic of scala-xml</book>

  def main(args: Array[String]): Unit = {
    println(s"Book ID = ${book \@ "id"}")
  }

}
