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


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 400);

        scene.getStylesheets().add(0, "styles/main.css");

        primaryStage.setTitle("Графический редактор");
        primaryStage.setScene(scene);
        primaryStage.show();

        Controller controller = loader.<Controller>getController();
        controller.focus();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
