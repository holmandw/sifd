package sifd

class Person(var name: String, var age: Integer) {
  def this() = {
    this("", 0)
  }
  override def toString: String = s"name: $name, age: $age"
}


class Employee(name: String, age: Integer, var salary: Integer) extends Person(name, age) {
  val employeeID: Integer = Employee.uniqueID()
  def this(p: Person, salary: Integer) = this(p.name, p.age, salary)
  def this(p: Person) = this(p, 0)
  override def toString: String =  s"name: $name, age: $age, id: $employeeID, salary: $salary"
}


// provide some static methods for class Employee
object Employee {
  private var currID = 0
  def uniqueID(): Integer = { currID += 1; currID }
}

// No enums, but objects can be like enums
object Job extends Enumeration {
  val engineering, sales, management, misc = Value
}