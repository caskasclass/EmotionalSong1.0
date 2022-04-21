package main.java;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.Managers.StyleManager;

public class App extends Application {
    StyleManager style = new StyleManager();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/view/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.mainStyle());
        primaryStage.setTitle("EmotionalSong");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);

    }

    public void changeScene(String fxml) throws IOException {
        Stage stg = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stg.setScene(scene);
        stg.show();

    }
}
