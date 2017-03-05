package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.controll.Buffer;
import shapes2.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;



public class Controller {
    public  Canvas canvas;
    public ComboBox choiseBox;
    Point start, finish;
    Buffer buffer;
    Square square;
    Circle circle;
    Polygon polygon;
    Triangle triangle;
    Star star;
    //переиенная для определения наличия текущей выбранной фигуры
    boolean activeFigure=false;
    //переменнные для определения нажатия на иконку отрисовки и выбора изображаемой фигуры

    static boolean isCircle =false, isRectangle = false, isTriangle = false, isPolygon =false, isStar=false, isSelect =false,
            isChangePosition=false,isRotate=false;
    //переменная для диаметра
    double diam = 0;
    GraphicsContext gc;

    //Конструктор класса
    public Controller(){
        //инициализация экземпляра класса буффер, для работы с коллекцией объектов
        buffer = new Buffer();

    }

    public static void selectInstrument(String instrumentname){
        isCircle =false;
        isRectangle = false, isTriangle = false, isPolygon =false, isStar=false, isSelect =false,
                isChangePosition=false,isRotate=false;
        switch(instrumentname){

        }
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

        gc.setLineWidth(2.5);
        gc.setStroke(Color.BLACK);

        buffer.paintShapes(gc);

        //Создаем прямугольник
        if (isRectangle) {
            square.setRightDownAngle(getFinishPoint(mouseEvent));

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
            triangle.setHelpPoint(getFinishPoint(mouseEvent));

            //вызываем метод создания треугольника
            if (start == triangle.getCenterAngle() && mouseEvent.isPrimaryButtonDown()) triangle.paintShape(gc);
        }
        //изменяем размер многоугольника в зависимости от движения мыши
        if (isPolygon) {
            polygon.setRadius(getFinishPoint(mouseEvent));

            //вызываем метод отрисовки многоугольника
            if (start == polygon.getCenter() && mouseEvent.isPrimaryButtonDown()) polygon.paintShape(gc);
        }

        //изменяем размер звезды в зависимости от движения мыши
        if (isStar) {
            star.setRadius(getFinishPoint(mouseEvent));

            //вызываем метод отрисовки звезды
            if (start == star.getCenter() && mouseEvent.isPrimaryButtonDown()) star.paintShape(gc);
        }

        //если есть выбранная фигура
        if (buffer.indexOfselect != -1 )
        {
            //пворачиваем фигуру в зависимости от движения мыши
            if (isRotate){
                buffer.setRotateAngle2(getFinishPoint(mouseEvent));
                //вызываем метод поворота
                // if ( mouseEvent.isPrimaryButtonDown()) {
                buffer.rotateFigure(gc);
                // }
            }
            if (isChangePosition){
                buffer.setChangePoint2(getFinishPoint(mouseEvent));
                buffer.changePositionFigure(gc);
            }

        }

    }
    //метод, срабатывающий на отжатие мыши
    public void canvasMouseClicked(MouseEvent mouseEvent) {
        //передаем gc
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2.5);
        gc.setStroke(Color.BLACK);
        //отрисовываем фигуры из буфера
        buffer.paintShapes(gc);

        //отрисовка новой текущей фигуры:

        //если задействован прямоугольник
        if (isRectangle)
        {   //записываем вторую точку для отрисовки прямоугольника
            square.setRightDownAngle(new Point((int) mouseEvent.getX(), (int) mouseEvent.getY()));
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
            triangle.setHelpPoint(new Point((int) mouseEvent.getX(), (int) mouseEvent.getY()));
            buffer.addShape(triangle);
        }

        if (isPolygon){
            //передаем методу нахождения радиуса текущее положение(точку для построения вектора
            // который будет радиусом)

            polygon.setRadius(new Point((int) mouseEvent.getX(), (int) mouseEvent.getY()));
            buffer.addShape(polygon);
        }

        if (isStar){
            //передаем методу нахождения радиуса текущее положение(точку для построения вектора
            // который будет радиусом)
            star.setRadius(new Point((int) mouseEvent.getX(), (int) mouseEvent.getY()));
            buffer.addShape(star);
        }

