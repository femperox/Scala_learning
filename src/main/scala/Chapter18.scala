object Chapter18 extends App
{

  //1. Определите неизменяемый класс Pair[T, S] с методом swap, воз-
  //вращающим новую пару, где компоненты поменяны местами.



  class Pair[T, S](val first:T, val second:S)
  {
    def swap = new Pair(second, first)

    override def toString: String = s"($first, $second)"
  }

  var p = new Pair(1, "one")
  println(p)
  val p2 = p.swap
  println(p2)



  //2. Определите изменяемый класс Pair[T] с методом swap, который
  //меняет компоненты пары местами.
  class Pair2[T](override val first: T, override val second:T) extends Pair(first, second)
  {
    override def swap = new Pair2(second, first)
  }

  val p1 = new Pair2(1, 1)
  println(p1)
  println(p1.swap)


  //3. Для класса Pair[T, S] напишите обобщенный метод swap, ко-
  //торый принимает пару в виде аргумента и возвращает новую
  //пару с компонентами, поменянными местами.
  def swapAll[T, S](pair: Pair[T,S]):Pair[S, T] = new Pair(pair.second, pair.first)

  println(swapAll(p2))



  //4. Напишите обобщенный метод middle, возвращающий средний
  //элемент из любого экземпляра Iterable[T]. Например, вызов
  //middle("World") должен вернуть 'r'.

  def middle[T](e: Iterable[T]): T = e.slice( (e.size / 2).toInt , (e.size / 2).toInt+1 ).head


  //5. Для изменяемого класса Pair[S, T] используйте механизм огра-
  //ничения типа, чтобы определить метод swap, который можно
  //вызывать с параметрами одного типа.

  def swapAll2[T, S](pair: Pair[T,S])(implicit ev: T =:= S) =  { new Pair(pair.second, pair.first) }
