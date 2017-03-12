package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.controll.Buffer;
import shapes2.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;



public class Controller {
    public GraphicsContext gc;
    public  Canvas canvas;
    public ComboBox choiseBox;
    Point start, finish;
    Buffer buffer;
    private Square square;
    private Circle circle;
    private Polygon polygon;
    private Triangle triangle;
    private Star star;
    public String key;
    //переиенная для определения наличия текущей выбранной фигуры
    boolean activeFigure=false;
    //переменнные для определения нажатия на иконку отрисовки и выбора изображаемой фигуры

    static boolean isCircle =false, isRectangle = false, isTriangle = false, isPolygon =false, isStar=false, isSelect =false,
            isChangePosition=false,isRotate=false;

    boolean isShift=false, isResize=false;
    //переменная для диаметра
    double diam = 0;


    //Конструктор класса
    public Controller(){
        //инициализация экземпляра класса буффер, для работы с коллекцией объектов
        buffer = new Buffer();
    }


//
public static void selectInstrument(String instrumentname){

    isCircle =false;
        isRectangle = false;
        isTriangle = false;
        isPolygon =false;
        isStar=false;
        isSelect =false;
        isChangePosition=false;
        isRotate=false;
        switch(instrumentname){
            case "changePosition":
                isChangePosition = true;
                break;
            case "rotate":
                isRotate = true;
                break;
            case "select":
                isSelect = true;
                break;
            case "star":
                isStar = true;
                break;
            case "triangle":
                isTriangle = true;
                break;
            case "polygon":
                isPolygon = true;
                break;
            case "rectangle":
                isRectangle = true;
                break;
            case "circle":
                isCircle = true;
                break;
        }

    }


    //перемещение мыши
    public void canvasMouseDragged(MouseEvent mouseEvent) {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 571, 373);
        gc.setLineWidth(1.5);
        gc.setStroke(Color.BLACK);
        buffer.paintShapes(gc);
        Point p=new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());
        //Создаем прямугольник
        if (isRectangle) {
            square.setRightDownAngle(p);
            //вызываем метод создания прямоугольника
            if (start == square.getLeftUpAngle() && mouseEvent.isPrimaryButtonDown()) square.paintShape(gc);
        }
        //создаем круг
        if (isCircle) {
             diam = mouseEvent.getY()-start.getY();
            circle.setDiametr(diam);
            //вызываем метод создания круга
            if (start == circle.getLeftUpAngle() && mouseEvent.isPrimaryButtonDown()) circle.paintShape(gc);
        }
        //создаем треугольник
        if (isTriangle) {
            triangle.setHelpPoint(p);
            //вызываем метод создания треугольника
            if (start == triangle.getCenterAngle() && mouseEvent.isPrimaryButtonDown()) triangle.paintShape(gc);
        }
        //изменяем размер многоугольника в зависимости от движения мыши
        if (isPolygon) {
            polygon.setRadius(p);
            //вызываем метод отрисовки многоугольника
            if (start == polygon.getCenter() && mouseEvent.isPrimaryButtonDown()) polygon.paintShape(gc);
        }
        //изменяем размер звезды в зависимости от движения мыши
        if (isStar) {
            star.setRadius(p);
            //вызываем метод отрисовки звезды
            if (start == star.getCenter() && mouseEvent.isPrimaryButtonDown()) star.paintShape(gc);
        }
        //если есть выбранная фигура
        System.out.println(key);

        if (isSelect){
            if (buffer.indexOfselect != -1 )
            {//пворачиваем фигуру в зависимости от движения мыши
                buffer.setChangePoint2(p);
                if (key=="shift"){
                    //передаем методу нахождения радиуса текущее положение,которое будет второй точкой для угла поворота)
                    buffer.rotateFigure(gc);
                }else {
                    if (key=="ctrl"){
                        //при перемещении мыши
                    }else {
                    //меняем текущее положение фигуры
                        buffer.changePositionFigure(gc);
                    }

                }
            }
        }
    }
    //метод, срабатывающий на отжатие мыши
    public void canvasMouseClicked(MouseEvent mouseEvent) {
        //передаем gc
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2.0);
        gc.setStroke(Color.BLACK);
        //отрисовываем фигуры из буфера
        buffer.paintShapes(gc);
        //точка, содержащая текущее положение мыши
        Point p=new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());
        //отрисовка новой текущей фигуры:
        //если задействован прямоугольник





        if (isRectangle)
        {   //записываем вторую точку для отрисовки прямоугольника
            square.setRightDownAngle(p);
            buffer.addShape(square);
        }
        if (isCircle)
        {   //записываем вторую точку для отрисовки круга
             diam = mouseEvent.getY()-start.getY() ;
            circle.setDiametr( diam )   ;
            buffer.addShape(circle);
        }
        if (isTriangle)
        {   //записываем вторую точку для отрисовки прямоугольника
            triangle.setHelpPoint(p);
            buffer.addShape(triangle);
        }
        if (isPolygon){
            //передаем методу нахождения радиуса текущее положение(точку для построения вектора
            // который будет радиусом)
            polygon.setRadius(p);
            buffer.addShape(polygon);
        }
        if (isStar){
            //передаем методу нахождения радиуса текущее положение(точку для построения вектора
            // который будет радиусом)
            star.setRadius(p);
            buffer.addShape(star);
        }
        //выбор фигуры-поворот или смещение
        if (isSelect){
            if (buffer.indexOfselect != -1 )
            {//поворачиваем фигуру в зависимости от движения мыши
                buffer.setChangePoint2(p);//getFinishPoint(mouseEvent)
                if (key=="shift"){
                    //передаем методу нахождения радиуса текущее положение,которое будет второй точкой для угла поворота)
                    buffer.rotateFigure(gc);
                }else {
                    if (key == "ctrl") {
                        //при перемещении мыши
                    } else {
                        //меняем текущее положение фигуры
                        buffer.changePositionFigure(gc);
                    }
                    //меняем текущее положение фигуры
                }
                buffer.changeActiveFigure();
            }
        }
        //для проверки записи кординат, выводим их в консоль
        System.out.println((int)mouseEvent.getX() +" "+ (int)mouseEvent.getY());
    }
    //метод, срабатывающий на нажатие мыши
    public void canvasMousePressed(MouseEvent mouseEvent) {
        //узнаем начальную точку фигуры(отжатая кнопка мыши) или текущее положение мыши при нажатии
        start = new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());

        //поиск угла rotate
        findAngle(start.getX(), start.getY());

        gc.strokeLine(  100,200,start.getX(), start.getY());


        //если нужно выбрать фигуру
        if (isSelect){
            //если выбранной фигуры нет, находим ее
            //ищем наличие выделенной фигуры(находится ли какая то фигура в этой точке)
            buffer.findActiveFigure(start);
            if (buffer.indexOfselect != -1 )//если есть выбранная фигура
            {//если задействован поворот фигуры, т.е. зажата клавиша Shift

                    buffer.setChangePoint1(start);


            }
        }
        //в зависимости от задействованной фигуры, создаем нужную

        //создаем круг
        if (isCircle)
        {
            circle = new Circle();
            //записываем первую координату
            circle.setLeftUpAngle(start);
        }
        //создаем прямоугольник
        if (isRectangle)
        {
            square = new Square();
            //записываем первую координату
            square.setLeftUpAngle(start);

        }
        //создаем треугольник
        if (isTriangle)
        {
            triangle = new Triangle();
            //записываем первую координату
            triangle.setCenterAngle(start);
        }
        //создаем многоугольник
        if (isPolygon){
            polygon = new Polygon();
                //!choiseBox.isShowing() &&
                if ( !choiseBox.getValue().equals(null)){
                    polygon.setCountOfSide(Integer.parseInt(choiseBox.getValue().toString()));
                }
                if (choiseBox.getValue().equals(null)) {
                    choiseBox.setValue(choiseBox.getItems().get(0));
                    polygon.setCountOfSide(Integer.parseInt(choiseBox.getValue().toString()));
                }
            //polygon.setCountOfSide(11);
            //записываем центр многоугольника
            polygon.setCenter(start);
        }
        //создаем звезду
        if (isStar){
            star = new Star();

            if ( !choiseBox.getValue().equals(null)){
                star.setCountOfSide(Integer.parseInt(choiseBox.getValue().toString()));
            }

            if (choiseBox.getValue().equals(null)) {
                star.setCountOfSide(Integer.parseInt(choiseBox.getValue().toString()));
            }
            //polygon.setCountOfSide(11);
            //записываем центр многоугольника
            star.setCenter(start);

        }

        //если нужно выбрать фигуру
        if (isRotate){
                buffer.setChangePoint1(start);
        }

        //для проверки записи кординат, выводим их в консоль
        System.out.println(mouseEvent.getX() + " "+  mouseEvent.getY());
    }


    //при нажатии на кнопку круга
    public void circleMouseClicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("circle");
        choiseBox.setVisible(false);
    }
