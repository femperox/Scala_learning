object Chapter2
{
  //1. Написать функцию сигнум, возвращающую 1 если x>0, 0 если x=0 и -1 в обратном случае
  def signum(x: Int) = if (x>0) 1 else if (x==0) 0 else -1

  //2. Какое значение возвращает пустой блок? Каков его тип?
  def empty_block
  {
    println({})
    println({}.getClass)
  }

  //3. Ситуация, когда x=y=1 допустимо
  def assign
  {
    var y: Int = 0
    var x: Unit = y =1
  }

  //4. Аналог Java-циклу: for (int i = 10; i >= 0; i-- ) System.out.println(i);
  def cycle = for (i <- 10 to 0 by -1) println(i)

  //5. Напишите процедуру countdown(n: Int), которая выводит числа от n до 0.
  def countdown(n: Int) = for (i <- n to 0 by -1) println(i)

  //6. Напишите цикл for для вычисления произведения кодовых пунктов Юникода всех букв в строке
  def mul_unicode(s: String):Long =
  { var mul: Long = 1
    for (chr <- s) mul *= chr
    mul
  }

  //7. То же, что и выше, но без for
  def mul_unicode_2(s:String):Long = s.foldLeft(1L)(_ * _)

  //8. То же, что и выше, но рекурсивно
  def mul_unicode_3(s: Char*): Long =
  {
    if (s.isEmpty) 1L
    else s.head * mul_unicode_3(s.tail: _*)
  }

  //9. Рекурсивное вычисление степени числа
  def new_pow(x:Long, n: Int): Double =
  {
    if (n==0) 1
    else if (n%2==0 && n>0) new_pow(x*x, n/2)
    else if (n%2==1 && n>0) x * new_pow(x, n-1)
    else {println(1/x.toDouble); (1/x.toDouble) * new_pow(x, n+1)}

  }

  def main(args: Array[String]): Unit =
  {

      println(new_pow(2,-5))
  }

}

