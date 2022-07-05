object Chapter5 {

  //1. Напишите класс BankAccount с методами deposit и withdraw
  //и свойством balance, доступным только для чтения.
  class BankAccount
  {
    private var p_balance: Double = 0

    def balance = p_balance

    def deposit(value: Double) {if (value >0) p_balance += value}

    def withdraw(value: Double) = {if (value >0) p_balance -= value}
  }

  //2. Напишите класс Time со свойствами hours и minutes, доступны-
  //ми только для чтения, и методом before(other: Time): Boolean,
  //который проверяет, предшествует ли время this времени other.
  //Объект Time должен конструироваться как new Time(hrs, min),
  //где hrs – время в 24-часовом формате.
  class Time(private var p_hours:Int,  private var p_minutes:Int)
  {
    if (p_hours > 23) p_hours = 0
    if (p_minutes > 59) p_hours = 0

    def hours = p_hours

    def minutes = p_minutes

    def before(other:Time):Boolean =
    { if (p_hours < other.p_hours) true
      else if (p_hours==other.p_hours && p_minutes < other.p_minutes) true
      else false
    }
  }

  //3. Напишите класс Person с главным конструктором, принимающим
  //строку, которая содержит имя, пробел и фамилию, на-
  //пример: new Person("Fred Smith"). Сделайте свойства firstName
  //и lastName доступными только для чтения.
  // Отрицательный возраст прировняйте к 0
  class Person(fullName:String, var age: Int)
  {
    if (age < 0) age = 0
    val firstName = fullName.split(" ")(0)
    val lastName = fullName.split(" ")(1)

  }

  //4. Создайте класс Car со свойствами, определяющими производи-
  //теля, название модели и год производства, которые доступны
  //только для чтения, и свойство с регистрационным номером
  //автомобиля, доступным для чтения/записи. Добавьте четыре
  //конструктора. Все они должны принимать название произво-
  //дителя и название модели. При необходимости в вызове кон-
  //структора могут также указываться год и регистрационный
  //номер. Если год не указан, он должен устанавливаться равным
  //-1, а при отсутствии регистрационного номера должна уста-
  //навливаться пустая строка. Какой конструктор вы выберете
  //в качестве главного? Почему?
  class Car
  {  private var p_manufacturer = ""
     private var p_model = ""
     private var p_year = -1

     def manufacturer = p_manufacturer
     def model = p_model
     def year = p_year

     var registrationNum = ""

     def this(manufacturer: String, model: String)
     {
       this()
       p_manufacturer = manufacturer
       p_model = model
     }

     def this(manufacturer: String, model: String, year:Int)
     {  this(manufacturer, model)
       p_year = year
     }

    def this(manufacturer: String, model: String, registrationNum:String)
    {  this(manufacturer, model)
       this.registrationNum = registrationNum
    }

    def this(manufacturer: String, model: String, year:Int, registrationNum:String)
    {  this(manufacturer, model, year)
       this.registrationNum = registrationNum
    }
  }


  def main(Args: Array[String]):Unit =
  {
        val b = new BankAccount
        b.deposit(100)
        b.withdraw(50)


        val t1 = new Time(1,3)
        val t2 = new Time(1, 3)
        println(t1.before(t2))

        val p = new Person("Fred Smith", 50)
        println(s"First Name: ${p.firstName}\nLast Name: ${p.lastName}\nAge: ${p.age}")

        val c = new Car(model = "ok", manufacturer = "well")
        println(c.model)
  }

}
