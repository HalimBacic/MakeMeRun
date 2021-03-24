package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("MakeMeRun");
        primaryStage.setScene(new Scene(root, 936, 591));
        primaryStage.show();

        MakeMeRun runner = new MakeMeRun();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
