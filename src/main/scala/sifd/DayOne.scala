package sifd

class DayOne(val message: String) {
  def mul(a: Integer, b: Integer): Integer = {
    a * b
  }

  def rangeSum(n: Integer): Integer = {
    0.to(n).sum // TODO figure out why parens breaks this
  }

  // iteration/ranges
  def printRange(n: Integer) {
    // inclusive of `n`
    for (i <- 0 to n) {
      print(f"$i ")
    }
    println()
  }

  def printRangeWhile(n: Integer) {
    var i = 0
    // expected exclusive range behavior
    while (i < n) {
      print(f"$i ")
      i += 1
    }
    println()
  }

  def printRangeForEach(n: Integer) {
    0.to(n).foreach(printf("%d ", _))
    println()
  }

  def printRangeMkString(n: Integer): Unit = {
    println(0.to(n).mkString(" "))
  }

  def printRangeReversed(n: Integer): Unit = {
    0.to(n).reverse.foreach(printf("%d ", _))
    println()
  }

  def stringInterpolation() {
    // string interpolation
    val one = 1
    println("one   == " + one)
    printf("two   == %d\n", one + 1)
    println(f"three == ${one + 2}") // preferred
  }

  def inclusiveArr(n: Integer): Array[Integer] = {
    0.to(n).map(_.asInstanceOf[Integer]).toArray
  }

  def exclusiveArr(n: Integer): Array[Integer] = {
    0.until(n).map(_.asInstanceOf[Integer]).toArray
  }

  def maps(): Unit = {
    val m = scala.collection.mutable.Map('a' -> 0, 'b' -> 1)
    m += 'c' -> 2
    println(m)
    val d = m.get('d') // option type
    println(s"'d' == $d")
    val sm = scala.collection.SortedMap("Bob" -> 3, "Cindy" -> 8, "Alice" -> 10, "Fred" ->  7)
    println(sm)
  }
}


