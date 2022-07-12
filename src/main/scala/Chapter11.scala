object Chapter11 extends App
{
  //1. Реализуйте класс Money с полями для выражения суммы в дол-
  //ларах и центах. Реализуйте операторы +, -, а также операторы
  //сравнения == и <. Например, выражение Money(1, 75) + + Money(0,
  //50) == Money(2, 25) должно возвращать true.

  class Money(var dollar: Int, var cent: Int)
  {
     def +(other:Money) =
     { var additional = 0
       var cents = this.cent + other.cent

       if (this.cent + other.cent >= 100)
       { additional = 1
         cents = cents - 100
       }

       new Money(this.dollar+other.dollar+additional, cents)
     }

     def -(other:Money) =
     { var additional = 0
       var cents = this.cent - other.cent

       if (this.cent - other.cent <= 0)
       { additional = -1
         cents = 100 + cents
       }

       new Money(this.dollar-other.dollar+additional, cents)
     }

     def ==(other: Money) =
     {
       (this.dollar == other.dollar) && (this.cent == other.cent)
     }

     def <(other: Money) =
     {
       if (this.dollar <= other.dollar)
         if (this.cent <= other.cent) true
         else false
       else false
     }

    override def toString: String = s"${this.dollar}.${this.cent}" + "$"
  }

  object Money
  {
    def apply(dollar:Int, cent:Int) = new Money(dollar, cent)
  }

  //2. Реализуйте операторы конструирования HTML-таблицы. На-
  //пример, выражение
  // Table("Java", "Scala" , "Gosling" , "Odersky" , "JVM", "JVM, .NET")
  //должно возвращать
  // <table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling...

  object Table
  {
    def apply(table: String*): Option[String] =
    {
      if (table.isEmpty) None
      else {
        val els = for {el <- table} yield s"<tr><td>$el</td></tr>"
        Some(s"<table>${els.mkString}</table>")
      }
    }
  }

  //3. Реализуйте класс ASCIIArt, объекты которого содержат фигуры,
  //Добавьте операторы для объединения двух фигур ASCIIArt по
  //горизонтали или по вертикали. Выберите операторы с соответствующими
  //приоритетами.

  trait StringForASCII
  {
    val kitty: String
    val speech_buble: String

    def | = (for { k <- kitty
                   s <- speech_buble
                   } yield k+s).mkString
  }

  class ASCIIArt(val art: String)
  {
    def |(other:ASCIIArt): ASCIIArt=
    {
      val s= this.art.lines().toArray
      val o= other.art.lines().toArray

      var newS = ""
      for (i <- 0 until  math.max(s.length, o.length))
      {
        if (i >= o.length) newS += s"\n${s(i)}"
        else if (i >= s.length) newS += s"\n${o(i)}"
        else newS += s"\n${s(i)}${o(i)}"
      }

      new ASCIIArt(newS)
    }

    def -(other: ASCIIArt):ASCIIArt = new ASCIIArt(this.art + other.art)

    override def toString: String = (for {s <- this.art} yield s).mkString
  }


  val kitty =
    """
    /\_/\
   ( ' ' )
    ( - )
    | | |
   (__|__)
    """

  val speech_buble =
    """
    / Hello \
    < Scala |
    \ Coder /
    """

  val ascii1 = new ASCIIArt(kitty)
  val ascii2 = new ASCIIArt(speech_buble)
  println(ascii2 - (ascii1 | ascii2))

  //3. Реализуйте класс Matrix – выберите сами, какую матрицу реализовать:
  //2 × 2, квадратную произвольного размера или матри-
  //цу m × n. Реализуйте операции + и *. Последняя должна также
  //позволять выполнять умножение на скаляр, например mat * 2.
  //Единственный элемент матрицы должен быть доступен как
  //mat(row, col).

  class Matrix(val n: Int, val m:Int, var els: Array[Array[Float]])
  {

    def ?=(other: Matrix) = (this.m == other.m) && (this.n == other.n)



  }



}
