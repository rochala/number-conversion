package services

import scala.collection.immutable.TreeMap
import scala.math.Integral.Implicits._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class NumberConverterService(implicit executionContext: ExecutionContext) {
  val romanNumerals = TreeMap(
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
    1 -> "I"
  )

  def romanConversion(number: Int, result: String = ""): String = {
    val numeral = romanNumerals.rangeTo(number).lastOption
    numeral match {
      case Some((value, literal)) =>
        romanConversion(number - value, result + literal)
      case None => result
    }
  }

  def convert(number: Int, conversion: String): Future[Option[String]] = {
    Future(
      conversion match {
        case "roman" => Some(romanConversion(number))
        case "hexadecimal" => systemConversion(number, 16)
      }
    )
  }

  def systemConversion(number: Int, system: Int, result: String = ""): Option[String] = {
    def systemNumeralConversion(number: Int, system: Int): String = {
      if (number < 10) {
        number.toString
      } else {
        (65 + number - 10).toChar.toString
      }
    }

    number match {
      case 0 => Some(result)
      case _ => {
        val (quotient, remainder) = number /% system
        systemConversion(quotient, system, systemNumeralConversion(remainder, system) + result)
      }
    }
  }

 //dzielimy ten crap

}
