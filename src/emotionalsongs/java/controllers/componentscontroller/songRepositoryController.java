/**
 * Provides the controller classes necessary to 
 * manage the Scenes(components) of the main window.
 * @see package.emotionalsongs.java.controllers
 */
package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ResourceBundle;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.SongTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * Controller Class for the file songs.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class songRepositoryController implements Initializable {

    /**fxml element for graphics */
    @FXML
    private TextField cercaCanzone;
    /**fxml element for graphics */
    @FXML
    private TableView<Canzone> repository;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> album;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> songindex;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Integer> anno;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> autore;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Double> durata;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> titolo;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> optionbutton;


    /**Initializes the fxml file*/
    @Override
    public void initialize(URL urilink, ResourceBundle reb) {
        
        SongTableView table = new SongTableView(repository, album, songindex, anno, autore, durata, titolo);
        table.initialize();
        table.addButton(optionbutton);
        table.cercaBranoMusicale(cercaCanzone);

    }
}