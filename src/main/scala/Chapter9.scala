import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.sys.process._

object Chapter9 extends App
{

  //1. Напишите на языке Scala код, который размещает строки
  //в файле в обратном порядке (последнюю делает первой и т. д.).
  def reverse_file(sa: Array[String])
  {
    val path = "files\\Chapter9_first.txt"

    val out = new PrintWriter(path)
    for (s <- sa.reverse) out.println(s)

    out.close()
  }

  //2. Напишите фрагмент кода на Scala, который читает файл и вы-
  //водит в консоль все слова, содержащие 12 или более символов.
  //Дополнительные баллы начисляются тем, кто сможет сделать
  //это в одной строке кода.
  def twelve_symbols(path: String) =
  {
    println(Source.fromFile(path, "UTF-8").getLines.toArray.filter(_.length >= 12).mkString("\n"))
  }

  twelve_symbols("files\\Chapter4_file.txt")

  //3. Напишите программу на Scala, которая читает текстовый
  //файл, содержащий только вещественные числа, выводит сум-
  //му, среднее, максимальное и минимальное значения.
  def math_file: (Double, Double, Double) =
  {
    val path = "files\\Chapter9_third.txt"
    val nums = Source.fromFile(path, "UTF-8").getLines()
    val real = (for (num <- nums) yield num.toDouble).toArray

    (real.sum, real.max, real.min)
  }

  //4. Напишите программу на Scala, которая читает текстовый файл
  //и выводит все лексемы, являющиеся вещественными чис-
  //лами. Используйте регулярное выражение
  def not_real
  {
    var path = "files\\Chapter4_file.txt"
    var f = Source.fromFile(path, "UTF-8").mkString

    val r = "(([0-9])\\.([0-9]))".r

    val l = r.findAllIn(f).toArray.mkString(" ")

    println(l)
  }

  //5. Напишите программу на Scala, которая выводит атрибуты src
  //всех тегов img в веб-странице. Используйте регулярные выра-
  //жения и группы.
  def get_src(url:String = "http://www.sai.msu.su/apod/ap050605.html")
  {
    val site = Source.fromURL(url).mkString

    val r = """<IMG SRC=["\S"]+""".r
    val find = r.findAllIn(site).toArray

    println(find.mkString(" ").replaceAll("<IMG SRC=",""))
  }

  //6. Напишите программу на Scala, которая подсчитывает количество
  //файлов с расширением .txt в указанном каталоге и во
  //всех его подкаталогах.
  def count_txt(path: String = "files\\")
  {
    val files = Files.walk(Paths.get(path)).toArray
    println(files.count(_.toString.contains(".txt")))

  }




}
