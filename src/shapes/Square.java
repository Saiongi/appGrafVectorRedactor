package shapes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import  shapes.Point;
/**
 * Created by Светлана on 04.02.2017.
 */
public class Square extends Shape{

    Point leftUpAngle;
    Point rightUpAngle;
    Point leftDownAngle;
    Point rightDownAngle;

    int lengthSide;

    public void setLeftUpAngle(Point leftUpAngle) {
        this.leftUpAngle = leftUpAngle;
    }
    public Point getLeftUpAngle() {
        return leftUpAngle;
    }
    public void setLengthSide(int lengthSide) {
        this.lengthSide = lengthSide;
    }
    public int getLengthSide() {
        return lengthSide;
    }

    public void setSquare(GraphicsContext gc){
        //получаем длину стороны
        int side=this.getLengthSide();
        // создаем экземпляр первой точки для работы в цикле
        Point p1=new Point();
        p1.setX(this.getLeftUpAngle().getX());
        p1.setY(this.getLeftUpAngle().getY());
        p1.setColor(Color.BLACK);
        Point p2=new Point();// создаем экземпляр второй точки для работы в цикле
        p2.setX(this.getLeftUpAngle().getX());
        p2.setY(this.getLeftUpAngle().getY());
        p2.setColor(Color.BLACK);
        this.getLeftUpAngle().setPoint(gc); //ставим точку левого верхнего угла
        for (int i=0; i< lengthSide;i++)    //цикл, в котором прорисовывается точками левая и верхняя сторона
        {   //Движемся вниз по оси Ox
            p1.setY(p1.getY()-1);// движемся на 1 точку вниз
            p1.setPoint(gc);//ставим ее
            //Движемся вниз по оси Oy
            p2.setX(p2.getX()+1);// движемся на 1 точку вправо
            p2.setPoint(gc);//ставим ее

        }

        for (int i=0; i< lengthSide;i++)    //цикл, в котором прорисовывается точками нижняя и правая сторона
        {   //Движемся вниз по оси Ox
            p1.setX(p1.getX()+1);// движемся на 1 точку вправо
            p1.setPoint(gc);//ставим ее
            //Движемся вниз по оси Oy
            p2.setY(p2.getY()-1);// движемся на 1 точку вниз
            p2.setPoint(gc);//ставим ее
        }
     //   p2..setPoint(gc); //ставим точку левого верхнего угла

    }


}
