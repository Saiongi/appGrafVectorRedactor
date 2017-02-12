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

    ArrayList<Shape> shapes;

    public Buffer(){
    shapes = new ArrayList<Shape>();
    }

    public void changeActiveFigure(){

    }

    public void findActiveFigure(Point start){
        int i=0;
        for (Shape shape : shapes) {
            if (shape.isSelected(start)) {

                System.out.println("Попали");
                return;
            }
        }
        if (i==0){
            for (Shape shape : shapes) {
                shape.select=false;
            }
        }
    }

    public void addShape(Shape shape){
        shapes.add(shape);
        System.out.println("add shape"+ shape.toString());

    }
    public void paintShapes(GraphicsContext gc){
             for (Shape shape : shapes) {
                shape.paintShape(gc);
             }

    }
}
