package emotionalsongs.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.controllers.componentscontroller.createPlaylistController;
import emotionalsongs.java.controllers.componentscontroller.homeComponentController;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Homebuilder implements Initializable {

    @FXML
    private HBox logbuttons;

    @FXML
    private TextField usernameEmail;
    @FXML
    private PasswordField passwd;
    @FXML
    private HBox myPlaylistcont;
    @FXML
    private BorderPane left_side_bpane;
    @FXML
    private Label hello_username;
    @FXML
    private MenuItem btn_signOut;
    @FXML
    private Label add_song_btn;
    @FXML
    private MenuButton usernameBtn;
    @FXML
    private MenuButton LogInMenuButton;
    @FXML
    private Pane navBarPane;
    @FXML
    private Label labelLeTuePlaylist;
    @FXML
    private Label labelNuovaPlaylist;
    @FXML
    private VBox menubar;

    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();

    private User logged;

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

    /************************************************************************/
    /********** Servono per tornare alla scermata loggin *****************/

    public void SignOut(ActionEvent e) throws IOException {
        GlobalsVariables.clearSession();
        logged = null;
        Parent p = (Parent) left_side_bpane.getCenter();
        left_side_bpane.getChildren().remove(p);
        createHome();
        menubar.getChildren().removeAll(labelLeTuePlaylist,labelNuovaPlaylist);
        logbuttons.getChildren().remove(usernameBtn);
        logbuttons.getChildren().add(LogInMenuButton);

    }


    public void Login(ActionEvent e) throws IOException {
        User loguser = new User(usernameEmail.getText(), passwd.getText(), usernameEmail.getText(),
                (usernameEmail.getText()).toLowerCase(), (usernameEmail.getText()).toUpperCase(),
                (usernameEmail.getText()).toLowerCase());
        ArrayList<User> users = (ArrayList<User>) UserManager.readUsers();
        if (users.contains(loguser)) {

            User log = users.get(users.indexOf(loguser)); // molto meglio
            System.out.println(log.printUser());// testing ok!

            updateWindow(log);

            System.out.println("\n\n\nUser trovato\n\n\n");
        } else {
            System.out.println("\n\n\nUser non presente\n\n\n");
        }
    }

    private void updateWindow(User u) {
        Parent p = (Parent) left_side_bpane.getCenter();
        left_side_bpane.getChildren().remove(p);
        logged = u;
        createHome();
        menubar.getChildren().addAll(labelLeTuePlaylist,labelNuovaPlaylist);
        logbuttons.getChildren().remove(LogInMenuButton);
        logbuttons.getChildren().add(usernameBtn);
        usernameBtn.setText(u.getUsername());


    }
    private void createHome()
    {
        FXMLLoader loader = obj.getComponentsLoader("home");
        homeComponentController homeComponentController = new homeComponentController();
        homeComponentController.setUser(logged);
        loader.setController(homeComponentController);
        try {
            Parent p = loader.load();
            left_side_bpane.setCenter(p);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        
    }
    private void createNewPlaylistUI(){
        FXMLLoader loader = obj.getComponentsLoader("createPlaylist");
        createPlaylistController createPlaylistController = new createPlaylistController();
        createPlaylistController.setUser(logged);
        loader.setController(createPlaylistController);
        try {
            Parent p = loader.load();
            p.getStylesheets().add(style.getStyle("repositoryCanzoni"));
            left_side_bpane.setCenter(p);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void SignUpWind(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/view/SignUpWindow.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.getStyle("registration"));
        stage.setTitle("EmotionalSong");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /*****************************************************
     * metodi per i button della homeWindow
     *****************************************************/

    public void backHome(MouseEvent e) throws IOException {
        System.out.println("funzia");
        Parent p = (Parent) left_side_bpane.getCenter();
        left_side_bpane.getChildren().remove(p);
        createHome();
    }

    public void newPlaylist(MouseEvent e) throws IOException {
        System.out.println("funzia");
        Parent p = (Parent) left_side_bpane.getCenter();
        left_side_bpane.getChildren().remove(p);
        createNewPlaylistUI();
    }

    public void playWind(MouseEvent e) throws IOException {
        System.out.println("funzia");
        Pane ui = obj.getPane("playlist");
        left_side_bpane.setCenter(ui);
    }
    public void songWind(MouseEvent e) throws IOException {
        System.out.println("funzia");
        Pane ui = obj.getPane("songs");
        ui.getStylesheets().add(style.getStyle("repositoryCanzoni"));
        left_side_bpane.setCenter(ui);
    }

    /*****************************************************
     *****************************************************/

}
