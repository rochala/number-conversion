import org.scalatest.flatspec.AsyncFlatSpec
import services.NumberConverterService
import scala.concurrent.Future

class NumberConverterTest extends AsyncFlatSpec {
  val converter = new NumberConverterService()

  def addSoon(addends: Int*): Future[Int] = Future { addends.sum }

  behavior of "NumberConverterService"

  it should "provide both roman and hexadecimal conversion using single function" in {
    for {
      res1 <- converter.convert("roman", 1)
      res2 <- converter.convert("hexadecimal", 2)
    } yield {
      assert(res1 == Some("I"))
      assert(res2 == Some("0x2"))
    }
  }

  it should "properly convert decimals into roman system" in {
    assertConversion(converter.convert("roman", 2352), Some("MMCCCLII"))
  }

  it should "properly convert decimals into hexadecimal system" in {
    assertConversion(converter.convert("hexadecimal", 2352), Some("0x930"))
  }

  it should "return None when input's are negative" in {
    for {
      res1 <- converter.convert("roman", -1)
      res2 <- converter.convert("hexadecimal", -1)
    } yield {
      assert(res1 == None)
      assert(res2 == None)
    }
  }

  it should "return None when input equals 0 while using roman convertion" in {
    assertConversion(converter.convert("roman", 0), None)
  }

  it should "return None when input is higher than 3999 while using roman conversion" in {
    assertConversion(converter.convert("roman", 4000), None)
  }

  it should "return None when conversion type argument != roman or hexadecimal" in {
    assertConversion(converter.convert("hex", 1), None)
  }

  def assertConversion(conversion: Future[Option[String]], expectedResult: Option[String]) = {
    conversion.map { res =>
      assert(res == expectedResult)
    }
  }
}
