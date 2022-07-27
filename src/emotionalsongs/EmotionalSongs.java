package emotionalsongs;

import java.io.IOException;
import java.text.ParseException;

import emotionalsongs.java.Managers.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmotionalSongs extends Application {
    StyleManager style = new StyleManager();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/view/HomeWindow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.mainStyle());
        primaryStage.setTitle("EmotionalSong");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) throws ParseException {
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
