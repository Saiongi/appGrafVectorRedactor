package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import shapes.Circle;
import shapes.Point;
import shapes.Square;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        Canvas canvas = canvas = new Canvas(400, 300);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
      //  Square sqr = new Square();
        Point p = new Point();
        p.setY(50);
        p.setX(50);
        p.setColor(Color.BLACK);

        Circle crcl = new Circle();
        crcl.setCenter(p);
        crcl.setRadius(30);
        crcl.setCircle(gc);

        /*
        p.setColor(Color.BLACK);
        sqr.setLeftUpAngle(p);
        sqr.setLengthSide(20);
        sqr.setSquare(gc);
        */



        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
