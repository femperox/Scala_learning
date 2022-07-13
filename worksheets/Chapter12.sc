
//1. Напишите функцию values(fun: (Int) => Int, low: Int, high:
//Int), возвращающую коллекцию из значений в указанном диа-
//пазоне. Например, вызов values(x => x * x, -5, 5) должен вер-
//нуть коллекцию пар (-5, 25), (-4, 16), (-3, 9), ..., (5, 25).
def values(fun: (Int) => Int, low: Int, high: Int) = for(v <- low to high) yield (v, fun(v))


//2. Как получить наибольший элемент массива с помощью метода
//reduceLeft
def fMax(array: Array[Int]): Int = array.reduceLeft(_ max _)


//3. Реализуйте функцию вычисления факториала с помощью ме-
//тодов to и reduceLeft без применения цикла или рекурсии.
def fact(num: Int) = (1 to num).reduceLeft(_*_)


//4. Предыдущая реализация должна предусматривать специаль-
//ный случай, когда n < 1. Покажите, как избежать этого с помощью
//foldLeft
def fact2(num: Int) = (1 to num).foldLeft(0)(_*_)


//5. Напишите функцию largest(fun: (Int) => Int, inputs: Seq[Int]),
//возвращающую наибольшее значение функции внутри задан-
//ной последовательности. Например, вызов largest(x => 10 * x
//- x * x, 1 to 10) должен вернуть 25. Не используйте цикл
//или рекурсию.
def largest(fun: (Int) => Int, inputs: Seq[Int]) = fMax(inputs.map(fun(_)).toArray)

largest(x => 10*x - x*x, 1 to 10)


//6. Измените предыдущую функцию так, чтобы она возвращала
//входное значение, соответствующее наибольшему выходному
//значению. Например, вызов largestAt(fun: (Int) => Int, inputs:
//Seq[Int]) должен вернуть 5. Не используйте цикл или
//рекурсию.
def largest2(fun: (Int) => Int, inputs: Seq[Int]) = inputs.sortWith(fun(_) > fun(_)).head


//7. Напишите функцию adjustToPair, принимающую
//функцию типа (Int, Int) => Int и возвращающую эквивалент-
//ную функцию, оперирующую парой. Например, вызов adjustToPair(_
//* _)((6, 7)) должен вернуть 42.
//Затем воспользуйтесь этой функцией в комбинации с map для
//вычисления сумм элементов в парах.
def adjustToPair(fun: (Int, Int) => Int)(p: (Int, Int)) = fun(p._1, p._2)

val pairs = (1 to 10) zip (11 to 20)
pairs.map(adjustToPair(_*_)(_))


//8. Реализуйте абстракцию управления потоком выполнения unless,
//действующую подобно if, но с инвертированным толко-
//ванием логического условия.
def unless(cond: => Boolean)(block: Unit)=
{
  if (!cond) {block}
}

unless(5<4) {println("liar")}

