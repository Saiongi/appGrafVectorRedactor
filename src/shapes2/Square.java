package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import shapes2.Point;
import shapes2.Shape;
import javafx.scene.input.MouseEvent;



/**
 * Created by Светлана on 04.02.2017.
 */
public class Square extends Shape {

    Point leftUpAngle;
    Point rightUpAngle;
    Point leftDownAngle;
    Point rightDownAngle;



    public void setLeftUpAngle(Point leftUpAngle) {
        this.leftUpAngle = leftUpAngle;
    }
    public Point getLeftUpAngle() {
        return leftUpAngle;
    }

    public Point getRightDownAngle() {
        return rightDownAngle;
    }
    public void setRightDownAngle(Point rightDownAngle) {
        this.rightDownAngle = rightDownAngle;
    }

    @Override
    public void paintShape(GraphicsContext gc){

        this.getLeftUpAngle().setPoint(gc);
        gc.strokeLine((double) this.getLeftUpAngle().getX(),
                (double) this.getLeftUpAngle().getY(),(double) this.getRightDownAngle().getX(), (double) this.getLeftUpAngle().getY());
        gc.strokeLine((double) this.getLeftUpAngle().getX(),
                (double) this.getLeftUpAngle().getY(), (double) this.getLeftUpAngle().getX(),(double)  this.getRightDownAngle().getY() );
        gc.strokeLine((double)this.getRightDownAngle().getX(),
                (double) this.getLeftUpAngle().getY(),(double) this.getRightDownAngle().getX(),(double) this.getRightDownAngle().getY());
        gc.strokeLine((double) this.getLeftUpAngle().getX(),
                (double)this.getRightDownAngle().getY(),(double) this.getRightDownAngle().getX(), (double)this.getRightDownAngle().getY() );
    }



}