// при нажатии на кнопку прямугольника
    public void rectangleMouseClicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("rectangle");
        choiseBox.setVisible(false);
    }
// /при нажатии на кнопку выбрать фигуру
    public void selectMouseClicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("select");
        choiseBox.setVisible(false);
        canvas.requestFocus();

    }
    //при нажатии на кнопку треугольника
    public void triangleMouseClicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("triangle");
        choiseBox.setVisible(false);
        canvas.requestFocus();

    }
    //при нажатии на кнопку полигона(многоугольника)
    public void polygonMouseclicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("polygon");
        choiseBox.setVisible(true);
        choiseBox.setItems(FXCollections.observableArrayList("3","5","7","9","11"));
        canvas.requestFocus();

    }
//при нажатии на кнопку звезда
    public void starMouseClicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("star");
        choiseBox.setVisible(true);
        choiseBox.setItems(FXCollections.observableArrayList("3","5","7","9","11"));
        canvas.requestFocus();

    }
    //при нажатии на поворот фигуры
    public void rotateMouseClicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("rotate");
        choiseBox.setVisible(false);
        canvas.requestFocus();

    }
    public void changeposMouseClicked(MouseEvent mouseEvent) {
        Controller.selectInstrument("changePosition");
        choiseBox.setVisible(false);
        canvas.requestFocus();

    }
    public void canvasKeyPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.SHIFT) key="shift";
        if (keyEvent.getCode() == KeyCode.CONTROL) key="ctrl";
        System.out.println(key);
    }
    public void canvasKeyReleased(KeyEvent keyEvent) {
        key="";
    }
    public void focus() {
        canvas.requestFocus();
    }

    public void findAngle(int x2,int y2){
        int y1=100,x1=200;
        int scalar = x1 * x2 + y1 * y2;
        double power1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        double power2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));
      //  System.out.println((scalar / (power1 * power2))*180/Math.PI );
     //   System.out.println(((Math.PI-Math.atan2(y1-y2,-(x1-x2)))*180/Math.PI));
    }


}

//        <Button fx:id="triangleButton" layoutX="12.0" layoutY="68.0" mnemonicParsing="false" onMouseClicked="#triangleMouseClicked" text="Treangle" />
