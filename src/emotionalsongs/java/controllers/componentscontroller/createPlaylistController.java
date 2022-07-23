package emotionalsongs.java.controllers.componentscontroller;

import java.io.IOError;
import java.io.IOException;

import emotionalsongs.java.Managers.StyleManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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


    StyleManager style = new StyleManager();


    public void changeImage(MouseEvent e)throws IOException{
        Stage stage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../../../resources/view/PlaylistImagePicker.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.getStyle("imagePickerWindow"));
        stage.setTitle("EmotioanlSongs");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void NoFocus(KeyEvent key)
    {
        System.out.println("ok");
    }


    
}
