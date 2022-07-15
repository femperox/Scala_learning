import scala.math.sqrt
import scala.reflect.ClassTag

object Chapter14 extends App
{

  //1. Используя сопоставление с образцом, напишите функцию
  //swap, которая принимает пару целых чисел и возвращает ту
  //же пару, поменяв компоненты местами\

  def swap(pair:(Int, Int)) =
    pair match
    {
      case (x, y) => (y, x)
      case _ => ()
    }


  // 2. Используя сопоставление с образцом, напишите функцию
  //swap, которая меняет местами первые два элемента массива,
  //если он имеет длину не меньше двух.

  def swapM[T:ClassTag](a: Array[T]) =
    a match
    {
      case Array(x, y, rest @ _*) => Array(y, x) ++ rest.toArray
      case _ => "Can't do that"
    }


  // 3.Для представления деревьев, хранящих значения только в листьях,
  //можно использовать списки. Например, список ((3 8) 2 (5))
  //описывает дерево. В этом случае одни элементы списка будут числами, а дру-
  //гие – списками. Однако в Scala нельзя создавать разнородные
  //списки, поэтому придется использовать List[Any]. Напишите
  //функцию leafSum для вычисления суммы всех значений листьев,
  //используя сопоставление с образцом для отделения чисел
  //от списков.

  def leafSum(e: Any):Int =
    e match
    {
      case x:Int => x
      case (x:Int, y:Int) => x+y
      case _ => 0
    }



  // 4. Такие деревья лучше всего моделировать с применением case-
  //классов. Начните с бинарных деревьев.
  // Напишите функцию, вычисляющую сумму всех значений
  //листьев.

  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

  def leafSum2(t: BinaryTree): Int =
    t match
    {
      case Leaf(x) => x
      case Node(l, r) => leafSum2(l) + leafSum2(r)
    }


  // 5. Расширьте дерево из предыдущего упражнения, чтобы каждый
  //узел в нем мог иметь произвольное количество дочерних уз-
  //лов, и перепишите функцию leafSum. Дерево в упражнении 5
  //должно выражаться как
  // Node(Node(Leaf(3), Leaf(8)), Leaf(2), Node(Leaf(5)))

  case class Node2(sides: BinaryTree*) extends BinaryTree

  def leafSum3(t: BinaryTree): Int =
    t match
    {
      case Leaf(x) => x
      case Node(l, r) => leafSum3(l) + leafSum3(r)
      case Node2(f, rest @ _*) => leafSum3(f) + rest.map(leafSum3 _).sum
    }



  //6. Напишите функцию, вычисляющую сумму всех непустых зна-
  //чений в List[Option[Int]]. Не используйте выражения match.

  def sumOption(a: List[Option[Int]]): Int = a.map(_.getOrElse(0)).sum


  //7. Напишите функцию, получающую две функции типа Double =>
  //Option[Double] и конструирующую на их основе третью функ-цию того же типа. Новая функция должна возвращать None,
  //если любая из двух исходных функций вернет это значение.
  //Например:
  //def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
  //def g(x: Double) = if (x >= 0) Some(sqrt(Double)) else None
  //val h = compose(f, g) // // h(x) должна быть g(f(x))
  //Вызов h(2) должен вернуть Some(1), а вызовы h(1) и h(0) долж-
  //ны вернуть None.

  def compose( f: Double => Option[Double], g: Double => Option[Double]): Double => Option[Double] =
  {

    (x: Double) => f(x) match
    {
      case Some(y) =>  g(y) match {
                                     case Some(z) => Some(z)
                                     case _ => None
                                   }

      case _ => None
    }
  }

  def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
  def g(x: Double) = if (x >= 0) Some(sqrt(x)) else None
  val h = compose(f, g)
  println(h(0))



}
