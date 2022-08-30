package emotionalsongs;

import java.text.ParseException;

import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EmotionalSongs extends Application {
    StyleManager style = new StyleManager();

    @Override
    public void start(Stage primaryStage) throws Exception {
        String imgPath = getClass().getResource("resources/images/icon3.png").toExternalForm();
        Parent root = FXMLLoader.load(getClass().getResource("resources/view/HomeWindow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.mainStyle());
        primaryStage.setTitle("EmotionalSong");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(imgPath));
        primaryStage.show();
    } 

    public static void main(String[] args) throws ParseException {
        GlobalsVariables.currentUser = new User("guest","guest","guest","guest","guest","guest",true);
        launch(args);

    }

}
