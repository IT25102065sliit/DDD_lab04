package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        stage.setScene(new Scene(root, 650, 520));
        stage.setTitle("University Management System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}