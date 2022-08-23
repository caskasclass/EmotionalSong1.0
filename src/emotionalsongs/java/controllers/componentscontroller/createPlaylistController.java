package emotionalsongs.java.controllers.componentscontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.PlaylistManager;
import emotionalsongs.java.Managers.StyleManager;
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
import javafx.scene.control.Button;
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

public class createPlaylistController implements Initializable {

    @FXML
    private ImageView playlistImage;
    @FXML
    private TextField PlayListName;

    @FXML
    private TextField cerca;
    @FXML
    private TableView<Canzone> addPlaylistrepository;
    @FXML
    private Button createPlaylist;

    @FXML
    private TableColumn<Canzone, Void> addbutton;

    @FXML
    private TableColumn<Canzone, String> album;

    @FXML
    private TableColumn<Canzone, Integer> anno;

    @FXML
    private TableColumn<Canzone, String> autore;

    @FXML
    private TableColumn<Canzone, Double> durata;

    @FXML
    private TableColumn<Canzone, Void> songindex;

    @FXML
    private TableColumn<Canzone, String> titolo;

    //seconda tabella 
    @FXML
    private TableView<Canzone> addedSongs;
    @FXML
    private TableColumn<Canzone, Void> optionbutton;
    @FXML
    private TableColumn<Canzone, String> album1;

    @FXML
    private TableColumn<Canzone, Integer> anno1;

    @FXML
    private TableColumn<Canzone, String> autore1;
    @FXML
    private TableColumn<Canzone, String> tit;

    @FXML
    private TableColumn<Canzone, Double> durata1;

    @FXML
    private TableColumn<Canzone, Void> index;

    @FXML
    private Label owner;

    User u = null;
    ArrayList<Canzone> aggiungere = new ArrayList<Canzone>();
    FxmlLoader obj = new FxmlLoader();
    StyleManager style = new StyleManager();

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

    public void changeImage(MouseEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../../resources/view/PlaylistImagePicker.fxml"));
        Scene scene = new Scene(root, 1100, 600);
        scene.getStylesheets().add(style.getStyle("imagePickerWindow"));
        stage.setTitle("EmotioanlSongs");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();

        String s = GlobalsVariables.PlaylistImg;

        Image img = new Image(s);

        playlistImage.setImage(img);


    }

    public void NoFocus(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            GlobalsVariables.PlaylistName = this.PlayListName.getText();
            this.addPlaylistrepository.requestFocus();
        }
    }

    public void setUser(User u){
        this.u =u;
    }

    public void createPlaylist(ActionEvent e){

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
    private void backToHome()
    {
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
    private ArrayList<Canzone> createSongList()
    {
        ArrayList<Canzone> arr = new ArrayList<Canzone>();

        for (Canzone c : GlobalsVariables.canzoni) {
            arr.add(c);
        }

        return arr;
    }
    private  String pathformatter(String path){
        String pathFormatted="";
        Integer indx = path.indexOf(("images/"));
        pathFormatted = path.substring(indx+7);
        System.out.println(pathFormatted);

        return pathFormatted;

    }
}
