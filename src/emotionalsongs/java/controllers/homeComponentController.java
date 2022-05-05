package emotionalsongs.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.util.Canzone;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class homeComponentController implements Initializable {
    @FXML
    private HBox UserPlaylistContainer;
    
    @FXML
    private VBox SongsContainer;


    @Override
    public void initialize(URL urilink, ResourceBundle reb) {

        Platform.runLater(() -> {
            createHomePlaylistUI();
            createHomeSongUI();

        });

    }

    private void createHomePlaylistUI() {
        UserPlaylistContainer.setPadding(new Insets(15));
        UserPlaylistContainer.setAlignment(Pos.CENTER_LEFT);
        UserPlaylistContainer.setSpacing(20);

        System.out.println("\n\nFunzia bene");
        for (int i = 0; i < 2; i++) {
            Label lab = new Label();
            lab.setText("Playlist");
            lab.setFont(new Font("Arial", 40));
            lab.alignmentProperty().setValue(Pos.CENTER);
            lab.setMinWidth(225);
            lab.setMinHeight(225);
            lab.setBackground(Background.fill(Color.CORAL));
            lab.setBorder(Border.stroke(Color.BLANCHEDALMOND));
            UserPlaylistContainer.getChildren().addAll(lab);
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
}
