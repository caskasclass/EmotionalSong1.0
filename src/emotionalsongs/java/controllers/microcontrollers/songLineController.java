package emotionalsongs.java.controllers.microcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import emotionalsongs.java.util.Canzone;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class songLineController implements Initializable {
    @FXML
    private MenuButton buttonContext;
    @FXML
    private GridPane rowLine;
    @FXML
    private Label lb_SongNumber;
    @FXML
    private Label lb_SongTitle;
    @FXML
    private Label lb_SongAutore;
    @FXML
    private Label lb_SongAlbum;
    @FXML
    private Label lb_SongGenere;
    @FXML
    private Label lb_SongAnno;
    @FXML
    private Label lb_SongDurata;

    private Canzone canzone;
    private Integer indx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            lb_SongNumber.setText(indx.toString());
            lb_SongTitle.setText(canzone.getTitolo());
            lb_SongAutore.setText(canzone.getAutore());
            lb_SongAlbum.setText(canzone.getAlbum());
            lb_SongGenere.setText("toglimi");
            lb_SongAnno.setText(canzone.getAnno());
            lb_SongDurata.setText(canzone.getDurata());
        });
    }

    public void setCanzone(Canzone c, Integer index) {
        canzone = c;
        indx = index;
    }

    public void addToPlaylistEvent(ActionEvent e) {
        System.out.println("Hai cliccato aggiungi alla playlist");
    }
    
    public void selectRow(MouseEvent e){
        VBox p = (VBox) rowLine.getParent();
        p.getChildren().forEach(node -> {
            node.getStyleClass().remove("nothover");
        });
        this.rowLine.getStyleClass().add("nothover");
        if(this.rowLine.getStyleClass().contains("nothover")){

            buttonContext.setVisible(true);
        }
        
        
    }
   
}
