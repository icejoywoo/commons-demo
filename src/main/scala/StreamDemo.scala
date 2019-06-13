object StreamDemo {
  def fibFrom(a: Long, b: Long): Stream[Long] = a #:: b #:: fibFrom(b, a+b)
  def fib(): Stream[Long] = fibFrom(1, 1)

  def main(args: Array[String]): Unit = {
    val s = 1 #:: 2 #:: 3 #:: Stream.empty
    println(s)

    val f = fib()
    println(f.take(10).toList)
  }
}
