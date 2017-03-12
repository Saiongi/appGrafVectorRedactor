package shapes2;

import javafx.scene.canvas.GraphicsContext;

/**
 * Абстрактный класс Shape содержит основные общие методы, используемые фигурами
 */
public abstract class Shape {
    public boolean select=false;

     Point startChange;
    private Point finishChange;
    protected Point startPoint;
    private Point finishPoint;


    public void setCountOfSide(int n){

    }
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    public Point getStartPoint() {
        return startPoint;
    }
    public void setFinishPoint(Point finishPoint) {
        this.finishPoint = finishPoint;
    }
    public Point getFinishPoint() {
        return finishPoint;
    }

    public void setStartChange(Point startChange) {
        this.startChange = startChange;
    }
    public Point getStartChange() {
        return startChange;
    }
    public void setFinishChange(Point finishChange) {
        this.finishChange = finishChange;
    }
    public Point getFinishChange() {
        return finishChange;
    }

    public boolean isSelected(Point findingPoint){
        return select;//проверяет выбрана ли данная фигура
    }

    public void changePosition(){
        return ;
        //изменяет позицию
    }
    public void changeSize(){
        //изменяет размер
    }
    public void deleteShape(){
        //удаляет фигуру
    }
    public void rotate(GraphicsContext gc){
        //поворачивает фигуру
    }
    public void paintShape(GraphicsContext gc){
        //рисует фигуру
    }
}
