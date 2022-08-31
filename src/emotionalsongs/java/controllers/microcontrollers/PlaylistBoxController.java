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

/**
 * Controller Class for the file PlaylistBoxView.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class PlaylistBoxController implements Initializable{

    /**fxml element for graphics */
    @FXML
    private Label playListName;
    /**fxml element for graphics */
    @FXML
    private Label usernameowner;
    /**fxml element for graphics */
    @FXML
    private ImageView PlaylistImageTag;
    /**Playlist object */
    private Playlist playlist; 
    /**Useful object to load fxml file*/
    private FxmlLoader obj= new FxmlLoader();

    /**Initializes the fxml file*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playListName.setText(playlist.getNomePlaylist());
        usernameowner.setText(getUsername(playlist.getOwner()));
        String path = getClass().getResource(("/emotionalsongs/resources/images/"+playlist.getPng())).toExternalForm();
        PlaylistImageTag.setImage(new Image(path));

    }

    /**
     * Sets current playlist
     * @param p Playlist object
     */
    public void setPlaylist(Playlist p)
    {
        playlist =p;
    }
    
    /**
     * Gets the owner of the playlist
     * @param id Id of the owner of the playlist
     * @return Id of the owner
     */
    public String getUsername(String id){
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

    /**
     * Method that sets the center of the BorderPane with the graphic of the playlist window
     * @throws IOException IOException
     */
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
