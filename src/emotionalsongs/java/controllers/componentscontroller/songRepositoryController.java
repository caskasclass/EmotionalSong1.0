package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class songRepositoryController implements Initializable{

    @FXML
    private TextField cercaCanzone;

    @FXML
    private TableView<Canzone> repository;
    
    @FXML
    private TableColumn<Canzone, String> album;

    @FXML
    private TableColumn<Canzone, Void> songindex;

    @FXML
    private TableColumn<Canzone, Integer> anno;

    @FXML
    private TableColumn<Canzone, String> autore;

    @FXML
    private TableColumn<Canzone, Double> durata;


    @FXML
    private TableColumn<Canzone, String> titolo;


    @FXML
    private TableColumn<Canzone, Void> optionbutton;

    @FXML
    private BorderPane songPane;

    @FXML
    private Label songToAdd;

    private final ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

    private final ObservableList<Canzone> list = FXCollections.observableArrayList(songs);

    FxmlLoader obj = new FxmlLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        songindex.setCellFactory(col -> new TableCell<Canzone, Void>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString((index+1)));
                }
            }
        });   
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        addButton();
        repository.setItems(list);
        cercaBranoMusicale();
    }

    private void addButton(){
      
        Callback<TableColumn<Canzone,Void>, TableCell<Canzone,Void>> cellFactory = new Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>>(){
            @Override
            public TableCell<Canzone, Void> call(final TableColumn<Canzone, Void> param) {
                final TableCell<Canzone, Void> cell = new TableCell<Canzone, Void>() {

                  
                    MenuItem mi1 = new MenuItem("Aggiungi alla Playlist");
                    MenuItem mi2 = new MenuItem("Vedi i Tag");
                    MenuItem mi3 = new MenuItem("Altro ...");

                    private final  MenuButton btn = new MenuButton("•••", null,mi1,mi2,mi3);
                    
            
                    

                    {
                    mi1.setOnAction((ActionEvent event) -> {
                            Canzone canzone = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + canzone.getTitolo());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        optionbutton.setCellFactory(cellFactory);

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

    public void InserisciEmozioniBrano(ActionEvent e){
    
        System.out.println("funzia");
        Pane ui = obj.getPane("emotions");
        songPane.setCenter(ui);
    }

    
    


        

}