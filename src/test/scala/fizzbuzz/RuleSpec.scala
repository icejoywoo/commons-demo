package fizzbuzz

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers, PropSpec}

class RuleSpec1 extends FunSpec with Matchers{
//  describe("World") {
//    import Default._
//
//    it ("times(3) -> Fizz") {
//      Times(3, "Fizz")(3 * 2) should be("Fizz")
//    }
//
//    it ("contains(3) -> Fizz") {
//      Contains(3, "Fizz")(13) should be("Fizz")
//    }
//
//    it ("default rule") {
//      default(2) should be("2")
//    }
//
//    it ("times(3) && times(5) -> FizzBuzz" ) {
//      AllOf(Times(3, "Fizz"), Times(5, "Buzz"))(3*5) should be("FizzBuzz")
//    }
//
//    it ("times(3) -> Fizz || times(5) -> Buzz" ) {
//      AnyOf(Times(3, "Fizz"), Times(5, "Buzz"))(3*5) should be("Fizz")
//      AnyOf(Times(3, "Fizz"), Times(5, "Buzz"))(5) should be("Buzz")
//    }
//  }

//  describe("World v2") {
//    import Rule._
//
//    it ("times(3) -> Fizz") {
//      times(3, "Fizz")(3 * 2) should be("Fizz")
//    }
//
//    it ("contains(3) -> Fizz") {
//      contains(3, "Fizz")(13) should be("Fizz")
//    }
//
//    it ("default rule") {
//      default(2) should be("2")
//    }
//
//    it ("times(3) && times(5) -> FizzBuzz" ) {
//      allof(times(3, "Fizz"), times(5, "Buzz"))(3*5) should be("FizzBuzz")
//    }
//
//    it ("times(3) -> Fizz || times(5) -> Buzz" ) {
//      anyof(times(3, "Fizz"), times(5, "Buzz"))(3*5) should be("Fizz")
//      anyof(times(3, "Fizz"), times(5, "Buzz"))(5) should be("Buzz")
//    }
//  }

  describe("using atom rule") {
    import Rule.atom
    import Matcher.times
    import Action.to

    it ("times(3) -> fizz" ) {
      atom(times(3), to("Fizz"))(3 * 2) should be("Fizz")
    }
  }
}

class RuleSpec extends PropSpec with TableDrivenPropertyChecks with Matchers {
  val specs = Table(
    ("n",         "expect"),
    (3,           "Fizz"),
    (5,           "Buzz"),
    (7,           "Whizz"),
    (3 * 5,       "FizzBuzz"),
    (3 * 7,       "FizzWhizz"),
    ((5 * 7) * 2, "BuzzWhizz"),
    (3 * 5 * 7,   "FizzBuzzWhizz"),
    (13,          "Fizz"),
    (35/*5*7*/,   "Fizz"),
    (2,           "2")
  )

  property("fizz buzz whizz") {
    val spec = Game.spec(3, 5, 7)
    forAll(specs) { spec(_) should be (_) }
  }
}
