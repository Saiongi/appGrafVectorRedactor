package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import shapes2.Shape;

/**
 * Class Point хранит координаты точки (x,y), наседуется от Shape,
 */
public class Point extends Shape {
    public Point(int x, int y){
        this.setColor(Color.BLACK);
        this.setY(y);
        this.setX(x);
    }

    private int x;
    private int y;
    private Color color;

    public void setX(int x)
    {
        this.x = x;
    }
    public int getX()
    {
        return this.x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getY()
    {
        return this.y;
    }
    public void setColor(Color c)
    {
        this.color = c;
    }
    public Color getColor()
    {
        return this.color;
    }


    public void setPoint(GraphicsContext gc){
        PixelWriter pw = gc.getPixelWriter(); // возвращает PixelWriter, который дает доступ к пикселям Canvas
        pw.setColor(this.getX(), this.getY(), this.getColor());
    }

    @Override
    public String toString() {

        return super.toString();
    }
}
