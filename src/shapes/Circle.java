package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Светлана on 04.02.2017.
 */
public class Circle extends Shape {
    //радиус окружности
    private int radius;
    //точка центра окружности
    private Point center;
    //геттеры и сеттеры
    public Point getCenter() {
        return center;
    }
    public void setCenter(Point center) {
        this.center = center;
    }
    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setCircle(GraphicsContext gc)
    {   //получаем радиус
        int rad = this.getRadius();
        int x1 = this.getCenter().getX();
        int y1 = this.getCenter().getY();
        int x=0, y = rad, delta = 1 - 2 * rad, error=0;
        Point p = new Point();
        p.setColor(Color.BLACK);
        while (y >= 0) {
            p.setX(x1+x);
            p.setY(y1+y);
            p.setPoint(gc);

            p.setX(x1+x);
            p.setY(y1-y);
            p.setPoint(gc);

            p.setX(x1-x);
            p.setY(y1+y);
            p.setPoint(gc);

            p.setX(x1-x);
            p.setY(y1-y);
            p.setPoint(gc);

            error = 2 * (delta + y) - 1;
            if ((delta < 0) && (error <= 0)) {
                delta += 2 * ++x + 1;
                continue;
            }
            error = 2 * (delta - x) - 1;
            if ((delta > 0) && (error > 0))
            {    delta += 1 - 2 * --y;
            continue;
            }
            x++;
            delta += 2 * (x - y);
            y--;
        }
    }
    /*
    // R - радиус, X1, Y1 - координаты центра
    int x := 0
    int y := R
    int delta := 1 - 2 * R
    int error := 0

            while (y >= 0)
    drawpixel(X1 + x, Y1 + y)
    drawpixel(X1 + x, Y1 - y)
    drawpixel(X1 - x, Y1 + y)
    drawpixel(X1 - x, Y1 - y)
    error = 2 * (delta + y) - 1
            if ((delta < 0) && (error <= 0))
    delta += 2 * ++x + 1
            continue
    error = 2 * (delta - x) - 1
            if ((delta > 0) && (error > 0))
    delta += 1 - 2 * --y
           continue
    x++
    delta += 2 * (x - y)
    y--
    */
}
