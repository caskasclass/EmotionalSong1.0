package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ResourceBundle;

import emotionalsongs.java.util.Playlist;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ShowPlaylistController  implements Initializable{


    @FXML
    private HBox hb;


    private Playlist current = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() ->{
            Label l = new Label(current.getNomePlaylist());
            hb.getChildren().add(l);
        });

    }

    public void getPlaylist(Playlist p){
        current = p;
    }
    
}
