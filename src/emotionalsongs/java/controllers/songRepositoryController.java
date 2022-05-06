package emotionalsongs.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.util.Canzone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class songRepositoryController implements Initializable{

    @FXML
    private TableView<Canzone> repository;
    
    @FXML
    private TableColumn<Canzone, String> album;

    @FXML
    private TableColumn<Canzone, Integer> anno;

    @FXML
    private TableColumn<Canzone, String> autore;

    @FXML
    private TableColumn<Canzone, Double> durata;

    @FXML
    private TableColumn<Canzone, String> genere;

    @FXML
    private TableColumn<Canzone, String> titolo;

    ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        genere.setCellValueFactory(new PropertyValueFactory<Canzone, String>("genere"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        
        repository.setItems(list);
    }

    
    ObservableList<Canzone> list = FXCollections.observableArrayList(songs);    

}