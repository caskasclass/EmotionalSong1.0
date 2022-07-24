package emotionalsongs.java.controllers.componentscontroller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.SongTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class createPlaylistController implements Initializable{

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
    private TableColumn<Canzone, Void> addbuttun;

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
    private Callback songs;

    private final ObservableList<Canzone> list = FXCollections.observableArrayList(songs);


    StyleManager style = new StyleManager();

    @Override
    public void initialize(URL urilink, ResourceBundle reb){
        SongTableView table = new SongTableView(addPlaylistrepository, album, songindex, anno, autore, durata, titolo);
        addPlaylistrepository.setFixedCellSize(50);
        table.initializeFiltered(10);
        table.cercaBranoMusicale(cerca,list.size());
        

    }
    public void changeImage(MouseEvent e)throws IOException{
        Stage stage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../../resources/view/PlaylistImagePicker.fxml"));
        Scene scene = new Scene(root,1100,600);
        scene.getStylesheets().add(style.getStyle("imagePickerWindow"));
        stage.setTitle("EmotioanlSongs");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();

        String s  = GlobalsVariables.ImageWindowPicker;
        Image img = new Image(s);

        playlistImage.setImage(img);
        GlobalsVariables.ImageWindowPicker = "";

    }
    public void NoFocus(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.addPlaylistrepository.requestFocus();
        }
    }


    
}
