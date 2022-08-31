package emotionalsongs.java.controllers;

import emotionalsongs.java.util.GlobalsVariables;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller Class for the file PlaylistImagePicker.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class ImagePlaylistPicker {

    /**
     * Method that sets the selected picture as the icon of the new playlist
     * @param e javafx mouse event
     * @throws IOException IOException
     */
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
