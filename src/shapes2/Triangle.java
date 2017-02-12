package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Светлана on 11.02.2017.
 */
public class Triangle extends Shape {
    Point centerAngle;
    Point leftDownAngle;
    Point rightDownAngle;
    Point helpPoint;



    public void setCenterAngle(Point leftUpAngle) {
        this.centerAngle = leftUpAngle;
    }
    public Point getCenterAngle() {
        return centerAngle;
    }
    public Point getRightDownAngle() {
        return rightDownAngle;
    }
    public void setRightDownAngle(Point rightDownAngle) {
        this.rightDownAngle = rightDownAngle;
    }
    public void setLeftDownAngle(Point leftDownAngle) {
        this.leftDownAngle = leftDownAngle;
    }
    public Point getLeftDownAngle() {
        return leftDownAngle;
    }

    //доп перем для определения в какую сторону нарисован треуголбник
    private String vKakuyuStoronuTreeugolnik="";
    //эта точка лежит в центре противоположной стороны от первого угла(центрального), ровно по середине
    public void setHelpPoint(Point helpPoint) {
        this.helpPoint = helpPoint;
    }

    public Point getHelpPoint() {
        return helpPoint;
    }
//устанавливает углы,если не установлены
    public void setAngles(){
        int dlinaVisoti=0;
        //если треугольник рисуем вниз
        if (this.getHelpPoint().getY() > this.getCenterAngle().getY()   )
        {
            vKakuyuStoronuTreeugolnik = "вниз";
            dlinaVisoti=this.getHelpPoint().getY()-this.getCenterAngle().getY();
            this.setRightDownAngle(new Point(this.getHelpPoint().getX()-(dlinaVisoti/2), this.getHelpPoint().getY()));
            this.setLeftDownAngle(new Point(this.getHelpPoint().getX()+(dlinaVisoti/2), this.getHelpPoint().getY()));

        }
        if (this.getHelpPoint().getY() < this.getCenterAngle().getY()   )
        {
            vKakuyuStoronuTreeugolnik = "вверх";
            dlinaVisoti=this.getCenterAngle().getY()-this.getHelpPoint().getY();
            this.setRightDownAngle(new Point(this.getHelpPoint().getX()-(dlinaVisoti/2), this.getHelpPoint().getY()));
            this.setLeftDownAngle(new Point(this.getHelpPoint().getX()+(dlinaVisoti/2), this.getHelpPoint().getY()));

        }
        if (this.getHelpPoint().getX() > this.getCenterAngle().getX()   )
        {
            vKakuyuStoronuTreeugolnik = "вправо";
            dlinaVisoti=this.getHelpPoint().getX()-this.getCenterAngle().getX();
            this.setRightDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()+(dlinaVisoti/2)));
            this.setLeftDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()-(dlinaVisoti/2)));

        }
        if (this.getHelpPoint().getX() < this.getCenterAngle().getX()   )
        {
            vKakuyuStoronuTreeugolnik = "влево";
            dlinaVisoti=this.getHelpPoint().getX()-this.getCenterAngle().getX();
            this.setRightDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()-(dlinaVisoti/2)));
            this.setLeftDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()+(dlinaVisoti/2)));

        }

    }
//
    public void setTrueAngles(){

    }

    @Override
    public void paintShape(GraphicsContext gc){
       setAngles();
        if (this.select) gc.setStroke(Color.RED);
        else gc.setStroke(Color.BLACK);

        this.getCenterAngle().setPoint(gc);

        gc.strokeLine((double) this.getCenterAngle().getX(),
                (double) this.getCenterAngle().getY(),(double) this.getRightDownAngle().getX(), (double) this.getRightDownAngle().getY());
        gc.strokeLine((double) this.getCenterAngle().getX(),
                (double) this.getCenterAngle().getY(), (double) this.getLeftDownAngle().getX(),(double)  this.getLeftDownAngle().getY() );
        gc.strokeLine((double)this.getRightDownAngle().getX(),
                (double) this.getRightDownAngle().getY(),(double) this.getLeftDownAngle().getX(),(double) this.getLeftDownAngle().getY());
    }

    public boolean easySelect(int ii , int jj){
        for (int i =ii-200;i<(ii+200);i++)
        {
            if (i==jj) return true;
        }
        return false;
    }

    //перепределенный метод выбора фигуры(прямоугольника)
    @Override
    public boolean isSelected(Point findingPoint){
        this.setAngles();
        //проверяем точку на принадлежность к верхней  стороне
        //если точка CenterAngle левее RightDownAngle]
        int x1=this.getCenterAngle().getX(), y1=this.getCenterAngle().getY();
        int x2=this.getLeftDownAngle().getX(), y2=this.getLeftDownAngle().getY();
        int x3=this.getRightDownAngle().getX(), y3=this.getRightDownAngle().getY();
        int x0=findingPoint.getX(), y0=findingPoint.getY();
        int a = (x1 - x0) * (y2 - y1) - (x2 - x1) * (y1 - y0);
        int b = (x2 - x0) * (y3 - y2) - (x3 - x2) * (y2 - y0);
        int c = (x3 - x0) * (y1 - y3) - (x1 - x3) * (y3 - y0);
        //закоментирован вывод значений
        //      System.out.println(" a= "+a+" b= "+b+"c="+c);
        if (easySelect(a,0) || easySelect(b, 0) || easySelect(c , 0 )){
            this.select=true;
            return this.select;
        }

        this.select=false;
        return this.select;
    }


}
