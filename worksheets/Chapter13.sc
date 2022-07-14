import scala.reflect.ClassTag

//1. Напишите функцию, возвращающую для указанной стро-
//ки отображение индексов всех символов. Например, вызов
//indexes("Mississippi") должен вернуть ассоциативный массив,связывающий 'M'
// со множеством {0}, 'i' – со множеством {1,
//4, 7, 10} и т. д. Используйте изменяемый ассоциативный мас-
//сив, отображающий символы в изменяемые множества.
def indexes(s: String) =
{
  var m = scala.collection.mutable.Map[Char, List[Int]]()
   for (i <- 0 until s.length)
   {
     if (m.get(s(i)) == None) m(s(i)) = List(i)
     else m(s(i)) = m(s(i)) :+ i
   }

  m

}

// 2. Напишите функцию, превращающую массив значений Double
//в двумерный массив. Число колонок должно передаваться
//в виде параметра. Например, для Array(1, 2, 3, 4, 5, 6) и трех
//колонок функция должна вернуть Array(Array(1, 2, 3), Array(4,
//5, 6)). Используйте метод grouped.
def to2DArray[A:ClassTag](array: Array[A], cols: Int): Array[Array[A]] =
{
   array.grouped(cols).toArray
}

