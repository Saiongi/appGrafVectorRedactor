package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import sample.controll.Buffer;
import shapes2.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;



public class Controller {
    public  Canvas canvas;
    Point start, finish;
    Buffer buffer;
    Square square;
    Circle krug;
    @FXML
    Triangle treugolnik;
    //переиенная для определения наличия текущей выбранной фигуры
    boolean activeFigure=false;
    //переменнные для определения нажатия на иконку отрисовки и выбора изображаемой фигуры

    boolean circlebool =false, rectangle = false, triangle = false, selecting=false;
    //переменная для диаметра
    double diam = 0;
    GraphicsContext gc;
    //Конструктор класса
    public Controller(){
        //инициализация экземпляра класса буффер, для работы с коллекцией объектов
        buffer = new Buffer();

    }
    //определеяет текущее положение курсора. Если оно изменено,значение меняется. Используется для эффекта отрисовки.
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
        if (circlebool) {
             diam = mouseEvent.getY()-start.getY();

            krug.setDiametr(diam);
            //вызываем метод создания круга
            if (start == krug.getLeftUpAngle() && mouseEvent.isPrimaryButtonDown()) krug.paintShape(gc);
        }
        //создаем треугольник
        if (triangle) {
            treugolnik.setHelpPoint(getFinishPoint(mouseEvent));

            //вызываем метод создания прямоугольника
            if (start == treugolnik.getCenterAngle() && mouseEvent.isPrimaryButtonDown()) treugolnik.paintShape(gc);
        }

    }
    //метод, срабатывающий на отжатие мыши
    public void canvasMouseClicked(MouseEvent mouseEvent) {
        //передаем gc
        gc = canvas.getGraphicsContext2D();

        //отрисовываем фигуры из буфера
        buffer.paintShapes(gc);

        //отрисовка новой текущей фигуры:

        //если задействован прямоугольник
        if (rectangle)
        {   //записываем вторую точку для отрисовки прямоугольника
            square.setRightDownAngle(new Point((int) mouseEvent.getX(), (int) mouseEvent.getY()));
            buffer.addShape(square);
        }


        //
        if (circlebool)
        {   //записываем вторую точку для отрисовки круга
             diam = mouseEvent.getY()-start.getY() ;

            krug.setDiametr( diam )   ;
            buffer.addShape(krug);
        }

        if (triangle)
        {   //записываем вторую точку для отрисовки прямоугольника
            treugolnik.setHelpPoint(new Point((int) mouseEvent.getX(), (int) mouseEvent.getY()));
            buffer.addShape(treugolnik);
        }

        //для проверки записи кординат, выводим их в консоль
        System.out.println((int)mouseEvent.getX() +" "+ (int)mouseEvent.getY());
    }
    //метод, срабатывающий на нажатие мыши
    public void canvasMousePressed(MouseEvent mouseEvent) {
        //узнаем начальную точку фигуры(отжатая кнопка мыши) или текущее положение мыши при нажатии
        start = new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());
        //в зависимости от задействованной фигуры, создаем нужную

        //если нужно выбрать фигуру
        if (selecting){
            buffer.findActiveFigure(start);

        }


        //создаем круг
        if (circlebool)
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
        //создаем треугольник
        if (triangle)
        {
            treugolnik = new Triangle();
            //записываем первую координату
            treugolnik.setCenterAngle(start);
        }

        //для проверки записи кординат, выводим их в консоль
        System.out.println(mouseEvent.getX() + " "+  mouseEvent.getY());
    }
//кнопка круга
    public void circleMouseClicked(MouseEvent mouseEvent) {
        if (circlebool){
            circlebool =false;

        }
        else {
            circlebool =true;
            rectangle=false;
            selecting=false;
            triangle=false;

        }

    }
//кнопка квадрата
    public void rectangleMouseClicked(MouseEvent mouseEvent) {
        if (rectangle){
            rectangle=false;

        }else {
            rectangle=true;
            circlebool =false;
            selecting=false;
            triangle=false;
        }
    }
//кнопка выбрать фигуру
    public void selectMouseClicked(MouseEvent mouseEvent) {
        if (selecting){
            selecting=false;

        }else {
            selecting=true;
            rectangle=false;
            circlebool =false;
            triangle=false;

        }
    }

    //кнопка треугольника
    public void triangleMouseClicked(MouseEvent mouseEvent) {
        if (triangle){
            triangle=false;

        }else {
            triangle=true;
            circlebool =false;
            selecting=false;
            rectangle=false;
        }
    }

}
