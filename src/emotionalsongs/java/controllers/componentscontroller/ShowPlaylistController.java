package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ResourceBundle;

import emotionalsongs.java.controllers.microcontrollers.PlaylistBoxController;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.Playlist;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ShowPlaylistController  implements Initializable{

   

    

    @FXML
    private Label PlayListName;

    @FXML
    private TableView<Canzone> addedSongs;

    @FXML
    private Label owner;

    @FXML
    private ImageView playlistImage;

    

    private Playlist current = null;

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        setPlaylist();
    }

    public void getPlaylist(Playlist p){
        current = p;
    }

    public void setPlaylist(){

        String path = getClass().getResource(("../../../resources/images/"+current.getPng())).toExternalForm();
        playlistImage.setImage(new Image(path)); 
        PlayListName.setText(current.getNomePlaylist());
        PlaylistBoxController playlistBoxController= new PlaylistBoxController();
        playlistBoxController.setPlaylist(current);
        owner.setText(playlistBoxController.getUsername(current.getOwner()));
        ObservableList<Canzone> list= FXCollections.observableArrayList(current.getCanzoni());
        addedSongs.setItems(list);
        
        
    }


    
}
