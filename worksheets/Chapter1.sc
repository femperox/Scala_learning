import scala.math.BigInt.probablePrime
import scala.math._
import scala.util.Random

//1. квадратный корень из 3, затем возвести в квадрат
pow(sqrt(3), 2)

//2. перемножение строк и чисел
"crazy" * 3

//3. max
10 max 2

//4. импорт необходимых библиотек для опервции ниже
probablePrime(100, Random)

//5. использование BigInt
BigInt(2).pow(1024)

//6. перевод в другую систему счисления
val r = probablePrime(100, Random)
r.toString(36)

//7. индекация строк
val s = "1 2 3 4 5"
s(0).toString == s.take(1)
s(s.length-1).toString == s.takeRight(1)