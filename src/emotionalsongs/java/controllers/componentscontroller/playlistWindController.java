/**
 * Provides the controller classes necessary to 
 * manage the Scenes(components) of the main window.
 * @see package.emotionalsongs.java.controllers
 */
package emotionalsongs.java.controllers.componentscontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.PlaylistManager;
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

/**
 * Controller Class for the file playlist.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class playlistWindController implements Initializable {

    /**fxml element for graphics */
    @FXML
    private FlowPane Container;
    /**fxml element for graphics */
    private Label avviso = new Label();

    /**Sets the User to the current one */
    User u = GlobalsVariables.currentUser;
    /**Useful boolean variable */
    private boolean lemie;
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

    /**
     * Sets the boolean field to true if current user's playlists are considered, if not to false
     * @param x true if current user's playlists are considered, if not to false
     */
    public void setLemie(boolean x) {
        lemie = x;
    }

    /**
     * Method that sets the center of the BorderPane with the graphic of all the playlists in the application
     * @throws IOException IOException
     */
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

    /**
     * Method that sets the center of the BorderPane with the graphic of current user's playlists
     * @throws IOException IOException
     */
    private void createHomePlaylistUI() throws IOException {

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

    /**
     * Method that sets the center of the BorderPane with the graphic of other users' playlists
     * @throws IOException IOException
     */
    private void createHomeOthersPlaylistUI() throws IOException {
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

    /**Filters the list of playlists that are in the Playlist.data.txt file based on the user Id. */
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
