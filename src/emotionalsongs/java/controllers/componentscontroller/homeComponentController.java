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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class homeComponentController implements Initializable {
    @FXML
    private HBox UserPlaylistContainer;
    @FXML
    private HBox SongsContainer;
    @FXML
    private FlowPane notMyPlaylistContainer;

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

    User u = null;

    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();
    ArrayList<Playlist> mine = new ArrayList<Playlist>();
    ArrayList<Playlist> others = new ArrayList<Playlist>();

    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        Platform.runLater(() -> {
            if (u != null) {
                filterPlaylist();
            }

            try {
                if (u == null) {
                    Label avviso = new Label("Per usufruire di altre funzione bisogna effetuare il login");
                    avviso.setFont(new Font("Proxima Nova", 25));
                    UserPlaylistContainer.getChildren().add(avviso);

                } else {
                    createHomePlaylistUI();
                }
                createHomeSongUI();
                createHomeOthersPlaylistUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void setUser(User u) {
        this.u = u;
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
            addPlaylistBoxController.setUser(u);
            loader.setController(addPlaylistBoxController);
            Parent ui = loader.load();
            ui.getStylesheets().add(style.getStyle("PlaylistBox"));
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
        System.out.println("\n\nFunzia bene");
        ArrayList<Playlist> playlists = others;
        if (playlists == null || playlists.isEmpty()) {
            for (int i = 0; i < 6; i++) {
                BorderPane ui = (BorderPane) obj.getMicroPane("AddPlaylistBox");
                ui.getStylesheets().add(style.getStyle("PlaylistBox"));
                notMyPlaylistContainer.getChildren().add(ui);
            }

        } else {
            for (Playlist playlist : playlists) {
                FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                playlistBoxController.setPlaylist(playlist);
                loader.setController(playlistBoxController);
                Parent ui = loader.load();
                notMyPlaylistContainer.getChildren().add(ui);
            }
        }

    }

    public void showSongs(ActionEvent e) throws IOException {
        System.out.println("funzia");
        Pane ui = obj.getPane("songs");
        BorderPane p = (BorderPane) getParent("left_side_bpane", SongsContainer);
        p.setCenter(ui);

    }

    // porco due quesat funzione funziona // ora a rivedere la funzia ... che merda,
    // pero funziona
    // da cambiare !!! Ora che ce la variabile Gloable risalire a left_side_bpane è
    // una cabbata
    private Parent getParent(String target_Id, Node n) {
        Parent p;
        p = n.getParent();
        do {
            p = p.getParent();
            System.out.print(p.getId());
            if (p.getId() == null) {
                System.out.print("null");
                continue;
            }
        } while (p.getId() == null || !p.getId().equals(target_Id));
        return p;
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