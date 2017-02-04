package shapes;
import com.sun.javafx.scene.paint.GradientUtils;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * Class Point хранит координаты точки (x,y), наседуется от Shape,
 */
public class Point extends Shape {

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

}