        if (buffer.indexOfselect != -1 )
        {
            //пворачиваем фигуру в зависимости от движения мыши
            if (isRotate){
                //передаем методу нахождения радиуса текущее положение(
                // которое будет второй точкой для угла поворота)
                Point p=new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());
                buffer.setRotateAngle2(p);

            }
            if (isChangePosition){
                buffer.setChangePoint2(getFinishPoint(mouseEvent));
                buffer.changePositionFigure(gc);
            }

        }

        //для проверки записи кординат, выводим их в консоль
        System.out.println((int)mouseEvent.getX() +" "+ (int)mouseEvent.getY());
    }
    //метод, срабатывающий на нажатие мыши
    public void canvasMousePressed(MouseEvent mouseEvent) {
        //узнаем начальную точку фигуры(отжатая кнопка мыши) или текущее положение мыши при нажатии
        start = new Point((int) mouseEvent.getX(), (int) mouseEvent.getY());

        //если нужно выбрать фигуру
        if (isSelect){
            //если выбранной фигуры нет, находим ее
                //ищем наличие выделенной фигуры(находится ли какая то фигура в этой точке)
                buffer.findActiveFigure(start);


            if (buffer.indexOfselect != -1 )//если есть выбранная фигура
            {
                //если задействован поворот фигуры
                if (isRotate){
                    //устанавливаем нажатую точку, как начало угла для оворота
                    buffer.setRotateAngle1(start);
                }
                //если задействовано изменеие положения фигуры
                if (isChangePosition){
                    //устанавливаем нажатую точку как точку,относительно которой будем менять положение фигуры
                    buffer.setChangePoint1(start);
                }
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
                buffer.setRotateAngle1(start);
        }

        //для проверки записи кординат, выводим их в консоль
        System.out.println(mouseEvent.getX() + " "+  mouseEvent.getY());
    }
//при нажатии на кнопку круга
    public void circleMouseClicked(MouseEvent mouseEvent) {
        if (isCircle){
            isCircle = false;

        }
        else {
            isCircle = true;
            isRectangle = false;
            isSelect = false;
            isTriangle = false;
            isPolygon = false;
            isStar = false;
            isRotate = false;
        }

    }
// при нажатии на кнопку прямугольника
    public void rectangleMouseClicked(MouseEvent mouseEvent) {
        if (isRectangle){
            isRectangle =false;

        }else {
            isRectangle =true;
            isCircle =false;
            isSelect =false;
            isTriangle =false;
            isPolygon =false;
            isRotate = false;
        }
    }
//при нажатии на кнопку выбрать фигуру
    public void selectMouseClicked(MouseEvent mouseEvent) {

            isSelect = true;
            isRectangle = false;
            isCircle = false;
            isTriangle = false;
            isPolygon = false;
            isStar = false;

    }

    //при нажатии на кнопку треугольника
    public void triangleMouseClicked(MouseEvent mouseEvent) {
        //если трекгольник уже был выбран(активная кнопка)
        if (isTriangle){
            //снимаем активность с кнопки треугольника, убираем видимость с бокса с количеством вершин
            isTriangle =false;
            //если треугольник ранее выбран не был(неактивная кнопка)
        }else {
            //делаем треугольник текущей выбранной фигурой и снимаем активность с остальных фигур и  с выбора
            isTriangle =true;
            isCircle =false;
            isSelect =false;
            isRectangle =false;
            isPolygon =false;
            isStar = false;
            isRotate = false;
        }
    }


//при нажатии на кнопку полигона(многоугольника)
    public void polygonMouseclicked(MouseEvent mouseEvent) {
        //если полигон уже был выбран(активная кнопка)
        if (isPolygon){
            //снимаем активность с кнопки многоугольника, убираем видимость с бокса с количеством вершин
            isPolygon =false;
            choiseBox.setVisible(false);

            //choiseBox.valueProperty().setValue(3);
        //если многоугольник ранее выбран не был(неактивная кнопка)
        }else {
            //делаем многоугольник текущей выбранной фигурой и снимаем активность с остальных фигур и  с выбора
            choiseBox.setItems(FXCollections.observableArrayList("3","5","7","9","11"));
            choiseBox.setVisible(true);
            isPolygon =true;
            isStar = false;
            isCircle = false;
            isSelect = false;
            isRectangle = false;
            isTriangle = false;
            isRotate = false;

        }
    }
//при нажатии на кнопку звезда
    public void starMouseClicked(MouseEvent mouseEvent) {
       //если звезда уже была выбрана
        if (isStar){
            //снимаем активность с кнопки звезда, убираем видимость с бокса с количеством вершин
            isStar =false;
            choiseBox.setVisible(false);

        //если звезда ранее выбрана не была(кнопка не активна)
        }else {
            //делаем звезду текущей выбранной фигурой и снимаем активность с остальных фигур и  с выбора
            isRotate = false;
            choiseBox.setVisible(true);
            choiseBox.setItems(FXCollections.observableArrayList("3","5","7","9","11"));
            isStar = true;
            isPolygon =false;
            isCircle =false;
            isSelect =false;
            isRectangle =false;
            isTriangle =false;

        }
    }
    //при нажатии на поворот фигуры
    public void rotateMouseClicked(MouseEvent mouseEvent) {

        if (isRotate){
            //снимаем активность с кнопки треугольника, убираем видимость с бокса с количеством вершин
            isRotate = false;
            //если треугольник ранее выбран не был(неактивная кнопка)
        }else {
            //делаем треугольник текущей выбранной фигурой и снимаем активность с остальных фигур и  с выбора
            if (isSelect){
                isRotate=true;
                isTriangle = false;
                isCircle = false;
                isRectangle = false;
                isPolygon = false;
                isStar = false;
            }else {

                isRotate = false;
                isTriangle = false;
                isCircle = false;
                //isSelect =false;
                isRectangle = false;
                isPolygon = false;
                isStar = false;
            }
        }
    }

    public void changeposMouseClicked(MouseEvent mouseEvent) {
        if (isSelect){
            if (isChangePosition){
                isRotate=false;
                isTriangle = false;
                isCircle = false;
                isRectangle = false;
                isPolygon = false;
                isStar = false;
                isChangePosition=false;
            }else{
                isRotate=false;
                isTriangle = false;
                isCircle = false;
                isRectangle = false;
                isPolygon = false;
                isStar = false;
                isChangePosition=true;
            }
        }else {

            isRotate = false;
            isTriangle = false;
            isCircle = false;
            //isSelect =false;
            isRectangle = false;
            isPolygon = false;
            isStar = false;
            isChangePosition=false;
        }
    }

  /*  choiceBox = new ChoiceBox();
        choiceBox.setItems(FXCollections.observableArrayList("3","5","7","9","11"));

*/
}
