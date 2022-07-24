package emotionalsongs.java.util;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.controllers.componentscontroller.WindowCanzoneController;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;
import emotionalsongs.java.util.GlobalsVariables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class SongTableView implements Serializable{

    
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

    FxmlLoader loader = new FxmlLoader();

    private final ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

    private final ObservableList<Canzone> list = FXCollections.observableArrayList(songs);

    FxmlLoader obj = new FxmlLoader();
    
    public SongTableView(TableView<Canzone> Repository,TableColumn<Canzone, String> album2,TableColumn<Canzone, Void> indx,
    TableColumn<Canzone, Integer> anno2,TableColumn<Canzone, String> autore2, TableColumn<Canzone, Double> durata2, 
    TableColumn<Canzone, String> titolo2){

        this.repository= Repository;
        this.album=album2;
        this.songindex=indx;
        this.anno=anno2;
        this.autore=autore2;
        this.durata= durata2;
        this.titolo= titolo2;
        

    }
  
    public void initialize() {

        repository.setRowFactory(tableView -> {
            TableRow<Canzone> row = new TableRow<Canzone>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Canzone c = tableView.getItems().get(row.getIndex());
                    Node n = (Node) mouseEvent.getSource();
                    Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
                    GlobalsVariables.left_side_bpane.getChildren().remove(p);

                    SendData(c);

                }
            });
            return row;

        });
        songindex.setCellFactory(col -> new TableCell<Canzone, Void>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString((index + 1)));
                }
            }
        });
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        repository.setItems(list);
        
    }
    public void initializeFiltered(int num) {

        repository.setRowFactory(tableView -> {
            TableRow<Canzone> row = new TableRow<Canzone>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Canzone c = tableView.getItems().get(row.getIndex());
                    Node n = (Node) mouseEvent.getSource();
                    Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
                    GlobalsVariables.left_side_bpane.getChildren().remove(p);

                    SendData(c);

                }
            });
            return row;

        });
        songindex.setCellFactory(col -> new TableCell<Canzone, Void>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString((index + 1)));
                }
            }
        });
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        repository.setItems(setNumberofRows(num));
        
    }

    public void addButton(TableColumn<Canzone, Void> optionbutton2) {
        this.optionbutton=optionbutton2;
        Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>> cellFactory = new Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>>() {
            @Override
            public TableCell<Canzone, Void> call(final TableColumn<Canzone, Void> param) {
                final TableCell<Canzone, Void> cell = new TableCell<Canzone, Void>() {

                    MenuItem mi1 = new MenuItem("Aggiungi alla Playlist");
                    MenuItem mi2 = new MenuItem("Vedi i Tag");
                    MenuItem mi3 = new MenuItem("Altro ...");

                    private final MenuButton btn = new MenuButton("•••", null, mi1, mi2, mi3);

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

    public void cercaBranoMusicale(TextField CercaCanzone,int num) {
        this.cercaCanzone=CercaCanzone;
        FilteredList<Canzone> filteredData = new FilteredList<Canzone>(setNumberofRows(num), b -> true);
        cercaCanzone.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Canzone>) (Canzone canzone) -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCase = newValue.toLowerCase();

                if (canzone.getTitolo().toLowerCase().contains(lowerCase) != false) {
                    return true;
                } else if (canzone.getAutore().toLowerCase().contains(lowerCase) != false) {
                    return true;
                } else if (String.valueOf(canzone.getAnno()).contains(lowerCase) != false) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<Canzone> sortedData = new SortedList<Canzone>(filteredData);

        sortedData.comparatorProperty().bind(repository.comparatorProperty());

        repository.setItems(sortedData);
    }

    private void SendData(Canzone canz) {
        try {
            FXMLLoader load = loader.getComponentsLoader("WindowCanzone");
            WindowCanzoneController windowCanzoneController = new WindowCanzoneController();
            windowCanzoneController.setCanzone(canz);
            load.setController(windowCanzoneController);
            Pane ui = load.load();
            GlobalsVariables.left_side_bpane.setCenter(ui);
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }

    }

   public FilteredList<Canzone> setNumberofRows(int num){
    FilteredList<Canzone> filteredData = new FilteredList<>(
    list,
    song -> list.indexOf(song) < num
    );
    return filteredData;
   }

  

}

