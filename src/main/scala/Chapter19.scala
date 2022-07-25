object Chapter19 extends App
{
  //1.Реализуйте класс Bug, моделирующий жука, перемещающе-
  //гося по горизонтальной линии. Метод move перемещает жука
  //в текущем направлении, метод turn изменяет направление на
  //противоположное, а метод show выводит текущую позицию.
  //Обеспечьте возможность составления цепочек из вызовов этих
  //методов. Например, цепочка:
  // bugsy.move(4).show().move(6).show().turn().move(5).show()
  // должна вывести 4 10 5.


  class Bug(var place: Integer = 0, var direction: Boolean = true)
  {

    def ??(x: Int) = { if (direction) x  else -x}

    def move(distance: Int): this.type = {place += ??(distance); this}

    def turn() = {direction = !direction; this}

    def show() = {print(place+" "); this}
  }

  val bugsy = new Bug()
  bugsy.move(4).show().move(6).show().turn().move(5).show()


  // 2. Напишите функцию printValues с тремя параметрами: f, from
  //и to, выводящую все значения f, для входных значений в за-
  //данном диапазоне от from до to. Здесь f должен быть любым
  //объектом с методом apply, получающим и возвращающим зна-
  //чение типа Int. Например:
  //printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55),3,6)
  //// Выведет 3 5 8 13

  def printValues(f: {def apply(x:Int): Int}, from: Int, to: Int) =
  {
    println()
    for (i <- from to to) print(s"${f(i)} ")
  }

  printValues(Array(1, 1, 2, 3, 5, 8, 13, 21, 34, 55),3,6)
}
