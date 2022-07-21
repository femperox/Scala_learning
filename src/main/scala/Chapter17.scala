import java.time.LocalTime
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future, Promise, blocking}
import scala.io.StdIn.readLine


object Chapter17 extends App
{

  def await[T](f: Future[T], time: Duration = 10.seconds) = Await.result(f, time)

  //1. Напишите функцию doInOrder, принимающую функции f: T =>
  //Future[U] и g: U => Future[V] и возвращающую функцию T =>
  //Future[U], которая для заданного значения t в конечном счете
  //возвращает g(f(t)).

  def doInOrder[T,U,V](f: T => Future[U], g: U => Future[V]): T => Future[V] =
  {
    t => f(t) flatMap g
  }

  def f(n: Int) = Future { n + 1 }
  def g(n: Int) = Future { n * 2 }
  println(await(doInOrder(f, g)(10)))



  //2. Повторите предыдущее упражнение для произвольной после-
  //довательности функций типа T => Future[T].

  def doInOrder2[T](f: (T => Future[T])*): T => Future[T] =
  {
    f.reduceLeft((x, y) => z => x(z) flatMap y)
  }

  println(await(doInOrder2(f, g)(10)))



  //3. Напишите функцию doTogether, принимающую функции f:
  //T => Future[U] и g: U => Future[V] и возвращающую функцию
  //T => Future[(U, V)], которая выполняет два задания параллель-
  //но и для заданного значения t в конечном счете возвращает
  //(f(t), g(t)).

  def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] =
  {
      t => f(t) zip g(t)
  }

  println(await(doTogether(f, g)(10)))


  //4. Напишите функцию, принимающую последовательность объ-
  //ектов Future и возвращающую объект Future, который в конеч-
  //ном счете возвращает последовательность всех результатов.

  def doTogether2[T](a:Seq[Future[T]]): Future[Seq[T]] =
  {
    Future.sequence(a)
  }

  val s = Seq(f(10), g(10))
  println(await(doTogether2(s)))


  //5. С помощью объектов Future запустите четыре задания, каж-
  //дое из которых приостанавливается на десять секунд и затем
  //выводит текущее время. Если вы пользуетесь достаточно со-
  //временным компьютером, весьма вероятно, что он оснащен
  //четырьмя процессорами, доступными виртуальной машине
  //Java, и все задания должны завершиться практически одно-
  //временно. Теперь попробуйте проделать то же самое для соро-
  //ка заданий. Что получилось? Почему? Попробуйте в качестве
  //контекста выполнения использовать кэширующий пул пото-
  //ков. Что получилось в этом случае? (Будьте внимательны,
  //определяйте объекты Future после замены неявного контекста
  //выполнения.)

  def tickTock =
  {
    val n = 40
    val f = for (i <- 0 to n) yield Future{Thread.sleep(10000); println(LocalTime.now())}

    await(doTogether2(f), n*10.seconds)
  }

  tickTock

  //6. Напишите программу, подсчитывающую количество простых
  //чисел между 1 и n с использованием BigInt.isProbablePrime.
  //Разбейте интервал на p частей, где p – количество доступных процессоров (ядер). Подсчитайте количество простых чисел
  //в каждой части с помощью заданий Future, выполняющихся
  //параллельно, и объедините полученные результаты.

  def countPrime(n: BigInt): Int =
  {
    val p = Runtime.getRuntime.availableProcessors()

    (BigInt(1) to n).grouped((n/p).toInt).map(x => await(Future { x.count(_.isProbablePrime(100))}) ).reduceLeft(_+_)
  }

  countPrime(20)

}
