
package emotionalsongs.java.controllers.microcontrollers;

import java.io.IOException;


import emotionalsongs.java.controllers.componentscontroller.createPlaylistController;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * Controller Class for the file AddPlaylistBox.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class AddPlaylistBoxController {

    /**Useful object to load fxml file*/
    FxmlLoader obj = new FxmlLoader();

    /**
     * Method that sets the center of the BorderPane with the graphic of the create playlist window
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void CreatePlaylistMethod(MouseEvent e) throws IOException{
        Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        FXMLLoader loader = obj.getComponentsLoader("createPlaylist");
        createPlaylistController createPlaylistController = new createPlaylistController();
        loader.setController(createPlaylistController);
        Parent ui = loader.load();
        GlobalsVariables.left_side_bpane.setCenter(ui);
    }

}
