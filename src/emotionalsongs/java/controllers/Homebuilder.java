package emotionalsongs.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.CanzoniManager;

import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.util.Canzone;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class Homebuilder implements Initializable {

    @FXML
    private HBox myPlaylistcont;
    @FXML
    private BorderPane left_side_bpane;
    @FXML
    private Label hello_username;
    @FXML
    private Button btn_signOut;
    @FXML
    private Label add_song_btn;
    @FXML
    private TextField songTitle_pl;
    @FXML
    private TextField songTitle_pl1;

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

    StyleManager style = new StyleManager();
    FxmlLoader obj = new FxmlLoader();

    private User logged;

    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        Platform.runLater(() -> {

            homePane = obj.getPane("home");
            left_side_bpane.setCenter(homePane);

            //createHomeSongUI();

            hello_username.setText("Ciao, " + logged.getUsername());

        });

    }

    public void SignOut(ActionEvent e) throws IOException {
        closewindow(btn_signOut);
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

    public void setUser(User u) {
        logged = u;
    }

    public void add_canzone_UI(MouseEvent e) {
        // myPlaylistcont.getChildren().clear(); //funziona
        left_side_bpane.getChildren().remove(homePane);
        VBox hbox = new VBox();
        Label lb1 = new Label("Titolo");
        tf1 = new TextField();
        tf1.setMinWidth(100);
        Label lb2 = new Label("Autore");
        tf2 = new TextField();
        Label lb3 = new Label("Anno");
        tf3 = new TextField();
        Label lb4 = new Label("Durata");
        tf4 = new TextField();
        Label lb5 = new Label("Album");
        tf5 = new TextField();
        Label lb6 = new Label("Genere");
        tf6 = new TextField();
        tf1.setMaxWidth(100);
        tf2.setMaxWidth(100);
        tf3.setMaxWidth(100);
        tf4.setMaxWidth(100);
        tf5.setMaxWidth(100);
        tf6.setMaxWidth(100);
        Button submit = new Button("Aggiungi");
        submit.setOnAction(event -> {
            submit();
        });
        hbox.getChildren().addAll(lb1, tf1, lb2, tf2, lb3, tf3, lb4, tf4, lb5, tf5, lb6, tf6, submit);
        left_side_bpane.setCenter(hbox);

    }

    public void submit() {
        String Titolo = tf1.getText();
        String Autore = tf2.getText();
        Integer Anno = Integer.parseInt(tf3.getText());
        double Durata = Double.parseDouble(tf4.getText());
        String Album = tf5.getText();
        String Genere = tf6.getText();
        Canzone canzone = new Canzone(Titolo, Autore, Anno, Durata, Album, Genere);
        ArrayList<Canzone> canzoni = CanzoniManager.readCanzoni();
        canzoni.add(canzone);
        CanzoniManager.getCanzoni(canzoni);
        System.out.println("Aggiunt0!!!");

    }


 

    public void backHome(MouseEvent e) throws IOException{
        System.out.println("funzia");
        Pane ui= obj.getPane("home");
        left_side_bpane.setCenter(ui);
    }

    public void playWind(MouseEvent e) throws IOException {
        System.out.println("funzia");
        obj = new FxmlLoader();
        Pane ui = obj.getPane("playlist");
        left_side_bpane.setCenter(ui);
    }

    public void songWind(MouseEvent e) throws IOException {
        System.out.println("funzia");
        Pane ui = obj.getPane("songs");
        left_side_bpane.setCenter(ui);
    }

    /*
     * public void songWindTest(MouseEvent e) throws IOException{
     * System.out.println("funzia");
     * FxmlLoader obj = new FxmlLoader();
     * Pane ui= obj.getPane("songsTopBar");
     * left_side_bpane.setCenter(ui);
     * BorderPane.setAlignment(ui, Pos.TOP_CENTER);
     * 
     * 
     * 
     * 
     * }
     */

    /*
     * public void addsongPLaylist(ActionEvent e){
     * 
     * ArrayList<Canzone> songs = (ArrayList<Canzone>) CanzoniManager.readCanzoni();
     * for (Canzone s : songs) {
     * if(s.getTitolo() == songTitle_pl.getText()){
     * 
     * Playlist pl= new Playlist(songTitle_pl1.getText(), logged.getUsername(),
     * s.getIdCanzone());
     * for (String si : pl.getCanzoni()) {
     * System.out.print(si);
     * }
     * 
     * }
     * }
     * 
     * }
     */
}
