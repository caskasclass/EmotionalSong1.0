package emotionalsongs.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.util.FxmlLoader;

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
    private Pane homePane;
    @FXML 
    private Pane navBarPane;


    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();


    private User logged;

    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        Platform.runLater(() -> {

            homePane = obj.getPane("home");
            left_side_bpane.setCenter(homePane);
    
        });

        if(logged==null)
        {
            logbuttons.getChildren().remove(usernameBtn);      
        }

    }
    /************************************************************************/
    /**********  Servono per tornare alla scermata loggin  *****************/

    public void SignOut(ActionEvent e) throws IOException {
        logged = null;
        logbuttons.getChildren().remove(usernameBtn);
        logbuttons.getChildren().add(LogInMenuButton);

    }

    public void setUser(User u) {
        logged = u;
    }


    public void Login(ActionEvent e) throws IOException {
        User loguser = new User(usernameEmail.getText(), passwd.getText(), usernameEmail.getText(), (usernameEmail.getText()).toLowerCase(), (usernameEmail.getText()).toUpperCase(), (usernameEmail.getText()).toLowerCase());
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

    private void updateWindow(User u)
    {
        logged = u;
        logbuttons.getChildren().remove(LogInMenuButton);
        logbuttons.getChildren().add(usernameBtn);
        usernameBtn.setText(u.getUsername());
        
    }


    public void SignUpWind(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/view/SignUpWindow.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.mainStyle());
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
        Pane ui = obj.getPane("home");
        left_side_bpane.setCenter(ui);
    }

    public void newPlaylist(MouseEvent e)throws IOException{
        System.out.println("funzia");
        Pane ui = obj.getPane("createPlaylist");
        ui.getStylesheets().add(style.getStyle("repositoryCanzoni"));
        left_side_bpane.setCenter(ui);
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
