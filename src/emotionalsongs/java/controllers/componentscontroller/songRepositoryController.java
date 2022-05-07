package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.util.Canzone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class songRepositoryController implements Initializable{

    @FXML
    private TextField cercaCanzone;

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

    private final ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

    private final ObservableList<Canzone> list = FXCollections.observableArrayList(songs);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        genere.setCellValueFactory(new PropertyValueFactory<Canzone, String>("genere"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        repository.setItems(list);
        cercaBranoMusicale();
    }

    private void cercaBranoMusicale(){
        FilteredList<Canzone> filteredData = new FilteredList<Canzone>(list, b -> true);
        cercaCanzone.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Canzone>) (Canzone canzone) -> {

                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCase = newValue.toLowerCase();

                if(canzone.getTitolo().toLowerCase().contains(lowerCase) != false){
                    return true;
                }
                else if(canzone.getAutore().toLowerCase().contains(lowerCase) != false){
                    return true;
                }
                else if(String.valueOf(canzone.getAnno()).contains(lowerCase) != false){
                    return true;
                }
                else 
                 return false;
            });
        });

        SortedList<Canzone> sortedData = new SortedList<Canzone>(filteredData);

        sortedData.comparatorProperty().bind(repository.comparatorProperty());

        repository.setItems(sortedData);
    }
    


        

}