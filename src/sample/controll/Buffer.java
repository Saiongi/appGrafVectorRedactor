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
    public ArrayList <Integer> memory;
        int i=0;
     public ArrayList<Shape> shapes;
    public int indexOfselect=-1;
    private Point changePoint1;
    private Point changePoint2;
    public Buffer(){
    shapes = new ArrayList<Shape>();
    memory = new ArrayList<Integer>();
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
                memory.add(indexOfselect);
                i++;
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
        this.paintShapes(gc);
    }

    public void setChangePoint1(Point p){
        shapes.get(indexOfselect).setStartChange(p);
        this.changePoint1=p;
    }
    public void setChangePoint2(Point p){
        shapes.get(indexOfselect).setFinishChange(p);
        this.changePoint2=p;
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

    public void resizeFigure(GraphicsContext gc) {
        shapes.get(indexOfselect).setFinishPoint(this.changePoint2);
        this.paintShapes(gc);
    }

    public void deleteShape() {
        System.out.println("Delete shape"+shapes.get(indexOfselect).toString());
        shapes.remove(indexOfselect);
        for (int i=0;i<memory.size();i++){
            if (memory.get(i)==indexOfselect) memory.remove(i);
        }
        for (int i=0;i<memory.size();i++){
        if (memory.get(i)>indexOfselect) memory.set(i, memory.get(i)-1 );
        }
    }
}
