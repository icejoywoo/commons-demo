import scala.collection.mutable
import scala.util.parsing.combinator._

case class WordFreq(word: String, count: Int) {
  override def toString: String = "Word <" + word + "> " + "occurs with frequency " + count
}

class SimpleParser extends RegexParsers {
  def word: Parser[String]    = """[a-z]+""".r ^^ { _.toString }
  def number: Parser[Int]    = """(0|[1-9]\d*)""".r ^^ { _.toInt }
  def freq: Parser[WordFreq] = word ~ number        ^^ { case wd ~ fr => WordFreq(wd,fr) }
}

object TestSimpleParser extends SimpleParser {
  def main(args: Array[String]): Unit = {
    println(parse(word, "johnny come lately"))

    parse(word, "johnny come lately") match {
      case Success(matched,_) => println(matched)
      case Failure(msg,_) => println("FAILURE: " + msg)
      case Error(msg,_) => println("ERROR: " + msg)
    }

    parse(freq, "johnny 121") match {
      case Success(matched,_) => println(matched)
      case Failure(msg,_) => println("FAILURE: " + msg)
      case Error(msg,_) => println("ERROR: " + msg)
    }
  }
}

class Arith extends JavaTokenParsers {
  var env: mutable.Map[String, Double] = mutable.Map.empty

  def identifier: Parser[String] = """[a-zA-Z_]+""".r

  def expr: Parser[Double] = term~rep("+"~term | "-"~term) ^^ {
    case term~ops =>
      ops.foldLeft(term) {
        case (a, "+"~b) => a + b
        case (a, "-"~b) => a - b
      }
  }
  def term: Parser[Double] = factor~rep("*"~factor | "/"~factor) ^^ {
    case factor~ops =>
      ops.foldLeft(factor) {
        case (a, "*"~b) => a * b
        case (a, "/"~b) => a / b
      }
  }
  def factor: Parser[Double] = (
    floatingPointNumber                  ^^ { _.toDouble }
  | ("-" | "+")~floatingPointNumber      ^? ({
      case "-"~v => -v.toDouble
      case "+"~v => v.toDouble
    }, { i => s"unexpected error [$i]" })
  | identifier                           ^? ({ case i if env.contains(i) => env(i) }, { i => s"$i is not found in context"})
  | ("-" | "+")~identifier               ^? ({
      case "-"~v if env.contains(v) => -env(v)
      case "+"~v if env.contains(v) => env(v)
    }, { case _~i => s"$i is not found in context" })
  | "("~expr~")"        ^? ({
      case "("~v~")" => v
    }, { i => s"unexpected error [$i]"})
  )
}

object TestArith extends Arith {
  def main(args: Array[String]): Unit = {
    env += "a" -> 5
    env += "b" -> 6
    env += "c" -> 7

    assert(parseAll(expr, "2 * (3 + 7) * a + (b + c) * c").get == 191.0)
    assert(parseAll(expr, "2 * (3 + 7) * (a + (b + c) * c)").get == 1920.0)
    assert(parseAll(expr, "a + 4").get == 9.0)
    assert(parseAll(expr, "((a) + (4))").get == 9.0)
    assert(parseAll(expr, "a / 20 + 10 + a * b + c * 10").get == 110.25)
    assert(parseAll(expr, "+5").get == 5.0)
    assert(parseAll(expr, "-a").get == -5.0)

    parseAll(expr, "2 * (3 + 7)") match {
      case Success(matched,_) => println(matched)
      case Failure(msg,_) => println("FAILURE: " + msg)
      case Error(msg,_) => println("ERROR: " + msg)
    }

    parseAll(expr, "2 * (3 + 7))") match {
      case Success(matched,_) => println(matched)
      case Failure(msg,_) => println("FAILURE: " + msg)
      case Error(msg,_) => println("ERROR: " + msg)
    }

    assert(!parseAll(expr, "2 * (3 + 7))").successful)
    assert(!parseAll(expr, "(2 * (3 + 7)").successful)

    parseAll(expr, "-d") match {
      case Success(matched,_) => println(matched)
      case Failure(msg,_) => println("FAILURE: " + msg)
      case Error(msg,_) => println("ERROR: " + msg)
    }
  }
}

object TestArith2 {
  def main(args: Array[String]): Unit = {
    val a = new Arith
    a.env ++= Map(
      "a" -> 5,
      "b" -> 6,
      "c" -> 7
    )

    assert(a.parseAll(a.expr, "2 * (3 + 7) * a + (b + c) * c").get == 191.0)
    assert(a.parseAll(a.expr, "2 * (3 + 7) * (a + (b + c) * c)").get == 1920.0)
    assert(a.parseAll(a.expr, "a + 4").get == 9.0)
    assert(a.parseAll(a.expr, "((a) + (4))").get == 9.0)
    assert(a.parseAll(a.expr, "a / 20 + 10 + a * b + c * 10").get == 110.25)

    assert(!a.parseAll(a.expr, "2 * (3 + 7))").successful)
    assert(!a.parseAll(a.expr, "(2 * (3 + 7)").successful)
    assert(!a.parseAll(a.expr, "d").successful)
  }
}
