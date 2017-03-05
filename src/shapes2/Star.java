package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Светлана on 19.02.2017.
 */
public class Star extends  Polygon {
    public boolean rotating=false;
   private int radius2;
   private double angle;
   private Point pointForRotate1;
    private Point pointForRotate2;
    //добавляем внутренний радиус
    public void setRadius2(){

        this.radius2 = this.getRadius()/3;
    }
    public int getRadius2() {
        return radius2;
    }
    public void setPointForRotate1(Point p){
        this.pointForRotate1 =p;
    }
    public Point getPointForRotate1() {
        return pointForRotate1;
    }
    public void setPointForRotate2(Point p){
        this.pointForRotate2 = p;
    }
    public Point getPointForRotate2() {
        return pointForRotate2;
    }
    //нахождение угла для поворота по трем точкам и уего установление
    public void setAngle() {

        Point a=this.getPointForRotate1();
        Point b=this.getCenter();
        Point c=this.getPointForRotate2();
        //  point a, point b, point c;
        double x1 = a.getX() - b.getX(), x2 = c.getX() - b.getX();
        double y1 = a.getY() - b.getY(), y2 = c.getY() - b.getY();
        double d1 = Math.sqrt (x1 * x1 + y1 * y1);
        double d2 = Math.sqrt (x2 * x2 + y2 * y2);
        this.angle= Math.acos ((x1 * x2 + y1 * y2) / (d1 * d2));
    }
    public double getAngle() {
        return angle;
    }

    //вычисление вершин звезды
    @Override
    public void setAngles() {
        float a=0;
        ArrayList<Point> angles= new ArrayList<Point>();
        Point p1;
        Point p2=new Point(0,0);

        //Цикл расчета вершин звезды
        for (int i=0;i<this.getCountOfSide()*2+1;i++)
        {
            if ((i%2)==1) //При выполнении условия четности следующие формулы
            {

                p1=new Point((int)(this.getCenter().getX() + this.getRadius2() * Math.cos(a*Math.PI/180)),//getradius2 / 2 убрала
                             (int)(this.getCenter().getY() - this.getRadius2() * Math.sin(a*Math.PI/180)));//

            }
            else //При невыполнении условия четности следующие формулы
            {
                p1=new Point((int)(this.getCenter().getX() + this.getRadius() * Math.cos(a*Math.PI/180)),
                (int)(this.getCenter().getY() - this.getRadius() * Math.sin(a*Math.PI/180)));

            }
            angles.add(i, p1);
            a=a+180/ this.getCountOfSide();

        }
        //Завершаем построение звезды соединяя её окончание с начальной точкой
        angles.add(this.getCountOfSide()*2, angles.get(0));
        this.setPoligonAngles(angles);
    }
    /*ФУНКЦИЯ ПОСТРОЕНИЯ ЗВЕЗДЫ*/
    @Override
    public void paintShape(GraphicsContext gc) {
        if (!this.select) {
            this.setRadius2();
            this.setAngles();
        }
        if (this.select) gc.setStroke(Color.RED);
        else gc.setStroke(Color.BLACK);

        for (int i = 0; i < this.getPoligonAngles().size()-2; i++){

            gc.strokeLine((double) this.getPoligonAngles().get(i).getX(),
                    (double) this.getPoligonAngles().get(i).getY(),
                    (double) this.getPoligonAngles().get(i+1).getX(),
                    (double) this.getPoligonAngles().get(i+1).getY());
        }
    }
    //поворот фигуры
    @Override
    public void rotate(GraphicsContext gc){
        Point p;
        this.setAngle();
        System.out.println("Angle: "+this.getAngle());
        System.out.println("new^");
        for (int i = 0; i < this.getPoligonAngles().size(); i++){
            p=new Point(0,0);

            p.setX((int)(Math.cos(this.getAngle())*(this.getPoligonAngles().get(i).getX()-this.getCenter().getX())
                  - Math.sin(this.getAngle())*(this.getPoligonAngles().get(i).getY()-this.getCenter().getY())+this.getCenter().getX()));

            p.setY((int)(Math.sin(this.getAngle())*(this.getPoligonAngles().get(i).getX()-this.getCenter().getX())
                    + Math.cos(this.getAngle())*(this.getPoligonAngles().get(i).getY()-this.getCenter().getY())+this.getCenter().getY()));

                System.out.println(" ish X:"+this.getPoligonAngles().get(i).getX()+" Y: "+this.getPoligonAngles().get(i).getY());
                this.getPoligonAngles().set(i, p);
                System.out.println(" nov "+this.getPoligonAngles().get(i).getX()+" Y: "+this.getPoligonAngles().get(i).getY());
        }
        //перерисовываем
        //this.rotating=true;
        this.paintShape(gc);

    }

