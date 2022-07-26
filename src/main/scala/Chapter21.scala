object Chapter21 extends App
{

  //1.Определите оператор +%, добавляющий указанный процент
  //к значению. Например, выражение 120 +% 10 должно вернуть
  //132. Используйте неявный класс.

  //2. Определите оператор ! вычисления факториала, чтобы выра-
  //жение 5.! возвращало 120.

  object Operations
  {
      implicit class NumbersLike(val num: Double) extends AnyVal
      {

        def +%(num: Double): Double = this.num + this.num*num/100

        def !():Double = if (this.num>0) this.num * (this.num-1).! else 1

      }

  }

  import Operations.NumbersLike
  println(120 +% 10)
  println(5.!)




}
