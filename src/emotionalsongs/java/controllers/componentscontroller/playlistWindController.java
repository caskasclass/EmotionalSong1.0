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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class playlistWindController implements Initializable {

    User u = GlobalsVariables.currentUser;
    private boolean lemie;
    ArrayList<Playlist> mine = new ArrayList<Playlist>();
    ArrayList<Playlist> pl = PlaylistManager.readPlaylist();
    ArrayList<Playlist> others = new ArrayList<Playlist>();
    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();

    @FXML
    private FlowPane Container;

    private Label avviso = new Label();

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


    public void setLemie(boolean x) {
        lemie = x;
    }

    private void PlaylistUI() throws IOException {
        if (pl.isEmpty() || pl == null) {
            Container.setAlignment(Pos.CENTER);
            avviso.setText("Nessuna Playlist presente");
            avviso.setFont(new Font("Proxima Nova", 25));
            Container.getChildren().add(avviso);

        }else{
            for (Playlist playlist : pl) {
                FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                playlistBoxController.setPlaylist(playlist);
                loader.setController(playlistBoxController);
                Parent ui = loader.load();
                Container.getChildren().add(ui);

            }
        }
        
    }

    private void createHomePlaylistUI() throws IOException {

        System.out.println("\n\nFunzia bene");
        ArrayList<Playlist> playlists = mine;
        if (playlists == null || playlists.isEmpty()) {
            FXMLLoader loader = obj.getLoader("AddPlaylistBox");
            AddPlaylistBoxController addPlaylistBoxController = new AddPlaylistBoxController();
            loader.setController(addPlaylistBoxController);
            Parent ui = loader.load();
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
        if (pl.isEmpty() || pl == null) {
                Container.setAlignment(Pos.CENTER);
                avviso.setText("Nessuna Playlist presente");
                avviso.setFont(new Font("Proxima Nova", 25));
                Container.getChildren().add(avviso);

        } else{
            if (u != null) {
                if (others.isEmpty() || others == null) {
                    Container.setAlignment(Pos.CENTER);
                    avviso = new Label("Nessuna Playlist presente");
                    avviso.setFont(new Font("Proxima Nova", 25));
                    Container.getChildren().add(avviso);

                } else {
                    for (Playlist playlist : others) {
                        FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                        PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                        playlistBoxController.setPlaylist(playlist);
                        loader.setController(playlistBoxController);
                        Parent ui = loader.load();
                        Container.getChildren().add(ui);
                    }
                }
            } else {
                for (Playlist playlist : pl) {
                    FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                    PlaylistBoxController playlistBoxController = new PlaylistBoxController();
                    playlistBoxController.setPlaylist(playlist);
                    loader.setController(playlistBoxController);
                    Parent ui = loader.load();
                    Container.getChildren().add(ui);
                }
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
