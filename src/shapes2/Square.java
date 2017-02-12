package shapes2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


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
    public void setLeftDownAngle(Point leftDownAngle) {
        this.leftDownAngle = leftDownAngle;
    }
    public Point getLeftDownAngle() {
        return leftDownAngle;
    }
    public void setRightUpAngle(Point rightUpAngle) {
        this.rightUpAngle = rightUpAngle;
    }
    public Point getRightUpAngle() {
        return rightUpAngle;
    }

    @Override
    public void paintShape(GraphicsContext gc){
        if (this.select) gc.setStroke(Color.RED);
        else gc.setStroke(Color.BLACK);
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

    //метод проверки принадлежности координат
    public boolean easySelect(int ii , int jj){
        for (int i =ii-3;i<(ii+3);i++)
        {
            if (i==jj) return true;
        }
        return false;
    }


    //метод по определению какая из точек прямоугольника правее/левее и выше/ниже
    public void setAngles(){
        Point leftUp, leftDown,rightDown,  rightUp;
        int leftX=0, upY=0, rightX=0, downY=0;
        //роверяем, какая из точек(начальная точка фигуры или конечная) находятся левее по оси Ox

        if (this.getLeftUpAngle().getX() > this.getRightDownAngle().getX()){
            //если точка левее
            //левый x
            leftX = this.getRightDownAngle().getX();
            //правый x
            rightX = this.getLeftUpAngle().getX();
            if (this.getRightDownAngle().getY()<this.getLeftUpAngle().getY()){
                //левее и выше - она левый верхний угол
                //верхний y
                upY=this.getRightDownAngle().getY();
                //нижний y
                downY=this.getLeftUpAngle().getY();

                //левый верхний угол
                leftUp=this.getRightDownAngle();
                //правый нижний угол
                rightDown = this.getLeftUpAngle();
                //левый нижний угол
                leftDown = new Point(leftX, downY);
                //правый верхний угол
                rightUp = new Point(rightX,upY);
            }else {
                //левее и ниже - она левый нижний угол
                //верхний y
                upY= this.getLeftUpAngle().getY();
                //нижний y
                downY = this.getRightDownAngle().getY();
                //левый верхний угол
                leftUp= new Point(leftX,upY);
                //правый нижний угол
                rightDown=new Point(rightX,downY);
                //левый нижний угол
                leftDown=this.getRightDownAngle();
                //правый верхний угол
                rightUp = this.getLeftUpAngle();
            }
        }
        else {
            //если точка правее
            leftX = this.getLeftUpAngle().getX();
            rightX = this.getRightDownAngle().getX();
            if (this.getRightDownAngle().getY()<this.getLeftUpAngle().getY()) {
                //правее и выше - она правый верхний угол
            upY = this.getRightDownAngle().getY();
            downY = this.getLeftUpAngle().getY();
                //левый верхний угол
            leftUp=new Point(leftX,upY);
                //правый нижний угол
            rightDown=new Point(rightX, downY);
                //левый нижний угол
            leftDown=this.getLeftUpAngle();
                //правый верхний угол
            rightUp=this.getRightDownAngle();

            }else{
                //правее и ниже - она правый нижний угол
                upY=this.getLeftUpAngle().getY();
                downY=this.getRightDownAngle().getY();

                //левый верхний угол
                leftUp=this.getLeftUpAngle();
                //правый нижний угол
                rightDown=this.getRightDownAngle();
                //левый нижний угол
                leftDown=new Point(leftX,downY);
                //правый верхний угол
                rightUp=new Point(rightX, upY);



            }
        }
        this.setRightDownAngle(rightDown);
        this.setLeftUpAngle(leftUp);
        this.setRightUpAngle(rightUp);
        this.setLeftDownAngle(leftDown);
    }

    //перепределенный метод выбора фигуры(прямоугольника)
    @Override
    public boolean isSelected(Point findingPoint){
        this.setAngles();
        //проверяем точку на принадлежность к верхней  стороне
        for (int i=this.getLeftUpAngle().getX(); i<=this.getRightUpAngle().getX(); i++)
        {   //если точка совпадает по x
            if (this.easySelect(i,findingPoint.getX())) {
                //если точка совпадает по y
                if (this.easySelect(this.getLeftUpAngle().getY(),findingPoint.getY()))
                {
                    //нужно реализовать окрас фигуры
                    selectedColor();
                    this.select=true;
                    return this.select;
                }
            }
        }
        //проверяем точку на принадлежность к нижней  стороне
        for (int i=this.getLeftDownAngle().getX(); i<=this.getRightDownAngle().getX(); i++)
        {   //если точка совпадает по x
            if (this.easySelect(i,findingPoint.getX())){
                //если точка совпадает по y
                if(this.easySelect(this.getLeftDownAngle().getY(),findingPoint.getY())) {
                    selectedColor();
                    this.select = true;
                    return this.select;
                }

            }
        }
        //проверяем точку на принадлежность к левой  стороне
        for (int i=this.getLeftUpAngle().getY(); i<=this.getLeftDownAngle().getY(); i++)
        {

            //если точка совпадает по y
            if (this.easySelect(i,findingPoint.getY()))
            {
                //если точка совпадает по x
                if(this.easySelect(this.getLeftUpAngle().getX(),findingPoint.getX()))
                {
                    selectedColor();
                    this.select = true;
                    return this.select;
                }

            }
        }
        //проверяем точку на принадлежность к правой  стороне
        for (int i=this.getRightUpAngle().getY(); i<=this.getRightDownAngle().getY(); i++)
        {   //если точка совпадает по y
            if (this.easySelect(i,findingPoint.getY()))
            {
                //если точка совпадает по x
                if(this.easySelect(this.getRightUpAngle().getX(),findingPoint.getX()))
                {
                    selectedColor();
                    this.select = true;
                    return this.select;
                }

            }
        }
        this.select=false;
        return this.select;
    }

    public void selectedColor(){
        this.getLeftDownAngle().setColor(Color.RED);
        this.getRightDownAngle().setColor(Color.RED);
        this.getLeftUpAngle().setColor(Color.RED);
        this.getRightUpAngle().setColor(Color.RED);
    }

}
