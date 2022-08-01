package emotionalsongs.java.controllers.microcontrollers;

import java.io.IOException;


import emotionalsongs.java.controllers.componentscontroller.createPlaylistController;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class AddPlaylistBoxController {

    FxmlLoader obj = new FxmlLoader();
    User u = null;

    public void CreatePlaylistMethod(MouseEvent e) throws IOException{
        Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        FXMLLoader loader = obj.getComponentsLoader("createPlaylist");
        createPlaylistController createPlaylistController = new createPlaylistController();
        createPlaylistController.setUser(u);
        loader.setController(createPlaylistController);
        Parent ui = loader.load();
        GlobalsVariables.left_side_bpane.setCenter(ui);
    }

    public void setUser(User u)
    {
        this.u = u;
    }
}
