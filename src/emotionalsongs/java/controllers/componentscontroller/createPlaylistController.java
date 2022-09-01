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
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.Playlist;
import emotionalsongs.java.util.SongTableView;
import emotionalsongs.java.util.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

/**
 * Controller Class for the file createPlaylist.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class createPlaylistController implements Initializable {

    /**fxml element for graphics */
    @FXML
    private ImageView playlistImage;
    /**fxml element for graphics */
    @FXML
    private TextField PlayListName;
    /**fxml element for graphics */ 
    @FXML
    private TextField cerca;
    /**fxml element for graphics */
    @FXML
    private TableView<Canzone> addPlaylistrepository;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> addbutton;
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
    private TableColumn<Canzone, Void> songindex;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> titolo;
    /**fxml element for graphics */
    @FXML
    private TableView<Canzone> addedSongs;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> optionbutton;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> album1;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Integer> anno1;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> autore1;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> tit;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Double> durata1;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> index;
    /**fxml element for graphics */
    @FXML
    private Label owner;

    /**Sets the User to the current one */
    User u = GlobalsVariables.currentUser;

    /**Useful object to load fxml file*/
    FxmlLoader obj = new FxmlLoader();

    /**Initializes the fxml file*/
    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        
        owner.setText(u.getUsername());
        GlobalsVariables.addedSongs=  this.addedSongs;
        if(!GlobalsVariables.PlaylistImg.equals(""))
        {
            playlistImage.setImage(new Image(GlobalsVariables.PlaylistImg));
        }

        if(GlobalsVariables.PlaylistName.equals("")){
            PlayListName.setText("La Mia Playlist");
        }else{
            PlayListName.setText(GlobalsVariables.PlaylistName); 
        }
        SongTableView addedTable = new SongTableView(addedSongs, album1, index, anno1, autore1, durata1, tit);
        addedTable.initializeAdded();
        addedTable.addButton(optionbutton);
        
        SongTableView table = new SongTableView(addPlaylistrepository, album, songindex, anno, autore, durata, titolo);
        addPlaylistrepository.setFixedCellSize(50);
        table.initialize();
        table.addToPlylistButton(addbutton);
        table.cercaBranoMusicale(cerca);

    }

    /**
     * Method that opens the window in which u can select the icon for the new playlist
     * @param e javafx mouse event
     * @throws IOException Exception
     */
    public void changeImage(MouseEvent e) throws IOException {
        String imgPath = getClass().getResource("/emotionalsongs/resources/images/icon3.png").toExternalForm();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/emotionalsongs/resources/view/PlaylistImagePicker.fxml"));
        Scene scene = new Scene(root, 1100, 600);
        scene.getStylesheets().add(GlobalsVariables.style.getStyle("imagePickerWindow"));
        stage.setTitle("EmotionalSongs");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(imgPath));
        stage.showAndWait();

        String s = GlobalsVariables.PlaylistImg;

        Image img = new Image(s);

        playlistImage.setImage(img);


    }

    /**
     * After entering the key moves the focus to another fxml alement
     * @param key Enter key
     */
    public void NoFocus(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            GlobalsVariables.PlaylistName = this.PlayListName.getText();
            this.addPlaylistrepository.requestFocus();
        }
    }

    /**
     * Method that creates the playlist when the button is clicked
     * @param e javafx action event
     * @throws IOException Exception
     */
    public void RegistraPlaylist(ActionEvent e){

        String path = pathformatter(playlistImage.getImage().getUrl()) ;
        String playlistname = PlayListName.getText();
        String userId = u.getId();
        ArrayList<Canzone> arr = createSongList();
        Playlist pl = new Playlist(playlistname, path, userId, arr);
        ArrayList<Playlist> array = PlaylistManager.readPlaylist();
        array.add(pl);
        PlaylistManager.getPlaylist(array);
        GlobalsVariables.clearSession();
        Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        backToHome();

    }

    /**
     * Method that takes the user back to home after a certain action
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void backToHome()
    {
         try {
            Parent p = obj.getPane("home");
            GlobalsVariables.left_side_bpane.setCenter(p);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }

    /**
     * Method that creates the list of the songs of the new playlist
     * @return ArrayList of type Canzone objects
     */
    private ArrayList<Canzone> createSongList()
    {
        ArrayList<Canzone> arr = new ArrayList<Canzone>();

        for (Canzone c : GlobalsVariables.canzoni) {
            arr.add(c);
        }

        return arr;
    }

    /**
     * Returns the path formatted of the image of the new playlist
     * @param path original path of the image
     * @return path formatted as String
     */
    private  String pathformatter(String path){
        String pathFormatted="";
        Integer indx = path.indexOf(("images/"));
        pathFormatted = path.substring(indx+7);

        return pathFormatted;

    }
}
