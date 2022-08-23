package emotionalsongs.java.controllers;


import emotionalsongs.java.util.GlobalsVariables;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ImagePlaylistPicker {

    String UrlPath= "";

    public void selectPicture(MouseEvent e)
    {
        String path="";
        ImageView imgview=null;
        if(e.getSource() instanceof ImageView){
            imgview = (ImageView)e.getSource();
        }
        if(imgview!=null)
        {
           Image img= imgview.getImage();
           path = img.getUrl();

        }

        

        GlobalsVariables.PlaylistImg = path;
        Stage stage = (Stage) imgview.getScene().getWindow();
        stage.close();
    }

}
