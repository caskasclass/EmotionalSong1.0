/*Beatrice Bastianello, matricola 751864
Nazar Viytyuk, matricola 748964
sede VA*/
package emotionalsongs.java.util;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Useful Class that  every possible emotion that will be evaluated in ValutazioneUtente.   
 * @author Beatrice Bastianello
 * @author Nazar Viytyuk
 */
public class FxmlLoader {
    private Pane ui;

    public Pane getPane(String file) {

        try {

            URL fileUrl = getClass().getResource("/emotionalsongs/resources/components/" + file + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("File non trovato");
            }

            ui = FXMLLoader.load(fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ui;
    }
    public Pane getMicroPane(String file)
    {
        try {

            URL fileUrl = getClass().getResource("/emotionalsongs/resources/microcomponents/" + file + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("File non trovato");
            }

            ui = FXMLLoader.load(fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ui;
    }
    public FXMLLoader getLoader(String file)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/emotionalsongs/resources/microcomponents/" + file + ".fxml"));
        return loader;

    }
    public FXMLLoader getComponentsLoader(String file)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/emotionalsongs/resources/components/" + file + ".fxml"));
        return loader;

    }

}
