/**
 * Provides the controller classes necessary to 
 * manage the Scenes(components) of the main window.
 * @see package.emotionalsongs.java.controllers
 */
package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.PlaylistManager;
import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.Playlist;
import emotionalsongs.java.util.SongTableView;
import emotionalsongs.java.util.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller Class for the file ShowPlaylist.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class ShowPlaylistController  implements Initializable{

   /**fxml element for graphics */
    @FXML
    private Button deleteButt;
    /**fxml element for graphics */
    @FXML
    private Label PlayListName;
    /**fxml element for graphics */
    @FXML
    private TableView<Canzone> PlaylistSongs;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> deletebutton;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> album;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Integer> anno;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> autore;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Double> durata;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> index;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> titolo;
    /**fxml element for graphics */
    @FXML
    private HBox deleteButtContainer;
    /**fxml element for graphics */
    @FXML
    private Label owner;
    /**fxml element for graphics */
    @FXML
    private ImageView playlistImage;

    /**Sets the User to the current one */
    User u = GlobalsVariables.currentUser;
    /**Useful object to load fxml file*/
    FxmlLoader obj = new FxmlLoader();
    /**Instantiates a type Playlist object to null*/
    private Playlist current = null;

    /**Initializes the fxml file*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            
            if(u == null || !(current.getOwner().equals(u.getId())))
            {
                deleteButtContainer.getChildren().remove(deleteButt);
            }
            setPlaylist();
            
            GlobalsVariables.playlist = PlaylistSongs.getItems();
            GlobalsVariables.plist = current;
        });
        
        
    }

    /**
     * Sets the type Playlist object to the current one
     * @param p
     */
    public void getPlaylist(Playlist p){
        current = p;
    }

    /**Sets the graphic of the playlist */
    private void setPlaylist(){

        String path = getClass().getResource(("/emotionalsongs/resources/images/"+current.getPng())).toExternalForm();
        playlistImage.setImage(new Image(path)); 
        PlayListName.setText(current.getNomePlaylist()); 
        owner.setText(getUsername());

        SongTableView table  = new SongTableView(PlaylistSongs, album, index, anno, autore, durata, titolo);
        ObservableList<Canzone> list= FXCollections.observableArrayList(current.getCanzoni());
        table.initializePlaylistList(list);
        if(u == null || !(current.getOwner().equals(u.getId()))){
            table.addButton(deletebutton);
        }else{
            table.deleteFromPlaylist(deletebutton);
        }  
    }

    /**
     * Deletes the type Playlist object when a button is clicked 
     * @param e  javafx action event
     */
    public void deletePlaylist(ActionEvent e){
        ArrayList<Playlist> array = PlaylistManager.readPlaylist();
        array.remove(current);
        PlaylistManager.getPlaylist(array);
        GlobalsVariables.cleardeleteFromPlaylistSession();
        backToHome();
       
    }

    // lo dabbiamo fare globale
    public void backToHome(){
        try {
            Parent p = obj.getPane("home");
            GlobalsVariables.left_side_bpane.setCenter(p);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /**
     * Returns the username of the user who created the playlist
     * @return username of the user who created the playlist as String
     */
    private String getUsername()
    {
        String username="";
        ArrayList<User> users = UserManager.readUsers();
        for (User user : users) {
            if(user.getId().equals(current.getOwner())){
                username = user.getUsername();
                break;
            }
        }
        return username;
    }
}