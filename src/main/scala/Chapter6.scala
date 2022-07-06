object Chapter6 extends App{

  //1. Напишите объект Conversions с методами inchesToCentimeters,
  //gallonsToLiters и milesToKilometers
  object Conversions
  {
    def inchesToCentimeters(inch: Double) =  inch*2.54

    def gallonsToLiters(gallon: Double) = gallon*3.785411784

    def milesToKilometers(mile:Double) = mile*1.60934
  }

  println(Conversions.milesToKilometers(2))

  //2. Определите класс Point с объектом-компаньоном, чтобы мож-
  //но было конструировать экземпляры Point, как Point(3, 4), без
  //ключевого слова new.
  class Point(var x: Int, var y:Int) {}

  object Point
  {
    def apply(x:Int, y: Int) = new Point(x, y)
  }

  val p = Point(1,2)
  println(p.x)

  //3. Вывести аргументы командной строки в обратном порядке одной строкой
  if (args.length>0)
    println(args.reverse.mkString(" "))
  else ()

}
