package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Светлана on 07.02.2017.
 */
public class Circle extends Shape {
    private Point leftUpAngle;
    private double diametr;



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
    @Override
    public void paintShape(GraphicsContext gc){
        gc.setStroke(Color.BLACK);
        System.out.println("в круге " +this.getLeftUpAngle().getX()+ " "+this.getLeftUpAngle().getY());
        gc.strokeOval((double) this.getLeftUpAngle().getX(),(double) this.getLeftUpAngle().getY(), this.getDiametr(), this.getDiametr());
    }

}
