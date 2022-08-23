package emotionalsongs.java.controllers.componentscontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.PlaylistManager;
import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.controllers.microcontrollers.AddPlaylistBoxController;
import emotionalsongs.java.controllers.microcontrollers.PlaylistBoxController;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.Playlist;
import emotionalsongs.java.util.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class playlistWindController implements Initializable {

    User u = null;
    private boolean lemie;
    ArrayList<Playlist> mine = new ArrayList<Playlist>();
    ArrayList<Playlist> pl = PlaylistManager.readPlaylist();
    ArrayList<Playlist> others = new ArrayList<Playlist>();
    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();

    @FXML
    private FlowPane Container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(u != null) {
                filterPlaylist();
                if (lemie) {
                    createHomePlaylistUI();
                } else {
                    createHomeOthersPlaylistUI();
                }
            } else {
                PlaylistUI();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setUser(User u) {
        this.u = u;
    }

    public void setLemie(boolean x) {
        lemie = x;
    }

    private void PlaylistUI() throws IOException {
        for (Playlist playlist : pl) {
            FXMLLoader loader = obj.getLoader("PlaylistBoxView");
            PlaylistBoxController playlistBoxController = new PlaylistBoxController();
            playlistBoxController.setPlaylist(playlist);
            loader.setController(playlistBoxController);
            Parent ui = loader.load();
            Container.getChildren().add(ui);

        }
    }

    private void createHomePlaylistUI() throws IOException {

        System.out.println("\n\nFunzia bene");
        Container.setMinSize(GlobalsVariables.left_side_bpane.getHeight(), 825);
        ArrayList<Playlist> playlists = mine;
        if (playlists == null || playlists.isEmpty()) {
            FXMLLoader loader = obj.getLoader("AddPlaylistBox");
            AddPlaylistBoxController addPlaylistBoxController = new AddPlaylistBoxController();
            addPlaylistBoxController.setUser(u);
            loader.setController(addPlaylistBoxController);
            Parent ui = loader.load();
            ui.getStylesheets().add(style.getStyle("PlaylistBox"));
            Container.getChildren().add(ui);

        } else {
            for (Playlist playlist : mine) {
                FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                playlistBoxController.setPlaylist(playlist);
                loader.setController(playlistBoxController);
                Parent ui = loader.load();
                Container.getChildren().add(ui);

            }
        }

    }

    private void createHomeOthersPlaylistUI() throws IOException {
        System.out.println("\n\nFunzia bene");
        ArrayList<Playlist> playlists = others;
        if (playlists == null || playlists.isEmpty()) {
            for (int i = 0; i < 6; i++) {
                BorderPane ui = (BorderPane) obj.getMicroPane("AddPlaylistBox");
                ui.getStylesheets().add(style.getStyle("PlaylistBox"));
                Container.getChildren().add(ui);
            }

        } else {
            for (Playlist playlist : playlists) {
                FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                playlistBoxController.setPlaylist(playlist);
                loader.setController(playlistBoxController);
                Parent ui = loader.load();
                Container.getChildren().add(ui);
            }
        }

    }

    private void filterPlaylist() {
        for (Playlist playlist : pl) {
            if (playlist.getOwner().equals(u.getId())) {
                mine.add(playlist);
            } else {
                others.add(playlist);
            }

        }

    }

}
