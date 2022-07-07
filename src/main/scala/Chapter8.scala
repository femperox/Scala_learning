object Chapter8 extends App
{

  //1. Определите класс CheckingAccount, наследующий класс BankAccount,
  //который взимает $1 комиссионных за каждую операцию
  //пополнения или списания.
  class BankAccount(initialBalance: Double) {
    private var balance = initialBalance
    def currentBalance = balance
    def deposit(amount: Double) = { balance += amount; balance }
    def withdraw(amount: Double) = { balance -= amount; balance }
  }

  class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance)
  {
    override def withdraw(amount: Double): Double = super.withdraw(amount+1)

    override def deposit(amount: Double): Double =
    { super.deposit(amount)
      super.withdraw(1)
    }
  }



  //2.Определите класс SavingsAccount, наследующий класс BankAccount
  //из предыдущего упражнения, который начисляет про-
  //центы каждый месяц (вызовом метода earnMonthlyInterest)
  //и позволяет бесплатно выполнять три операции зачисления
  //или списания каждый месяц. Метод earnMonthlyInterest дол-
  //жен сбрасывать счетчик транзакций.
  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance)
  {
    private var p_count = 3

    def count = p_count

    def earnMonthlyInterest: Double =
    { val d = super.deposit(super.currentBalance*0.1)
      p_count = 3
      d
    }

    override def deposit(amount: Double): Double =
    { super.deposit(amount)
      if (p_count == 0) super.withdraw(1)
      p_count -= 1
      super.currentBalance
    }

    override def withdraw(amount: Double): Double =
    { if (p_count > 0) {p_count -= 1; super.withdraw(amount)}
    else super.withdraw(amount+1)
    }
  }


  //3. Определите абстрактный класс элемента Item с методами
  //price и description. Определите подкласс простого элемента
  //SimpleItem, представляющий элемент, цена и описание которо-
  //го определяются в конструкторе. Используйте тот факт, что
  //объявление val может переопределять def. Определите класс
  //Bundle – пакет элементов, содержащий другие элементы. Его
  //цена должна определяться как сумма цен элементов в паке-
  //те. Реализуйте также механизм добавления элементов в пакет
  //и соответствующий метод description.

  abstract class Item
  {
    def price: Double

    def description: String
  }

  class SimpleItem(val price: Double, val description: String)

  class Bundle(var bundle: Array[SimpleItem]) extends Item
  {
    def price:Double = (for {b <- bundle} yield b.price).sum

    def add(item: SimpleItem): Array[SimpleItem] = { bundle = bundle :+ item; bundle}

    def description: String = (for {b<-bundle} yield s"${b.description} - ${b.price}").mkString("\n")
  }

  //4. Спроектируйте класс точки Point, значения координат x и y
  //которой передаются конструктору. Реализуйте подкласс точки
  //с подписью LabeledPoint, конструктор которого будет прини-
  //мать строку с подписью и значения координат x и y,

  class Point(val x:Double, val y:Double)

  class LabeledPoint(val label:String, x:Double, y:Double) extends Point(x:Double, y:Double)


  //5. Определите абстрактный класс геометрической фигуры Shape
  //с абстрактным методом centerPoint и подклассы прямоуголь-
  //ника и окружности, Rectangle и Circle. Реализуйте соответствующие
  //конструкторы в подклассах и переопределите метод
  //centerPoint в каждом подклассе.

  abstract class Shape
  {
    def centerPoint: Point
  }

  class Rectangle(var p1:Point, var p2:Point) extends Shape
  {
    def centerPoint = new Point( (p1.x + p2.x)/2, (p1.y + p2.y)/2)
  }

  class Circle(var centerPoint: Point, var radius: Double) extends Shape






}

