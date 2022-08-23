package emotionalsongs.java.controllers.microcontrollers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.controllers.componentscontroller.ShowPlaylistController;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.Playlist;
import emotionalsongs.java.util.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private FxmlLoader obj= new FxmlLoader();

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

    public void ShowPlaylist() throws IOException
    {
       FXMLLoader load = obj.getComponentsLoader("ShowPlaylist");
       ShowPlaylistController showPlaylistController = new ShowPlaylistController();
       showPlaylistController.getPlaylist(playlist);
       load.setController(showPlaylistController);
       Parent ui = load.load();
       Parent currentWind =(Parent) GlobalsVariables.left_side_bpane.getCenter();
       GlobalsVariables.left_side_bpane.getChildren().remove(currentWind);
       GlobalsVariables.left_side_bpane.setCenter(ui);


       

       
    }

    
}
