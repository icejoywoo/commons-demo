
object RDD {
  implicit def rddToPairRDDFunctions[K, V](rdd: RDD[(K, V)]): PairRDDFunctions[K, V] = {
    new PairRDDFunctions(rdd)
  }
}

class RDD[T] {
  def filter(): Unit = {
    println("filter")
  }
}

class PairRDDFunctions[K, V](self: RDD[(K, V)]) {
  def combineByKey(): Unit = {
    println("combineByKey")
  }
}

object RDDImplicitDemo extends App {
  val rdd = new RDD[Int]
  rdd.filter()

  val pairRdd = new RDD[(Int, Int)]

  pairRdd.filter()
  pairRdd.combineByKey()
}
