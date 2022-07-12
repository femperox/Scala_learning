object Chapter10 extends App
{

  //1. Класс java.awt.Rectangle имеет очень удобные методы translate
  //и grow, которые, к сожалению, отсутствуют в таких классах,
  //как java.awt.geom.Ellipse2D. В Scala эту проблему легко испра-
  //вить. Определите трейт RectangleLike с конкретными методами
  //translate и grow
  trait RectangleLike extends java.awt.geom.Ellipse2D
  {
      def translate(dx: Double, dy: Double)
      {
        this.setFrame(this.getX+dx, this.getY+dy, this.getWidth, this.getHeight)
      }

      def grow(h: Double, w: Double)
      {
        this.setFrame(this.getX, this.getY, this.getWidth+w, this.getHeight+h)
      }
  }

  val egg = new java.awt.geom.Ellipse2D.Double(5,10,20,30)
            with RectangleLike
  egg.translate(10, -10)
  egg.grow(10, 20)




}
