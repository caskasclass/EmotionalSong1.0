package emotionalsongs.java.controllers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;



import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.util.FxmlLoader;

import emotionalsongs.java.util.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

public class Homebuilder implements Initializable {

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
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextField tf5;
    @FXML
    private TextField tf6;
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
            navBarPane = obj.getPane("userNavBar");
            navBarPane.getStylesheets().add(style.getStyle("UserNavBarCss"));
            left_side_bpane.setTop(navBarPane);
            //hello_username.setText("Ciao, " + logged.getUsername());


        });

    }
    /************************************************************************/
    /**********  Servono per tornare alla scermata loggin  *****************/

    public void SignOut(ActionEvent e) throws IOException {
        closewindow(usernameBtn);
        Logwindow();

    }

    private void Logwindow() throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../resources/view/LoginWindow.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(style.mainStyle());
        stage.setScene(scene);
        stage.show();
    }

    private void closewindow(Node n) {
        Stage stage = (Stage) n.getScene().getWindow(); // chiusura della finestra
        stage.close();
    }
    /************************************************************************/
    /************************************************************************/


    public void setUser(User u) {
        logged = u;
    }


 






    /*****************************************************
     * metodi per i button della homeWindow
     *****************************************************/

    public void backHome(MouseEvent e) throws IOException {
        System.out.println("funzia");
        Pane ui = obj.getPane("home");
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
