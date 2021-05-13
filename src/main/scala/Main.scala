import scala.collection.immutable.TreeMap

object Main extends App {
  val romanNumberals = TreeMap(
    1000 -> "M",
    900 -> "CM",
    500 -> "D",
    400 -> "CD",
    100 -> "C",
    90 -> "XC",
    50 -> "L",
    40 -> "XL",
    10 -> "X",
    9 -> "IX",
    5 -> "V",
    4 -> "IV",
    1 -> "I")
  def romanConversion(number: Int): String = {
    val numeral = romanNumberals.rangeTo(number).maxOption
    numeral match {
      case Some((value, literal)) => literal + romanConversion(number - value)
      case None => ""
    }
  }


  println(romanConversion(210))
  println(romanConversion(2110))
  println(romanConversion(2115))
  println(romanConversion(510))
  println(romanConversion(10))
  println("Hello, World!".concat("test"))
}
