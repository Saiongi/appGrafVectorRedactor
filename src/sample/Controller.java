package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.controll.Buffer;
import shapes2.Circle;
import shapes2.Shape;
import shapes2.Square;
import shapes2.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;



public class Controller {
    public  Canvas canvas;
    Point start, finish;
    Buffer buffer;
    Square square;
    Circle krug;
    //переменнные для определения нажатия на иконку отрисовки и выбора изображаемой фигуры
    boolean circle=false, rectangle = false;
    //переменная для диаметра
    double diam = 0;

    public Controller(){
        buffer = new Buffer();
    }
    public Point getFinishPoint(MouseEvent mouseEvent){
        double x=0, y=0;
        Point p;
        if(mouseEvent.isPrimaryButtonDown() && x!=mouseEvent.getX() && y != mouseEvent.getY()   ) {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
        }
        p=new Point((int)x,(int)y);
        return p;
    }
//перемещение мыши
    public void canvasMouseDragged(MouseEvent mouseEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 571, 373);
        buffer.paintShapes(gc);

        //Создаем прямугольник
        if (rectangle) {
            square.setRightDownAngle(getFinishPoint(mouseEvent));

            //вызываем метод создания прямоугольника
            if (start == square.getLeftUpAngle() && mouseEvent.isPrimaryButtonDown()) square.paintShape(gc);
        }

       //создаем круг
        if (circle) {
             diam = mouseEvent.getY()-start.getY();

            krug.setDiametr(diam);
            //вызываем метод создания круга
            if (start == krug.getLeftUpAngle() && mouseEvent.isPrimaryButtonDown()) krug.paintShape(gc);
        }
    }
    //метод, срабатывающий на отжатие мыши
    public void canvasMouseClicked(MouseEvent mouseEvent) {
        //передаем gc
        GraphicsContext gc = canvas.getGraphicsContext2D();
       //отрисовываем фигуры из буфера
        buffer.paintShapes(gc);

        if (rectangle)
        {   //записываем вторую точку для отрисовки прямоугольника
            square.setRightDownAngle(new Point((int) mouseEvent.getX(), (int) mouseEvent.getY()));
            buffer.addShape(square);
        }

        //если задействован прямоугольник

        if (circle)
        {   //записываем вторую точку для отрисовки прямоугольника
             diam = mouseEvent.getY()-start.getY() ;
            krug.setDiametr( diam )   ;
            buffer.addShape(krug);


        }

        //для проверки записи кординат, выводим их в консоль
        System.out.println((int)mouseEvent.getX() +" "+ (int)mouseEvent.getY());
    }
    //метод, срабатывающий на нажатие мыши
    public void canvasMousePressed(MouseEvent mouseEvent) {
        //узнаем начальную точку фигуры(отжатая кнопка мыши)
        start = new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());
        //в зависимости от задействованной фигуры, создаем нужную
       //создаем круг
        if (circle)
        {
            krug = new Circle();
            //записываем первую координату
            krug.setLeftUpAngle(start);
        }
        //создаем прямоугольник
        if (rectangle)
        {
            square = new Square();
            //записываем первую координату
            square.setLeftUpAngle(start);

        }
        //для проверки записи кординат, выводим их в консоль
        System.out.println(mouseEvent.getX() + " "+  mouseEvent.getY());

    }
//кнопка круга
    public void circleMouseClicked(MouseEvent mouseEvent) {
        if (circle){
            circle=false;
        }
        else {
            circle=true;
            rectangle=false;
        }

    }
//кнопка квадрата
    public void rectangleMouseClicked(MouseEvent mouseEvent) {
        if (rectangle){
            rectangle=false;

        }else {
            rectangle=true;
            circle=false;
        }
    }

   /*
   *
   * */





}
