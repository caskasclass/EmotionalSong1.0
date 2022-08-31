package emotionalsongs.java.controllers.componentscontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.PlaylistManager;
import emotionalsongs.java.controllers.microcontrollers.AddPlaylistBoxController;
import emotionalsongs.java.controllers.microcontrollers.PlaylistBoxController;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.Playlist;
import emotionalsongs.java.util.SongTableView;
import emotionalsongs.java.util.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Controller Class for the file home.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class homeComponentController implements Initializable {

    /**fxml element for graphics */
    @FXML
    private HBox UserPlaylistContainer;
    /**fxml element for graphics */
    @FXML
    private HBox notMyPlaylistContainer;
    /**fxml element for graphics */
    @FXML
    private Button myPlayButt;
    /**fxml element for graphics */
    @FXML
    private Button otherPlaylsitButton;
    /**fxml element for graphics */
    @FXML
    private TableView<Canzone> Tabella;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> songindex;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> album;
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
    private TableColumn<Canzone, Void> optionbutton;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> titolo;
    
    /**Sets the User to the current one */
    User u = GlobalsVariables.currentUser;
    /**fxml element for graphics */
    private Label avviso = new Label();
    /**Useful object to load fxml file*/
    FxmlLoader obj = new FxmlLoader();
    /**Useful ArrayList of type Playlist objects */
    ArrayList<Playlist> mine = new ArrayList<Playlist>();
    /**Useful ArrayList of type Playlist objects */
    ArrayList<Playlist> others = new ArrayList<Playlist>();
    /**Useful ArrayList of type Playlist objects that are in the Playlist.data.txt file*/
    ArrayList<Playlist> pl = PlaylistManager.readPlaylist();

    /**Initializes the fxml file*/
    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        Platform.runLater(() -> {
            try {
                createHomeSongUI();
                if (u != null) {
                    filterPlaylist();
                    createHomePlaylistUI();
                    createHomeOthersPlaylistUI();
                } else {
                    Label avviso = new Label("Per usufruire di altre funzioni effettuare il login");
                    avviso.setFont(new Font("Proxima Nova", 25));
                    UserPlaylistContainer.getChildren().add(avviso);
                    myPlayButt.setDisable(true);
                    createHomeOthersPlaylistUI();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    /**
     * Creates the graphic for the playlists of the current user
     * @throws IOException IOException
     */
    private void createHomePlaylistUI() throws IOException {
        UserPlaylistContainer.setPadding(new Insets(15));
        UserPlaylistContainer.setAlignment(Pos.CENTER_LEFT);
        UserPlaylistContainer.setSpacing(20);

        ArrayList<Playlist> playlists = mine;
        if (playlists == null || playlists.isEmpty()) {
            FXMLLoader loader = obj.getLoader("AddPlaylistBox");
            AddPlaylistBoxController addPlaylistBoxController = new AddPlaylistBoxController();
            loader.setController(addPlaylistBoxController);
            Parent ui = loader.load();
            UserPlaylistContainer.getChildren().add(ui);
        } else {
            for (Playlist playlist : mine) {
                FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                playlistBoxController.setPlaylist(playlist);
                loader.setController(playlistBoxController);
                Parent ui = loader.load();
                UserPlaylistContainer.getChildren().add(ui);

            }
        }

    }

    /**
     * Creates the graphic for the playlists of the current user
     * @throws IOException IOException
     */
    private void createHomeSongUI() throws IOException {
        SongTableView table = new SongTableView(Tabella, album, songindex, anno, autore, durata, titolo);
        table.initializeFiltered(15);
        table.addButton(optionbutton);
    }

    /**
     * Creates the graphic for the playlists of other users
     * @throws IOException IOException
     */
    private void createHomeOthersPlaylistUI() throws IOException {
        notMyPlaylistContainer.setPadding(new Insets(15));
        notMyPlaylistContainer.setAlignment(Pos.CENTER_LEFT);
        notMyPlaylistContainer.setSpacing(20);

        if (pl.isEmpty() || pl == null) {
                notMyPlaylistContainer.setAlignment(Pos.CENTER);
                avviso.setText("Nessuna Playlist presente");
                avviso.setFont(new Font("Proxima Nova", 25));
                notMyPlaylistContainer.getChildren().add(avviso);
                otherPlaylsitButton.setDisable(true);

        } else{
            if (u != null) {
                if (others.isEmpty() || others == null) {
                    notMyPlaylistContainer.setAlignment(Pos.CENTER);
                    avviso = new Label("Nessuna Playlist presente");
                    avviso.setFont(new Font("Proxima Nova", 25));
                    notMyPlaylistContainer.getChildren().add(avviso);
                    otherPlaylsitButton.setDisable(true);
                } else {
                    for (Playlist playlist : others) {
                        FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                        PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                        playlistBoxController.setPlaylist(playlist);
                        loader.setController(playlistBoxController);
                        Parent ui = loader.load();
                        notMyPlaylistContainer.getChildren().add(ui);
                    }
                }
            } else {
                for (Playlist playlist : pl) {
                    FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                    PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                    playlistBoxController.setPlaylist(playlist);
                    loader.setController(playlistBoxController);
                    Parent ui = loader.load();
                    notMyPlaylistContainer.getChildren().add(ui);
                }
            }
    
        }
    }

    /**
     * Method that sets the center of the BorderPane with the graphic of the songs window
     * @param e javafx action event
     * @throws IOException IOException
     */
    public void showSongs(ActionEvent e) throws IOException {

        Pane ui = obj.getPane("songs");
        GlobalsVariables.left_side_bpane.setCenter(ui);

    }

    /**
     * Method that sets the center of the BorderPane with the graphic of the current user's playlists window
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void showMyPlaylists(ActionEvent e) throws IOException {

        FXMLLoader load = obj.getComponentsLoader("playlist");
        playlistWindController playlistWindController = new playlistWindController();
        playlistWindController.setLemie(true);
        load.setController(playlistWindController);
        Parent ui = load.load();
        Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        GlobalsVariables.left_side_bpane.setCenter(ui);

    }

    /**
     * Method that sets the center of the BorderPane with the graphic of other users' playlists window
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void showPlaylists(ActionEvent e) throws IOException {

        FXMLLoader load = obj.getComponentsLoader("playlist");
        playlistWindController playlistWindController = new playlistWindController();
        playlistWindController.setLemie(false);
        load.setController(playlistWindController);
        Parent ui = load.load();
        Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        GlobalsVariables.left_side_bpane.setCenter(ui);

    }

    /**Filters the list of playlists that are in the Playlist.data.txt file based on the user Id. */
    private void filterPlaylist() {
        ArrayList<Playlist> pl = PlaylistManager.readPlaylist();
        for (Playlist playlist : pl) {
            if (playlist.getOwner().equals(u.getId())) {
                mine.add(playlist);
            } else {
                others.add(playlist);
            }

        }

    }
}