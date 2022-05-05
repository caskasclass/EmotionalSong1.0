package emotionalsongs.java.util;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {
    private Pane ui;

    public Pane getPane(String file) {

        try {

            URL fileUrl = getClass().getResource("../../resources/components/" + file + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("File non trovato");
            }

            ui = FXMLLoader.load(fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ui;
    }

}
