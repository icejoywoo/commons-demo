package fizzbuzz

// https://www.jianshu.com/p/beb83c98a358
//trait Rule {
//  def apply(n: Int): String
//}
//
//case class Times(n: Int, word: String) extends Rule {
//  def apply(m: Int): String = if (m % n == 0) word else ""
//}
//
//case class Contains(n: Int, word: String) extends Rule {
//  def apply(m: Int): String =
//    if (m.toString.contains(n.toString)) word else ""
//}
//
//class Default() extends Rule {
//  def apply(m: Int): String = m.toString
//}
//
//object Default {
//  def default = new Default
//}
//
//case class AllOf(times: Times*) extends Rule {
//  def apply1(n: Int): String = {
//    val result = new StringBuilder
//    times.foreach(t =>
//      result.append(t(n))
//    )
//    times foreach { result append _(n)}
//    result.toString
//  }
//
//  // v2
//  def apply2(n: Int): String = {
//    val result = new StringBuilder
//    times foreach { result append _(n)}
//    result.toString
//  }
//
//  def apply3(n: Int): String = {
//    times.foldLeft(""){ (acc, t) => acc + t(n) }
//  }
//
//  def apply(n: Int): String = {
//    times.foldLeft(""){ _ + _(n) }
//  }
//}
//
//case class AnyOf(times: Times*) extends Rule {
//  def apply(m: Int): String =
//    times.map(_(m)).filterNot(_.isEmpty).headOption.getOrElse("")
//}

// clean version
object Matcher {
  type Matcher = Int => Boolean

  def times(n: Int): Matcher = _ % n == 0
  def contains(n: Int): Matcher = _.toString.contains(n.toString)
  def always(bool: Boolean): Matcher = _ => bool
}

object Action {
  type Action = Int => String

  def to(str: String): Action = _ => str
  def nop: Action = _.toString
}

object Rule {
  import Matcher.Matcher
  import Action.Action

  type Rule = Int => String

//  def times(n: Int, word: String): Rule =
//    m => if (m % n == 0) word else ""
//
//  def contains(n: Int, word: String): Rule =
//    m => if (m.toString.contains(n.toString)) word else ""
//
//  def default: Rule = m => m.toString

  def atom(matcher: => Matcher, action: => Action): Rule =
    m => if(matcher(m)) action(m) else ""

  def allof(rules: Rule*): Rule =
    m => rules.foldLeft("")(_ + _(m))

  def anyof(rules: Rule*): Rule =
    m => rules.map(_(m)).filterNot(_.isEmpty).headOption.getOrElse("")
}


