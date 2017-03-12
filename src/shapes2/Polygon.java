package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Светлана on 12.02.2017.
 */
public class Polygon extends Shape {
    private ArrayList <Point> poligonAngles;
    private Point center;
    private int countOfSide=0;
    private int radius=0;



    public void setCenter(Point center) {
        this.center = center;
    }
    public Point getCenter() {
        return center;
    }
    @Override
    public void setCountOfSide(int countOfSide) {
        this.countOfSide = countOfSide;
    }
    public int getCountOfSide() {
        return countOfSide;
    }

    public void setPoligonAngles(ArrayList<Point> poligonAngles) {
        this.poligonAngles = poligonAngles;
    }
    public ArrayList<Point> getPoligonAngles() {
        return poligonAngles;
    }
    public void setRadius(Point finishPoint) {
        int x1=this.getCenter().getX();
        int x2=finishPoint.getX();
        int y1=this.getCenter().getY();
        int y2=finishPoint.getY();
        double radius= Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
        this.radius =(int) radius;
    }
    public int getRadius() {
        return radius;
    }
    //метод по попределению координат углов
    public void setAngles() {
        //создаем лист для хранения точек углов()
        ArrayList <Point> angles= new ArrayList<Point>();
        Point p;
        double z = 0; int i=0;
        double angle=(double)(360.0 / (double)this.getCountOfSide());
     //   System.out.println("new");
        while (i<this.getCountOfSide()+1)
        {
            /*
            p[i].x=Cntr.x+(int)( round(cos(z/180*Math.PI)*R) );
            p[i].y=Cntr.y-(int)( round(sin(z/180*Math.PI)*R) );
            */

            p=new Point(this.getCenter().getX()+(int)( Math.round(Math.cos(z/180*Math.PI)*this.getRadius()) ),
                    this.getCenter().getY()+(int)( Math.round(Math.sin(z/180*Math.PI)*this.getRadius()) ));
         //   System.out.println("p(X)="+p.getX()+" p(Y)="+p.getY());

            angles.add(p);
            z=z+angle;
            i++;
        }

        this.setPoligonAngles(angles);
    }

    @Override
    public void setFinishPoint(Point finishPoint){

        this.setRadius(finishPoint);
    }
    @Override
    public void setStartPoint(Point startPoint){
        this.startPoint =startPoint;
        this.setCenter(startPoint);

    }

    @Override
    public void paintShape(GraphicsContext gc) {
       if (!this.select){
           setAngles();
       }
        setAngles();//вытащила из if сверху для причины выяснения непрорисовки масштабируемости в процессе

        if (this.select) gc.setStroke(Color.RED);
        else gc.setStroke(Color.BLACK);

        for (int i = 0; i < this.getPoligonAngles().size()-1; i++){

            gc.strokeLine((double) this.getPoligonAngles().get(i).getX(),
                    (double) this.getPoligonAngles().get(i).getY(),
                    (double) this.getPoligonAngles().get(i+1).getX(),
                    (double) this.getPoligonAngles().get(i+1).getY());
        }
    }

    public boolean easySelect(double ii , double jj){
        int iii=(int)ii;
        int jjj=(int)jj;

        for (int i =iii-4;i<(iii+4);i++)
        {
            if (i==jjj) return true;
        }
        return false;
    }

    @Override
    public boolean isSelected(Point findingPoint){


        double x1,y1; // Координаты начала отрезка
        double x2,y2; // Координаты конца отрезка
        double x0=findingPoint.getX(),y0=findingPoint.getY(); // Координаты точки
        double k, c;

        //
        for (int i=0;i < this.getPoligonAngles().size()-1;i++)
        {
            x1=this.getPoligonAngles().get(i).getX();
            y1=this.getPoligonAngles().get(i).getY();
            x2=this.getPoligonAngles().get(i+1).getX();
            y2=this.getPoligonAngles().get(i+1).getY();

            //поверка принадлежности
            if (y0 <= (y1>y2 ? y1:y2)&& y0 >= (y1<y2 ? y1:y2) && x0 <= (x1>x2 ? x1:x2)&& x0 >= (x1<x2 ? x1:x2)){
                if (x1 == x2) {
                    if (easySelect(x0 , x1) && y0 >= (y1<y2 ? y1:y2) && y0 <= (y1>y2 ? y1:y2)){
                        this.select=true;
                        return this.select;
                    }else continue;
                }
                //k = (y2 - y1) / (x2 - x1);
                k=(y1-y2)/(x2-x1);
                c = (-y1 - k * x1);

                System.out.println("y0="+y0+" x0 * k + c="+ (x0 * k + c)+" "+ " c="+c);
                if (easySelect(y0,-(x0 * k + c))){
                    this.select=true;
                    return this.select;
                }

            }else continue;


        }
        this.select=false;
        return this.select;
    }

    @Override
    public void changePosition(){
        Point p1;
        int changeX=this.getFinishChange().getX()-this.getStartChange().getX();
        int changeY=this.getFinishChange().getY()-this.getStartChange().getY();
        for (int i = 0; i < this.getPoligonAngles().size(); i++) {

            p1 = new Point(this.getPoligonAngles().get(i).getX() + changeX, this.getPoligonAngles().get(i).getY() + changeY);
            this.getPoligonAngles().set(i,p1);

        }
        Point p2= new Point(this.getCenter().getX() + changeX, this.getCenter().getY() + changeY);
        this.setCenter(p2);
        p1 = this.getFinishChange();
        this.setStartChange(p1);

    }
/*
* double max(double x, double y)
{
    if (x < y) {
        return x;
    }
    return x;
}

double min(double x, double y)
{
    if (x > y) {
        return y;
    }
    return x;
}


bool thc(double x, double y, double z, double w, double a, double b)
{
    double k, c;

    if (z == x) {
        return (a == x && b >= min(y, w) && x <= max(y, w));
    }

    k = (w - y) / (z - x);
    c = y - k * x;

    return b == a * k + c;
}

int main(int argc, char* argv[])
{
    setlocale(LC_ALL, "Russian");

    double x, y; // Координаты начала отрезка
    double z, w; // Координаты конца отрезка
    double a, b; // Координаты точки

    bool result;

    cout << "Координаты начала отрезка: ";
    cin >> x >> y;

    cout << "Координаты конца отрезка: ";
    cin >> z >> w;

    cout << "Координаты точки: ";
    cin >> a >> b;

    result = thc(x, y, z, w, a, b);
    cout << result << endl;
    system("pause");

    return 0;
}
* */




}
