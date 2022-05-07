package emotionalsongs.java.controllers.microcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import emotionalsongs.java.util.Canzone;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class songLineController implements Initializable {
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
            lb_SongGenere.setText(canzone.getGenere());
            lb_SongAnno.setText(canzone.getAnno().toString());
            lb_SongDurata.setText(canzone.getDurata().toString());
        });       
    }
    
    public void setCanzone(Canzone c,Integer index)
    {
        canzone = c ;
        indx = index;
    }
    

}
