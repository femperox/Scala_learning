import scala.collection.mutable

object Chapter4 {

  //1. Ассоциативный массив с вещами и их стоимостью, второй такой же, но со скидкой 10%
  def items
  {  val items1 = Map("pencil" -> 10, "pen" -> 20, "notebook" -> 56)
     println(items1)

    val items2 = for { (k, v) <- items1} yield (k, v*0.9)
    println(items2)
  }

  //2. Прочитать из файла слова. вывести их количество в алфавитном порядке
  def fileReader
  {
    val in = new java.util.Scanner(new java.io.File("files\\Chapter4_file.txt"))
    var map = mutable.SortedMap[String, Int]()

    while (in.hasNext())
    {   var item = in.next()
        map(item) = map.getOrElse(item, 0) + 1
    }
    println(map)
  }

  //3. Напишите функцию, возвращающую пару, содержащую наименьшее и наибольшее значения
  def minmax(values: Array[Int]): (Int, Int)=
  {
    (values.max, values.min)
  }

  //4. Напишите функцию, возвращающую тройку, содержащую счетчик значений, меньших v,
  //равных v и больших v.
  def lteqgt(values: Array[Int], v:Int): (Int, Int, Int) =
  {
    (values.count(_<v), values.count(_==v), values.count(_>v))
  }

  def main(args: Array[String]):Unit =
  {
    fileReader

    println(minmax(Array(1,2,3,4,5)))
    println(lteqgt(Array(1,2,3,4,5), 4))
  }

}
