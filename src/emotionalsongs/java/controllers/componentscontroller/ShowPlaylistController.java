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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShowPlaylistController  implements Initializable{

   

    @FXML
    private Button deleteButt;
    
    @FXML
    private Button saveButt;

    @FXML
    private Button editButt;

    @FXML
    private VBox addBox;

    @FXML
    private VBox allBox;

    @FXML
    private Label PlayListName;

    @FXML
    private TextField cerca;

    @FXML
    private TableColumn<Canzone, Void> addbutton;

    @FXML
    private TableView<Canzone> PlaylistSongs;
    
    @FXML
    private TableView<Canzone> AddSongs;

    @FXML
    private TableColumn<Canzone, Void> deletebutton1;

    @FXML
    private TableColumn<Canzone, Void> deletebutton;

    @FXML
    private TableColumn<Canzone, String> album;

    @FXML
    private TableColumn<Canzone, Integer> anno;

    @FXML
    private TableColumn<Canzone, String> autore;

    @FXML
    private TableColumn<Canzone, Double> durata;

    @FXML
    private TableColumn<Canzone, Void> index;

    @FXML
    private TableColumn<Canzone, String> titolo;

    @FXML
    private TableColumn<Canzone, String> album1;

    @FXML
    private TableColumn<Canzone, Integer> anno1;

    @FXML
    private TableColumn<Canzone, String> autore1;

    @FXML
    private TableColumn<Canzone, Double> durata1;

    @FXML
    private TableColumn<Canzone, Void> index1;

    @FXML
    private TableColumn<Canzone, String> titolo1;

    @FXML
    private HBox deleteButtContainer;

    @FXML
    private Label owner;

    @FXML
    private ImageView playlistImage;

    @FXML
    private TableView<?> tabprova;

    FxmlLoader obj = new FxmlLoader();

    private Playlist current = null;

    User u = GlobalsVariables.currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
            if(u == null || !(current.getOwner().equals(u.getId())))
            {
                deleteButtContainer.getChildren().removeAll(deleteButt,saveButt,editButt);
                allBox.getChildren().remove(addBox);

            }
            else{
                deleteButtContainer.getChildren().remove(saveButt);
                allBox.getChildren().remove(addBox);
            }
            setPlaylist();
            
            
            GlobalsVariables.playlist = PlaylistSongs.getItems();
            GlobalsVariables.plist = current;
            
        
        
        
    }

    public void getPlaylist(Playlist p){
        current = p;
    }

    private void setPlaylist(){

        String path = getClass().getResource(("../../../resources/images/"+current.getPng())).toExternalForm();
        playlistImage.setImage(new Image(path)); 
        PlayListName.setText(current.getNomePlaylist()); 
        owner.setText(getUsername());

        SongTableView table  = new SongTableView(PlaylistSongs, album, index, anno, autore, durata, titolo);
        ObservableList<Canzone> list= FXCollections.observableArrayList(current.getCanzoni());
        table.initializePlaylsitList(list);
        if(u == null || !(current.getOwner().equals(u.getId()))){
            table.addButton(deletebutton);
        }else{
            table.deleteFromPlaylist(deletebutton);
        }
     
        
        
    }


    public void deletePlaylist(ActionEvent e){
        ArrayList<Playlist> array = PlaylistManager.readPlaylist();
        // non trova la playlist 
        array.remove(current);
        PlaylistManager.getPlaylist(array);
        GlobalsVariables.cleardeleteFromPlaylistSessio();
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

    public void addSong(ActionEvent e){

        deleteButtContainer.getChildren().add(saveButt);
        allBox.getChildren().add(addBox);
        SongTableView table = new SongTableView(AddSongs, album1, index1, anno1, autore1, durata1, titolo1);
        AddSongs.setFixedCellSize(50);
        table.initialize();
        table.addToPlylistButton(addbutton);
        table.cercaBranoMusicale(cerca);


    }

    public void deleteAddBox(ActionEvent e){

        deleteButtContainer.getChildren().remove(saveButt);
        allBox.getChildren().remove(addBox);
    }

}
