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

    @Override
    public void setFinishPoint(Point finishPoint){

        this.setHelpPoint(finishPoint);

    }
    @Override
    public void setStartPoint(Point startPoint){
        this.setCenterAngle(startPoint);
        this.startPoint =startPoint;
    }
    //доп перем для определения в какую сторону нарисован треуголбник
    private String napravTreug ="";
    //эта точка лежит в центре противоположной стороны от первого угла(центрального), ровно по середине
    public void setHelpPoint(Point helpPoint) {
        this.helpPoint = helpPoint;
    }

    public Point getHelpPoint() {
        return helpPoint;
    }
//устанавливает углы,если не установлены
    public void setAngles(){
        if (this.getHelpPoint().getX()!=0){
            int dlinVis=0;
            //если треугольник рисуем вниз
            if (this.getHelpPoint().getY() > this.getCenterAngle().getY()   )
            {
                napravTreug = "вниз";
                dlinVis=this.getHelpPoint().getY()-this.getCenterAngle().getY();
                this.setRightDownAngle(new Point(this.getHelpPoint().getX()-(dlinVis/2), this.getHelpPoint().getY()));
                this.setLeftDownAngle(new Point(this.getHelpPoint().getX()+(dlinVis/2), this.getHelpPoint().getY()));

            }
            if (this.getHelpPoint().getY() < this.getCenterAngle().getY()   )
            {
                napravTreug = "вверх";
                dlinVis=this.getCenterAngle().getY()-this.getHelpPoint().getY();
                this.setRightDownAngle(new Point(this.getHelpPoint().getX()-(dlinVis/2), this.getHelpPoint().getY()));
                this.setLeftDownAngle(new Point(this.getHelpPoint().getX()+(dlinVis/2), this.getHelpPoint().getY()));

            }
            if (this.getHelpPoint().getX() > this.getCenterAngle().getX()   )
            {
                napravTreug = "вправо";
                dlinVis=this.getHelpPoint().getX()-this.getCenterAngle().getX();
                this.setRightDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()+(dlinVis/2)));
                this.setLeftDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()-(dlinVis/2)));

            }
            if (this.getHelpPoint().getX() < this.getCenterAngle().getX()   )
            {
                napravTreug = "влево";
                dlinVis=this.getHelpPoint().getX()-this.getCenterAngle().getX();
                this.setRightDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()-(dlinVis/2)));
                this.setLeftDownAngle(new Point(this.getHelpPoint().getX(), this.getHelpPoint().getY()+(dlinVis/2)));

            }
        }
    }
//
    public void setTrueAngles(){

    }

    @Override
    public void paintShape(GraphicsContext gc){


        if (this.select){
            setAngles();
            this.getCenterAngle().setPoint(gc);
            gc.setStroke(Color.RED);
        }
        else{
            setAngles();
            gc.setStroke(Color.BLACK);
            this.getCenterAngle().setPoint(gc);
        }



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
    @Override
    public void changePosition(){
        int changeX=this.getFinishChange().getX()-this.getStartChange().getX();
        int changeY=this.getFinishChange().getY()-this.getStartChange().getY();
        Point p1=new Point(this.getRightDownAngle().getX()+changeX, this.getRightDownAngle().getY()+changeY);
        this.setRightDownAngle(p1);

        Point p2=new Point(this.getLeftDownAngle().getX()+changeX, this.getLeftDownAngle().getY()+changeY);
        this.setLeftDownAngle(p2);

        Point p3=new Point(this.getCenterAngle().getX()+changeX, this.getCenterAngle().getY()+changeY);
        this.setCenterAngle(p3);

        Point p4=new Point(this.getHelpPoint().getX()+changeX, this.getHelpPoint().getY()+changeY);
        this.setHelpPoint(p4);



        p1 = this.getFinishChange();
        this.setStartChange(p1);
        return ;
    }

}
