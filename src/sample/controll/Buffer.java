package sample.controll;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes2.Circle;
import shapes2.Point;
import shapes2.Shape;
import shapes2.Square;

import java.util.ArrayList;

/**
 * Класс, выполняющий функцию буффера между отображающимися фигурами и коллеккцией существующих фигур
 */
 public  class Buffer {

     public ArrayList<Shape> shapes;
    public int indexOfselect=-1;

    public Buffer(){
    shapes = new ArrayList<Shape>();
    }

    public void changeActiveFigure(){

    }

    public void findActiveFigure(Point start){
        System.out.println("FindActiveFigure");
        for (Shape shape : shapes) {
            shape.select=false;
            indexOfselect=-1;
        }
        int g=0;
        for (int i=0;i<shapes.size();i++) {//Shape shape : shapes
            if (shapes.get(i).isSelected(start)) {
                indexOfselect=i;
                g=1;
                System.out.println("Попали");
                return;
            }
        }
        if (g==0){
            for (Shape shape : shapes) {
                shape.select=false;
                indexOfselect=-1;
            }
        indexOfselect=-1;
        }
    }

    public void addShape(Shape shape){
        shapes.add(shape);
        System.out.println("add shape"+ shape.toString());
    }

    public void rotateFigure(GraphicsContext gc){
        shapes.get(indexOfselect).rotate(gc);


    }
    public void setRotateAngle1(Point p){
        for (Shape shape : shapes) {
            if (shape.select) shape.setPointForRotate1(p);
        }
    }
    public void setRotateAngle2(Point p){
        for (Shape shape : shapes) {
            if (shape.select) shape.setPointForRotate2(p);
        }
    }
    public void setChangePoint1(Point p){
        shapes.get(indexOfselect).setStartChange(p);
    }
    public void setChangePoint2(Point p){
        shapes.get(indexOfselect).setFinishChange(p);
    }
    public void changePositionFigure(GraphicsContext gc){
       shapes.get(indexOfselect).changePosition() ;
        this.paintShapes(gc);
    }

    public void paintShapes(GraphicsContext gc){
             for (Shape shape : shapes) {
                shape.paintShape(gc);
             }

    }
}
