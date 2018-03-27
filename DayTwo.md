# Day Two
File IO, http requests, etc.

# Notes

## General stuff

* file IO is pretty easy. User `scala.io.Source.fromFile` to read files.
* simple HTTP requests are _very_ easy too... `scala.io.Source.fromURL`.
* regex is nice and lightweight

## Functional Programming

* pass functions around
* can use notation like `(1 to 10) map { "x" * _ }  foreach { println(_) }` without `.`s
* you can curry functions

## Collections

* many have mutable and immutable variants
* `scala.collection.{mutable, immutable}.Map`
* `Predef` aliases immutable structures in the global namespace
* sequences, maps, sets
* `val iter = collection.iterator; while (iter.hasNext) { process iter.Next() }`
* `Seq` and `IndexedSeq` traits. i.e. `ArrayBuffer` has indexes, but a linked list does not
* `set.SameElements(other)` instead of `set == other`
* create `List`s like `head :: tail` i.e. `1 :: List(2, 3)` or `1 :: 2 :: 3 :: Nil`
* `zip` and `zipAll` are pretty useful. work as expected.
`List("dustin", "cailey") zip List(28, 26) == List(("dustin", 28), ("cailey", 26))`
* most collections have a `par` method for parallel processing. this yields a `Par<Type>`
that is incompatible with the non parallel type. convert it back with `.seq` i.e.
`coll.par.filter(_ % 2 == 0).seq` 

## Pattern Matching and Destructuring

* this is very similar to rust and other languages inspired by functional programming
* types can be matched on too
```scala
val s = "-123"
var ch = '-'
var sign = 0
for (ch <- s.toCharArray) {
    var digit = 0
    ch match {
      case _ if Character.isDigit(ch) => digit = Character.digit(ch, 10)
      case '+' => sign = 1
      case '-' => sign = -1
      case _   => sign = 0
    }
    // do something with digit and sign
}

// fancy list matching. this is really cool
val lst = List(1, 2, 3, 4, 5, 6)
val str = lst match {
  case 0 :: Nil => 0
  case x :: y :: Nil => s"$x $y"
  case x :: y :: z :: Nil => s"$x $y $z"
  case x :: y :: z :: tail => s"$x $y $z ..."
  case _ => "?"
}
// str = "1 2 3 ..."
```


