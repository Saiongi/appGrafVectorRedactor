package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;


/**
 * Created by Светлана on 07.02.2017.
 */
public class Circle extends Shape {
    private Point leftUpAngle;
    private double diametr;
    private Point center;


    public void setLeftUpAngle(Point leftUpAngle) {
        this.leftUpAngle = leftUpAngle;
    }
    public Point getLeftUpAngle() {
        return leftUpAngle;
    }
    public void setDiametr(double diametr) {
        this.diametr = diametr;
    }
    public double getDiametr() {
        return diametr;
    }
    public void setCenter() {
        Point p=new Point(this.getLeftUpAngle().getX(),this.getLeftUpAngle().getY());
        p.setX(p.getX()+(int)(diametr/2));
        p.setY(p.getY()+(int)(diametr/2));
        this.center=p;
    }
    public Point getCenter() {
        return center;
    }

    @Override
    public void paintShape(GraphicsContext gc){
        if (this.select) {
            gc.setStroke(Color.RED);
        }else  gc.setStroke(Color.BLACK);
        this.setCenter();

        gc.strokeOval((double) this.getLeftUpAngle().getX(),(double) this.getLeftUpAngle().getY(), this.getDiametr(), this.getDiametr());

            }

    //определение центра окружности и радиуса




    @Override
    public boolean isSelected(Point findingPoint){
        double p = Math.pow((findingPoint.getX()-this.getCenter().getX()),2)+
                Math.pow((findingPoint.getY()-this.getCenter().getY()),2);
        double r=Math.pow((diametr/2),2);


        System.out.println("R - P: " + abs(r-p));
        if ( abs(r - p) < 500 ){
            this.select = true;
            return this.select;
        }
        return false;
    }
    @Override
    public void changePosition(){
        int changeX=this.getFinishChange().getX()-this.getStartChange().getX();
        int changeY=this.getFinishChange().getY()-this.getStartChange().getY();
        Point p1=new Point(this.getLeftUpAngle().getX()+changeX, this.getLeftUpAngle().getY()+changeY);
        this.setLeftUpAngle(p1);
        p1 = this.getFinishChange();
        this.setStartChange(p1);
        return;
    }


}
