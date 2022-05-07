package emotionalsongs.java.controllers.componentscontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.Managers.PlaylistManager;
import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.controllers.microcontrollers.PlaylistBoxController;
import emotionalsongs.java.controllers.microcontrollers.SongLineController;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.Playlist;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class homeComponentController implements Initializable {
    @FXML
    private HBox UserPlaylistContainer;
    @FXML
    private VBox SongsContainer;
    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();

    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        Platform.runLater(() -> {
            try {
                createHomePlaylistUI();
                createHomeSongUI();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    private void createHomePlaylistUI() throws IOException {
        UserPlaylistContainer.setPadding(new Insets(15));
        UserPlaylistContainer.setAlignment(Pos.CENTER_LEFT);
        UserPlaylistContainer.setSpacing(20);

        System.out.println("\n\nFunzia bene");
        ArrayList<Playlist> playlists = PlaylistManager.readPlaylist();
        if (playlists == null || playlists.isEmpty()) {
            for (int i = 0; i < 6; i++) {
                BorderPane ui = (BorderPane) obj.getMicroPane("AddPlaylistBox");
                ui.getStylesheets().add(style.getStyle("PlaylistBox"));
                UserPlaylistContainer.getChildren().add(ui);
            }

        } else {
            for (Playlist playlist : playlists) {
                FXMLLoader loader = obj.getLoader("PlaylistBoxView");
                Pane ui = loader.load();
                PlaylistBoxController controller = loader.<PlaylistBoxController>getController();
                controller.setPlaylist(playlist);
                UserPlaylistContainer.getChildren().add(ui);
            }
        }

    }

    private void createHomeSongUI() throws IOException {
        int i = 1;
        SongsContainer.setPadding(new Insets(30, 0, 30, 30));
        SongsContainer.setSpacing(30);

        ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

        for (Canzone canzone : songs) {
            FXMLLoader loader = obj.getLoader("SongLine");
            Pane ui = (Pane) loader.load();
            SongLineController controller = loader.<SongLineController>getController();
            controller.setCanzone(canzone, i);
            SongsContainer.getChildren().add(ui);
            i++;

        }
    }

    public void showSongs(ActionEvent e) throws IOException {
        System.out.println("funzia");
        Pane ui = obj.getPane("songs");
        BorderPane p = (BorderPane) getParent("left_side_bpane", SongsContainer);
        p.setCenter(ui);

    }

    // porco due quesat funzione funziona
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
}
