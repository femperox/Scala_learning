import scala.collection.mutable.ArrayBuffer

object Chapter3
{

  //1. Запись в массив целые числа [0, n)
  def insertion(n: Int):List[Int] = List((for {i <- 0 until n} yield i) : _*)

  //2. Поменять местами смежные элементы
  def swap(a: Array[Int]): Array[Int] =
  {
    for (i <- 0 until a.length by 2) {

      if (i+1 < a.length) {

        var tmp = a(i)
        println(tmp)
        a(i) = a(i+1)
        a(i+1) = tmp
      }
    }
    a
  }

  //3. То же, что и выше, но с for-comprehension
  def swap_2(a:Array[Int]): Array[Int] = {
    Array(( for {i <- 0 until a.length
                 b = if (i+1 < a.length && i%2==0) a(i+1) else if (i-1 >=0 && i!=a.length-1) a(i-1) else a(i)
                 } yield b): _*)
  }

  //4. Дан массив целых чисел, создайте новый массив, в котором
  //сначала будут следовать положительные значения из ориги-
  //нального массива, в оригинальном порядке, а за ними отри-
  //цательные и нулевые значения, тоже в оригинальном порядке.
  def monotone(a: Array[Int]):Array[Int] =
  {
     val indxs = Array.concat(a.filter(_ > 0), a.filter(_ < 0), a.filter(_ == 0))
     indxs
  }

  //5. Среднее значение элементов массива Double
  def mean(a: Array[Double]): Double = a.sum/a.length

  //6. Обратный отсортированный порядок Array[Int]
  def sort_reverse(a: Array[Int]): Array[Int] = a.sortWith(_ < _)

  //7. Напишите фрагмент программного кода, выводящий значения
  //всех элементов из массива, кроме повторяющихся
  def distinct[T](a:Array[T]) = println(a.distinct.mkString(" "))
  def distinct_2[T](a:ArrayBuffer[T]) = println(a.distinct.mkString(" "))

  //8. Буффер целых чисел, все отриц знач, кроме первого
  def remove_negative(a:ArrayBuffer[Int]): ArrayBuffer[Int] =
  {
    val neg = a.filter(_ < 0).tail
    a.filter(!neg.contains(_))
  }

  //9. Создайте коллекцию всех часовых поясов, возвращаемых
  //методом java.util.TimeZone.getAvailableIDs для Америки. От-
  //бросьте префикс "America/" и отсортируйте результат.
  def america_timezone =
  {
      val timezones = java.util.TimeZone.getAvailableIDs
      val us_timezones = for { t <- timezones
                               if t.contains("America/") || t.contains("US/")
                             }   yield t.replaceAll("America/|US/", "")
      println(us_timezones.sortWith(_ < _).mkString("\n"))
  }

  def main(args: Array[String]): Unit =
  {
      val a = Array(1, -2, 0, 0, 0, 0, 3, -4, -5)
      val a1 = ArrayBuffer(-1, 0, 1, 2, -2)

      america_timezone
  }

}
