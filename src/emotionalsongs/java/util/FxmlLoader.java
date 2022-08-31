/**
 * Provides the Classes that represent the main objects of the program
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.util;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Useful Class that manages the loading of fxml files.   
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class FxmlLoader {
    
    /**Useful fxml element */
    private Pane ui;

    /**
     * Creates the fxml Pane object initialised with a fxml file passed by the String
     * @param file name of the file 
     * @return fxml Pane object
     */
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

    /**
     * Creates the fxml Pane object initialised with a fxml file representing a microcomponent passed by the String
     * @param file name of the file 
     * @return fxml Pane object
     */
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

    /**
     * Gets the object loader of the microcomponent file
     * @param file name of the file 
     * @return FXML loader object
     */
    public FXMLLoader getLoader(String file)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/emotionalsongs/resources/microcomponents/" + file + ".fxml"));
        return loader;

    }

    /**
     * Gets the object loader of the component file
     * @param file name of the file 
     * @return FXML loader object
     */
    public FXMLLoader getComponentsLoader(String file)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/emotionalsongs/resources/components/" + file + ".fxml"));
        return loader;

    }

}
