package emotionalsongs.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.util.Canzone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class songWindController implements Initializable{

    @FXML
    private VBox uno;
    @FXML
    private VBox due;
    @FXML
    private VBox tre;
    @FXML
    private VBox quattro;
    @FXML
    private VBox cinque;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createHomeSongUI();
        
        
    }
    private void createHomeSongUI() {
        ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

        for (Canzone canzone : songs) {
            Label nomeCanzone = new Label(canzone.getTitolo());
            Label nomeAutore = new Label(canzone.getAutore());
            Label durata = new Label(canzone.getDurata().toString());
            Label genere = new Label(canzone.getGenere());
            Label nomeAlbum = new Label(canzone.getAlbum());
            uno.getChildren().add(nomeCanzone);
            due.getChildren().add(nomeAlbum);
            tre.getChildren().add(genere);
            quattro.getChildren().add(nomeAutore);
            cinque.getChildren().add(durata);
        }

    }
}
