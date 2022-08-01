package emotionalsongs.java.controllers.microcontrollers;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.util.Playlist;
import emotionalsongs.java.util.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlaylistBoxController implements Initializable{

    @FXML
    private Label playListName;
    @FXML
    private Label usernameowner;
    @FXML
    private ImageView PlaylistImageTag;
    private Playlist playlist; 
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playListName.setText(playlist.getNomePlaylist());
        usernameowner.setText(getUsername(playlist.getOwner()));
        String path = getClass().getResource(("../../../resources/images/"+playlist.getPng())).toExternalForm();
        PlaylistImageTag.setImage(new Image(path));

    }
    public void setPlaylist(Playlist p)
    {
        playlist =p;
    }
    private String getUsername(String id){
        String username="";
        ArrayList<User> users = UserManager.readUsers();
        for (User user : users) {
            if(user.getId().equals(id)){
                username = user.getUsername();
                break;
            }
        }
        return username;

    }

    
}
