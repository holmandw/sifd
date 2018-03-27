# Day One

Code for this day is at `src/main/scala/sifd/DayOne.scala`. 

# General Notes

* an executable application has a method called `main` and it must
reside in an `object`. An `object` is like a singleton class- one
is instantiated at runtime and it cannot be re-instatiated.
alternatively, an object can extend `App`. it's body is called at runtime.
* `val x = 0` : x is immutable
* `var x = 0` : x is a mutable variable
* methods have to be defined within a `class` or `object`
* methods defined within a class must be called on an instance (no static
methods)
* no fancy init methods. initializers are part of the class definition:
`class Foo(n: Integer) {}` and is used like `val foo = new Foo(42)`
* string interpolation like `f"$x + 1 == ${x + 1}"` is preferred. printf and
string concatenation also works, but is not recommended.
* `C` style loops don't exist. use `for (i <- 0 to 100)` instead.
`(i <- 0 until 100)` excludes the last value, `to` is inclusive
* `while` works as expected
* prefer functional operations to iterative. i.e. `1.to(10).sum` instead
of iterating to generate the sum
* no `++` or `--` operators
* implicit returns are favored
* procedure vs function: `def f(s: String) { print(s) }` vs
`def wrapped(s: String): String = { f"|$s|"}`
* optional parameters are similar to python:
`def add(a: Integer, b: Integer = 0): Integer = { a + b}` can be used like
`val two = add(2)`
* values can be evaluated lazily:
`lazy val words = scala.io.Source.fromFile(...)` which is pretty cool
* class inheritance is pretty straightforward; mixins are also a thing here
* few methods to override superclasses
