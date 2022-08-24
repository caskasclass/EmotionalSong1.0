package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.PlaylistManager;
import emotionalsongs.java.controllers.microcontrollers.PlaylistBoxController;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.Playlist;
import emotionalsongs.java.util.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ShowPlaylistController  implements Initializable{

   

    @FXML
    private Button deleteButt;

    @FXML
    private Label PlayListName;

    @FXML
    private TableView<Canzone> PlaylistSongs;

    @FXML
    private Label owner;

    @FXML
    private ImageView playlistImage;

    FxmlLoader obj = new FxmlLoader();

    private Playlist current = null;

    User u = null;

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
        PlaylistSongs.setItems(list);
        
        
    }

    public void setUser(User u){
        this.u =u;
    }

    public void deletePlaylist(ActionEvent e){
        ArrayList<Playlist> array = PlaylistManager.readPlaylist();
        array.remove(current);
        PlaylistManager.getPlaylist(array);
        backToHome();
       
    }

    public void backToHome(){
        FXMLLoader loader = obj.getComponentsLoader("home");
        homeComponentController homeComponentController = new homeComponentController();
        homeComponentController.setUser(u);
        loader.setController(homeComponentController);
        try {
            Parent p = loader.load();
            GlobalsVariables.left_side_bpane.setCenter(p);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