    public void rotate2(GraphicsContext gc){
        double alpha=this.getAngle();
        double a=Math.cos((360-alpha)*Math.PI/180);
        double b=Math.sin((360-alpha)*Math.PI/180);
        double c=-Math.sin((360-alpha)*Math.PI/180);
        double d=Math.cos((360-alpha)*Math.PI/180);

        Point p;
        this.setAngle();
        System.out.println("Angle: "+this.getAngle());
        System.out.println("new^");
        for (int i = 0; i < this.getPoligonAngles().size(); i++) {
            p = new Point(0, 0);
            p.setX((int)(a * this.getPoligonAngles().get(i).getX() + c * this.getPoligonAngles().get(i).getY()) + this.getCenter().getX()); //Новые координаты вершин треугольника
            p.setY((int)(b * this.getPoligonAngles().get(i).getX() + d * this.getPoligonAngles().get(i).getY()) + this.getCenter().getY());

            this.getPoligonAngles().set(i, p);
        }

       // this.paintShape(gc);
    }
}



/*  for (i=1;i<n*2+2;i++)
    {
        lineto(p[i].x,p[i].y);
    }
    */

    /*

    //Последовательное соединение точек массива, хранящего вершины звезды
        for (i=1;i<n*2+2;i++)
        {
            lineto(p[i].x,p[i].y);
        }

        delete []p; //Освобождаем память
    }

    //Функция ввода двух радиусов и числа концов
    void input()
    {
        float R,r,n; //Внешний и внутренний радиусы
        cout<<"Внутренний радиус = ";cin>>r;
        cout<<"Внешний радиус      = ";cin>>R;
        cout<<"Число концов (n)      = ";cin>>n;
        star(R,r,n); //Построение звезды по радиусам
    }
    void main()
    {
        system("CLS");//ПОДГОТОВКА РАБОТЫ С ГРАФИКОЙ
        int gdriver = DETECT, gmode, errorcode;
        initgraph(&gdriver, &gmode, "");

        input(); //Ввод параметров звезды с последующим её построением

        system("PAUSE");
        return;
    }




/*

     //создаем лист для хранения точек углов()
        ArrayList<Point> angles= new ArrayList<Point>();
        Point p1=new Point(0,0);
        Point p2=new Point(0,0);
        Point p3;

        double a = 0;
        double  b = 3.14159265358979323846 / this.getCountOfSide();				//число пи разделенное на количество веершин?
        double  k=0.3;

        p1.setX((this.getCenter().getX() + (int)(this.getRadius() * Math.cos(a))));
        p1.setY((this.getCenter().getY() + (int)(this.getRadius() * Math.sin(a))));
        angles.add(p1);

        for (int i = 0; i < this.getCountOfSide(); i++)
        {

            a = a + b;

            p2=new Point( p1.getX()+(int)(k*this.getRadius() * Math.cos(a)),p1.getY()+(int)(k*this.getRadius() * Math.sin(a)));//первая тчка

            angles.add(p2);
            a = a + b;

            p1=new Point(this.getCenter().getX() + (int)(this.getRadius() * Math.cos(a)),this.getCenter().getY() + (int)(this.getRadius() * Math.sin(a)));

            angles.add(p1);

        }


        this.setPoligonAngles(angles);


        */
