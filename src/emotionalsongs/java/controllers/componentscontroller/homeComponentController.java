package emotionalsongs.java.controllers.componentscontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.PlaylistManager;
import emotionalsongs.java.Managers.StyleManager;
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

public class homeComponentController implements Initializable {
    @FXML
    private HBox UserPlaylistContainer;
    @FXML
    private HBox SongsContainer;
    @FXML
    private HBox notMyPlaylistContainer;

    @FXML
    private Button myPlayButt;

    @FXML
    private Button otherPlaylsitButton;

    @FXML
    private TableView<Canzone> Tabella;

    @FXML
    private TableColumn<Canzone, Void> songindex;

    @FXML
    private TableColumn<Canzone, String> album;

    @FXML
    private TableColumn<Canzone, Integer> anno;

    @FXML
    private TableColumn<Canzone, String> autore;

    @FXML
    private TableColumn<Canzone, Double> durata;

    @FXML
    private TableColumn<Canzone, Void> optionbutton;

    @FXML
    private TableColumn<Canzone, String> titolo;

    User u = GlobalsVariables.currentUser;

    private Label avviso = new Label();

    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();
    ArrayList<Playlist> mine = new ArrayList<Playlist>();
    ArrayList<Playlist> others = new ArrayList<Playlist>();
    ArrayList<Playlist> pl = PlaylistManager.readPlaylist();

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
                    Label avviso = new Label("Per usufruire di altre funzione bisogna effettuare il login");
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


    private void createHomePlaylistUI() throws IOException {
        UserPlaylistContainer.setPadding(new Insets(15));
        UserPlaylistContainer.setAlignment(Pos.CENTER_LEFT);
        UserPlaylistContainer.setSpacing(20);

        System.out.println("\n\nFunzia bene");
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

    // ***************************************** bene
    // *********************************************//
    private void createHomeSongUI() throws IOException {
        SongTableView table = new SongTableView(Tabella, album, songindex, anno, autore, durata, titolo);
        table.initializeFiltered(15);
        table.addButton(optionbutton);
    }
    // ********************************************************************************************//

    private void createHomeOthersPlaylistUI() throws IOException {
        notMyPlaylistContainer.setPadding(new Insets(15));
        notMyPlaylistContainer.setAlignment(Pos.CENTER_LEFT);
        notMyPlaylistContainer.setSpacing(20);
        System.out.println("\n\nFunzia bene");
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

    public void showSongs(ActionEvent e) throws IOException {
        System.out.println("funzia");
        Pane ui = obj.getPane("songs");
        GlobalsVariables.left_side_bpane.setCenter(ui);

    }

    public void showMyPlaylists(ActionEvent e) throws IOException {
        System.out.println("funzia");
        FXMLLoader load = obj.getComponentsLoader("playlist");
        playlistWindController playlistWindController = new playlistWindController();
        playlistWindController.setLemie(true);
        load.setController(playlistWindController);
        Parent ui = load.load();
        Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        GlobalsVariables.left_side_bpane.setCenter(ui);

    }

    public void showPlaylists(ActionEvent e) throws IOException {
        System.out.println("funzia");
        FXMLLoader load = obj.getComponentsLoader("playlist");
        playlistWindController playlistWindController = new playlistWindController();
        playlistWindController.setLemie(false);
        load.setController(playlistWindController);
        Parent ui = load.load();
        Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        GlobalsVariables.left_side_bpane.setCenter(ui);

    }

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