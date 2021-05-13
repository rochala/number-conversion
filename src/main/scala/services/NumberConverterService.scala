package services

import scala.collection.immutable.TreeMap
import scala.math.Integral.Implicits._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class NumberConverterService(implicit executionContext: ExecutionContext) {
  private val romanNumerals = TreeMap(
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

  def convert(conversion: String, number: Int): Future[Option[String]] = {
    Future(
      if (number < 0)
        None
      else
        conversion match {
          case "roman" =>
            if (number < 4000 && number != 0) Some(romanConversion(number)) else None
          case "hexadecimal" => Some("0x" + systemConversion(number, 16))
          case _ => None
        }
    )
  }

  def systemConversion(
      number: Int,
      system: Int,
      result: String = ""
  ): String = {
    def systemNumeralConversion(numeral: Int, system: Int): String = {
      if (numeral < 10) {
        numeral.toString
      } else {
        (65 + numeral - 10).toChar.toString
      }
    }

    number match {
      case 0 => result
      case _ => {
        val (quotient, remainder) = number /% system
        systemConversion(
          quotient,
          system,
          systemNumeralConversion(remainder, system) + result
        )
      }
    }
  }
}
