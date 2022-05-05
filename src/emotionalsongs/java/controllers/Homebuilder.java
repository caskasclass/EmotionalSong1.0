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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Homebuilder implements Initializable {


    @FXML
    private BorderPane left_side_bpane;
    @FXML
    private Label hello_username;
    @FXML
    private Button btn_signOut;
    @FXML
    private Label add_song_btn;
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

    /*private void createHomePlaylistUI() {

        myPlaylistcont.setPadding(new Insets(15));
        myPlaylistcont.setAlignment(Pos.CENTER_LEFT);
        myPlaylistcont.setSpacing(20);

        System.out.println("\n\nFunzia bene");
        for (int i = 0; i < 4; i++) {
            Label lab = new Label();
            lab.setText("Playlist");
            lab.setFont(new Font("Arial", 40));
            lab.alignmentProperty().setValue(Pos.CENTER);
            lab.setMinWidth(225);
            lab.setMinHeight(225);
            lab.setBackground(Background.fill(Color.CORAL));
            lab.setBorder(Border.stroke(Color.BLANCHEDALMOND));
            myPlaylistcont.getChildren().addAll(lab);
        }

    }

    private void createHomeSongUI() {
        SongsContainer.setPadding(new Insets(5, 0, 5, 0));
        ArrayList<Canzone> songs = CanzoniManager.readCanzoni();
       
        for(Canzone canzone : songs) {
            HBox songrow = new HBox();
            songrow.setPadding(new Insets(3));
            songrow.setAlignment(Pos.CENTER);
            Label nomeCanzone = new Label(canzone.getTitolo());
            Label nomeAutore = new Label(canzone.getAutore());
            Label durata = new Label(canzone.getDurata().toString());
            Label genere = new Label(canzone.getGenere());
            Label nomeAlbum = new Label(canzone.getAlbum());
            songrow.getChildren().addAll(nomeCanzone,nomeAlbum,genere,nomeAutore,durata);
            songrow.setSpacing(15);
            SongsContainer.getChildren().add(songrow);
        }
        
    }
*/
    public void backHome(MouseEvent e) throws IOException{
        System.out.println("funzia");
        Pane ui= obj.getPane("home");
        left_side_bpane.setCenter(ui);
        
    }
}
