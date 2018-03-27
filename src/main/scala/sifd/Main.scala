package sifd

import java.time.LocalDateTime

// Singleton class
object Main extends App {
  // static methods are run when the application starts
  dayOne()
  dayTwo()
  println(LocalDateTime.now)
  def dayOne(): Unit = {
    // still have to use new. `val` creates an immutable value
    val d = new DayOne("foobar")
    val six = d.mul(2, 3)
    assert(six == 6)
    assert(d.message == "foobar")
    // cannot reassign values
    // d.message = "nope" // compile time error
    // if definition was `class DayOne(var message: String)...`, this would work (assuming `d` is mutable)
    d.printRange(10)
    d.printRangeWhile(10)
    d.printRangeForEach(10)
    d.printRangeMkString(10)
    d.printRangeReversed(10)
    println(d.rangeSum(10))
    d.stringInterpolation()
    val arrIn = d.inclusiveArr(10)
    println(arrIn.mkString("[", ",", "]"))
    val arrEx = d.exclusiveArr(10)
    println(arrEx.mkString("[", ",", "]"))
    // Map stuff
    d.maps()
    // Classes and inheritance
    val default = new Person()
    println(default)
    val p = new Person("Dustin", 28)
    println(p)
    val e0 = new Employee(p.name, p.age, 123456789)
    val e1 = new Employee(p, 123456789)
    println(e0)
    println(e1)
  }

  def dayTwo(): Unit = {
    val d = new DayTwo()
    d.pwd()
    d.head()
    /*
    for (line <- d.getProjectReadMe) {
      println(line)
    }
    */
    d.findLinesWithNumbers()
    d.functionalFun()
    d.controls()
    d.colls()
    d.matching()
    d.futures()
  }


}
