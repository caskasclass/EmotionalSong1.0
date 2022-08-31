/**
 * Progetto laboratorio A: "Emotional Songs", anno 2021-2022
 */
package emotionalsongs;

import java.text.ParseException;

import emotionalsongs.java.Managers.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Progetto laboratorio A: "Emotional Songs", anno 2021-2022
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 * @version 1.0
 */
public class EmotionalSongs extends Application {
    StyleManager style = new StyleManager();

    @Override
    public void start(Stage primaryStage) throws Exception {
        String imgPath = getClass().getResource("/emotionalsongs/resources/images/icon3.png").toExternalForm();
        Parent root = FXMLLoader.load(getClass().getResource("/emotionalsongs/resources/view/HomeWindow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.mainStyle());
        primaryStage.setTitle("EmotionalSong");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(imgPath));
        primaryStage.show();
    } 

    /**
     * Launches the JavaFX application.
     * @param args main arguments 
     * @throws ParseException exception
     */
    public static void main(String[] args) throws ParseException {
        launch(args);

    }

}
