package emotionalsongs.java.controllers.componentscontroller;


import java.io.IOException;

import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.GlobalsVariables;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class createPlaylistController {

    @FXML
    private ImageView playlistImage;
    @FXML 
    private TextField PlayListName;
    @FXML 
    private TableView<Canzone> addPlaylistrepository;
    @FXML 
    private Button createPlaylist;


    StyleManager style = new StyleManager();


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
