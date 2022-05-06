package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ResourceBundle;

import emotionalsongs.java.util.Playlist;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlaylistBoxController implements Initializable{

    @FXML
    private Label playListName;
    @FXML
    private Label usernameowner;
    @FXML
    private ImageView PlaylistImageTag;
    private Playlist playlist; // dopo la uso 
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> { 



        });
    }
    public void setPlaylist(Playlist p)
    {
        playlist =p;
    }
    
}
