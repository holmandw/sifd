package sifd
import scala.concurrent.Future
import scala.io.Source

class DayTwo {
  val wordsFile = "/usr/share/dict/words"
  val url = "https://raw.githubusercontent.com/holmandw/sifd/master/README.md"
  def pwd(): Unit = {
    // imports can be anywhere
    import scala.sys.process._
    val result = "pwd".!!
    println(result)
  }

  def head(n: Integer = 10): Unit = {
    val source = Source.fromFile(wordsFile)
    val words = source.mkString.split("""\s+""")
    for (i <- 0 until n) {
      println(words(i))
    }
  }

  def getProjectReadMe: Array[String] = {
    val source = Source.fromURL(url, "UTF-8")
    source.mkString.split("""\n+""")
  }

  def findLinesWithNumbers(): Unit = {
    val searchText =
      """
        |Nobody expects the Spanish Inquisition.
        |The answer is 42.
        |So long and thanks for all the fish.
        |Allons-y Alonso! Doctor 11
      """.stripMargin // this is kinda fun, eh
    // kind of a crappy regex...
    val rg = "[\\w\\s\\-\\!]*\\d+[\\w\\s]*".r
    for (m<- rg.findAllIn(searchText)) {
      println(m)
    }
  }
  def functionalFun(): Unit = {
    // Object methods
    import scala.math._
    val f = floor _
    val floorPi = f(Pi)
    println(f"Pi ($Pi%.6f...) rounded down is $floorPi%.2f")
    // Class/instance methods
    val ch: (String, Integer) => Char = _.charAt(_)
    println(s"char at index 1 == ${ch("hello", 1)}")
    // Anonymous functions
    // It's probably not good style to mix `.` and infix(?) notation...
    val tripled10 = (1 to 10).toArray[Int] map { 3 * (_: Int) }
    tripled10.foreach(println(_))
    (1 to 10) map { "x" * _ }  foreach { println(_) }
    // Closures
    def mulBy(f: Integer) = (n: Integer) => f * n
    val doubler = mulBy(2)
    println(s"16 doubled is ${doubler(16)}")
  }

  def controls(): Unit = {
    def runThread(f: => Unit ): Unit = {
      new Thread {
        override def run() { f }
      }.start()
    }
    runThread { println("running in a new thread") }
  }

  def colls(): Unit = {
    // Fancy matching and destructuring
    def sum(lst: List[Int]): Int = lst match {
      case Nil => 0
      case h :: t => h + sum(t)
    }
    println(sum(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
    // sets work as expected
    val s = Set(1, 2, 3, 4, 5) + 1 // creates a `Set` and a second `Set` after adding the 1 with `+ 1`
    // s.add(6) // only works if s is `scala.collection.mutable.Set`
    println(s)
    val fLeft = (0 /: List(1, 2, 3, 4, 5))(_-_)
    val fRight = List(1, 2, 3, 4, 5).foldRight(0)(_-_) // TODO figure out `:\` syntax for fold right
    println(fLeft, fRight)
    // zipping
    val names = List("dustin", "cailey")
    val ages = List(28, 26, 25)
    // 25 is ignored
    val people = names zip ages
    val withDefault = names.zipAll(ages, "nobody", -1)
    println(people)
    println(people.zipWithIndex)
    println(withDefault)
    // fold and map
    println((0 until 10).scanLeft(0)(_+_))
    // streams are lazy, immutable sequences
    def numsFrom(n: BigInt): Stream[BigInt] = n #:: numsFrom(n + 1)
    val fromTen = numsFrom(10)
    println(fromTen) // tail is not computed until requested.
    println(fromTen.take(10).force)
    // parallelism on collections
    for (i <- (0 until 10).par) print(i)
    println()
  }

  def matching(): Unit = {
    // fancy list matching
    val lst = List(1, 2, 3, 4, 5, 6)
    val str = lst match {
      case 0 :: Nil => 0
      case x :: y :: Nil => s"$x $y"
      case x :: y :: z :: Nil => s"$x $y $z"
      case x :: y :: z :: tail => s"$x $y $z ..."
      case _ => "?"
    }
    println(str)

    // matching in a loop
    import scala.collection.JavaConverters._
    for ((k, v) <- System.getProperties.asScala.take(5)) {
      println(s"$k: $v")
    }
    val p0 = Some(new Person("dustin", 28))
    val p1 = None
    val p1Get = p1.getOrElse(p0)
    println(s"$p0 $p1 $p1Get")
    // can be done with matching, but unnecessary

  }

  def futures(): Unit = {

    def wait42(): Int = { Thread.sleep(1000); 42 }
    import scala.concurrent.ExecutionContext.Implicits.global
    val f1 = Future { wait42() }
    val f2 = Future { wait42() }
    val combined = for (v1 <- f1; v2 <- f2) yield v1 + v2
//    val combinedmap = f1.flatMap(v1 => f2.map(v2 => v1 + v2))
    Thread.sleep(1000)
    println(combined)
    import scala.concurrent.blocking
    val f = Future {
      blocking {
        getProjectReadMe
      }
    }
    println(f.value) // None because we aren't waiting
  }
}
