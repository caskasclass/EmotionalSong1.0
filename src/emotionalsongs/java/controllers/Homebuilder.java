/**
 * Provides the classes necessary to manage 
 * the three main Stages(windows) of the application  
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.controllers.componentscontroller.createPlaylistController;
import emotionalsongs.java.controllers.componentscontroller.playlistWindController;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller Class for the file HomeWindow.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class Homebuilder implements Initializable {

    /**fxml element for graphics */
    @FXML
    private HBox logbuttons;
    /**fxml element for graphics */
    @FXML
    private TextField usernameEmail;
    /**fxml element for graphics */
    @FXML
    private PasswordField passwd;
    /**fxml element for graphics */
    @FXML
    private BorderPane left_side_bpane;
    /**fxml element for graphics */
    @FXML
    private MenuButton usernameBtn;
    /**fxml element for graphics */
    @FXML
    private MenuButton LogInMenuButton;
    /**fxml element for graphics */
    @FXML
    private Label labelLeTuePlaylist;
    /**fxml element for graphics */
    @FXML
    private Label labelNuovaPlaylist;
    /**fxml element for graphics */
    @FXML
    private VBox menubar;
    
    @FXML
    private Label noUser;

    /**Useful object fxml file*/
    FxmlLoader obj = new FxmlLoader();

    /**Current User */
    private User logged = GlobalsVariables.currentUser;


    /**Initializes the fxml file*/
    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        Platform.runLater(() -> {
            
            menubar.getChildren().removeAll(labelLeTuePlaylist,labelNuovaPlaylist);
            GlobalsVariables.left_side_bpane = this.left_side_bpane;
            createHome();
            

        });

        if (logged == null) {
            logbuttons.getChildren().remove(usernameBtn);
        }

    }

    /**
     * Sets User to null and removes some fxml elements
     * @param e javafx action event
     * @throws IOException IOException
     */
    public void SignOut(ActionEvent e) throws IOException {
        GlobalsVariables.clearSession();
        GlobalsVariables.exitSession();
        logged = GlobalsVariables.currentUser;
        Parent p = (Parent) left_side_bpane.getCenter();
        left_side_bpane.getChildren().remove(p);
        createHome();
        menubar.getChildren().removeAll(labelLeTuePlaylist,labelNuovaPlaylist);
        logbuttons.getChildren().remove(usernameBtn);
        logbuttons.getChildren().add(LogInMenuButton);
        usernameEmail.setText("");
        passwd.setText("");

    }

    /**
     * Sets User to the user doing the login and adds some fxml elements
     * @param e javafx action event
     * @throws IOException IOException
     */
    public void Login(ActionEvent e) throws IOException {
        User loguser = new User(usernameEmail.getText(), passwd.getText(), usernameEmail.getText(),
                (usernameEmail.getText()).toLowerCase(), (usernameEmail.getText()).toUpperCase(),
                (usernameEmail.getText()).toLowerCase());
        ArrayList<User> users = (ArrayList<User>) UserManager.readUsers();
        if (users.contains(loguser)) {
            User log = users.get(users.indexOf(loguser)); // molto meglio
            GlobalsVariables.currentUser = log;
            noUser.setVisible(false);
            updateWindow();
        }
        else{
            usernameEmail.setText("");
            passwd.setText("");
            noUser.setVisible(true);
            noUser.setText("Utente non trovato");
        }
    }

    /**
     * Sets home window based on if the user is logged or not
     */
    private void updateWindow() {
        Parent p = (Parent) left_side_bpane.getCenter();
        left_side_bpane.getChildren().remove(p);
        logged = GlobalsVariables.currentUser;
        createHome();
        menubar.getChildren().addAll(labelLeTuePlaylist,labelNuovaPlaylist);
        logbuttons.getChildren().remove(LogInMenuButton);
        logbuttons.getChildren().add(usernameBtn);
        usernameBtn.setText(logged.getUsername());


    }

    /**
     * Sets the center of the BorderPane with the file home.fxml
     */
    private void createHome()
    {
        try {
            Parent p = obj.getPane("home");
            left_side_bpane.setCenter(p);
        } catch (Exception e) {
           e.printStackTrace();
        }
         
    }

    /**
     * Sets the center of the BorderPane with the file createPlaylist.fxml
     */
    private void createNewPlaylistUI()throws IOException{
        FXMLLoader load = obj.getComponentsLoader("createPlaylist");
        createPlaylistController createPlaylistController = new createPlaylistController();
        load.setController(createPlaylistController);
        Parent ui = load.load();
        Parent p = (Parent)GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        left_side_bpane.setCenter(ui);
    }

    /**
     * Opens a new window for the registration
     * @param e javafx action event
     * @throws IOException IOException
     */
    public void SignUpWind(ActionEvent e) throws IOException {
        String imgPath = getClass().getResource("/emotionalsongs/resources/images/icon3.png").toExternalForm();
        Parent root = FXMLLoader.load(getClass().getResource("/emotionalsongs/resources/view/SignUpWindow.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(GlobalsVariables.style.getStyle("registration"));
        stage.setTitle("EmotionalSong");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(imgPath));
        stage.show();
    }

    /**
     * Method that takes the user back to home after a certain action
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void backHome(MouseEvent e) throws IOException {
        GlobalsVariables.cleardeleteFromPlaylistSession();
        Parent p = (Parent) left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        createHome();
    }

    /**
     * Method that sets the center of the BorderPane with the graphic of the create playlist window
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void newPlaylist(MouseEvent e) throws IOException {
        createNewPlaylistUI();
        Parent p = (Parent) left_side_bpane.getCenter();
        left_side_bpane.getChildren().remove(p);
        createNewPlaylistUI();
    }

    /**
     * Method that sets the center of the BorderPane with the graphic of current user's playlists window
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void MyplayWind(MouseEvent e) throws IOException {
        
        FXMLLoader load = obj.getComponentsLoader("playlist");
        playlistWindController playlistWindController = new playlistWindController();
        playlistWindController.setLemie(true);
        load.setController(playlistWindController);
        Parent ui = load.load();
        Parent p = (Parent)GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        left_side_bpane.setCenter(ui);
    }

    /**
     * Method that sets the center of the BorderPane with the graphic of other users' playlists window
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void playWind(MouseEvent e) throws IOException {
        
        FXMLLoader load = obj.getComponentsLoader("playlist");
        playlistWindController playlistWindController = new playlistWindController();
        playlistWindController.setLemie(false);
        load.setController(playlistWindController);
        Parent ui = load.load();
        Parent p = (Parent)GlobalsVariables.left_side_bpane.getCenter();
        GlobalsVariables.left_side_bpane.getChildren().remove(p);
        left_side_bpane.setCenter(ui);
    }

    /**
     * Method that sets the center of the BorderPane with the graphic of the songs window
     * @param e javafx mouse event
     * @throws IOException IOException
     */
    public void songWind(MouseEvent e) throws IOException {
        
        Pane ui = obj.getPane("songs");
        ui.getStylesheets().add(GlobalsVariables.style.getStyle("repositoryCanzoni"));
        left_side_bpane.setCenter(ui);
    }

}
