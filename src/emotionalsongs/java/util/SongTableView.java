package emotionalsongs.java.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.controllers.componentscontroller.WindowCanzoneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class SongTableView {

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
    private TableColumn<Canzone, Void> addbutton;

    FxmlLoader loader = new FxmlLoader();

    private final ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

    private final ObservableList<Canzone> list = FXCollections.observableArrayList(songs);

    FxmlLoader obj = new FxmlLoader();

    public SongTableView(TableView<Canzone> Repository, TableColumn<Canzone, String> album2,
            TableColumn<Canzone, Void> indx,
            TableColumn<Canzone, Integer> anno2, TableColumn<Canzone, String> autore2,
            TableColumn<Canzone, Double> durata2,
            TableColumn<Canzone, String> titolo2) {
        this.repository = Repository;
        this.album = album2;
        this.songindex = indx;
        this.anno = anno2;
        this.autore = autore2;
        this.durata = durata2;
        this.titolo = titolo2;
    }

    public void initialize() {

        // ******************* questa funzione rende la riga cliccabile
        // ***************************************//
        repository.setRowFactory(tableView -> {
            TableRow<Canzone> row = new TableRow<Canzone>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Canzone c = tableView.getItems().get(row.getIndex());
                            Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
                            GlobalsVariables.left_side_bpane.getChildren().remove(p);
                            SendData(c);
                        }
                    }

                }
            });
            return row;

        });
        // ****************************************************************************************************//

        // ********************* questa serve sempre e setta l'index !
        // ************************//
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
        // ************************************************************************************//

        // ***************** queste settano le collone principali della canzone
        // ****************//
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        // *************************************************************************************//

        // ******************************** per riempire la playlist
        // **************************//
        repository.setItems(list);
        // ************************************************************************************//

    }

    public void initializeAdded() {

       

        // ********************* questa serve sempre e setta l'index !
        // ************************//
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
        // ************************************************************************************//

        // ***************** queste settano le collone principali della canzone
        // ****************//
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        // *************************************************************************************//

        // ******************************** per riempire la playlist
        // **************************//
        repository.setItems(FXCollections.observableArrayList(GlobalsVariables.canzoni));
        // ************************************************************************************//

    }

    public void initializeFiltered(int num) {
        // ******************* questa funzione rende la riga cliccabile
        // ***************************************//
        repository.setRowFactory(tableView -> {
            TableRow<Canzone> row = new TableRow<Canzone>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            // row.setStyle("-fx-background-color: RED");
                            Canzone c = tableView.getItems().get(row.getIndex());
                            Parent p = (Parent) GlobalsVariables.left_side_bpane.getCenter();
                            GlobalsVariables.left_side_bpane.getChildren().remove(p);
                            SendData(c);
                        }
                    }

                }
            });
            return row;

        });
        // ****************************************************************************************************//

        // ********************* questa serve sempre e setta l'index !
        // ************************//
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
        // ************************************************************************************//

        // ***************** queste settano le collone principali della canzone
        // ****************//
        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));
        // ************************************************************************************//

        // ******************************** per riempire la playlist
        // **************************//
        repository.setItems(setNumberofRows(num));
        // ************************************************************************************//

    }

    public void addButton(TableColumn<Canzone, Void> optionbutton2) {
        this.optionbutton = optionbutton2;
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

    public void addToPlylistButton(TableColumn<Canzone, Void> buttonadder) {
        this.addbutton = buttonadder;
        Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>> cellFactory = new Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>>() {
            @Override
            public TableCell<Canzone, Void> call(final TableColumn<Canzone, Void> param) {
                final TableCell<Canzone, Void> cell = new TableCell<Canzone, Void>() {

                    private final Button btn = new Button("ADD");
                    {

                        btn.setOnAction((ActionEvent event) -> {
                            // tolgo dalla ricerca e aggiungo alla tablella di spora!!!
                            Canzone canzone = getTableView().getItems().get(getIndex());
                            refresh(canzone);

                            System.out.println("hai aggiunto: " + canzone.getTitolo());
                            GlobalsVariables.canzoni.add(canzone);
                            GlobalsVariables.addedSongs.setItems(FXCollections.observableArrayList(GlobalsVariables.canzoni));
                            GlobalsVariables.addedSongs.refresh();
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

        addbutton.setCellFactory(cellFactory);

    }

    // ******************************* hmmmm forse ho capito
    // **********************************************//
    public void cercaBranoMusicale(TextField CercaCanzone) {
        this.cercaCanzone = CercaCanzone;
        FilteredList<Canzone> filteredData = new FilteredList<Canzone>(list, b -> true);
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

    private void refresh(Canzone c) {
        list.remove(c);
        repository.setItems(list);
        repository.refresh();
        cercaBranoMusicale(cercaCanzone);

    }
    // ********************************************************************************************//

    // *************** per visualizzaere il brano su un altra scene
    // *************************//
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
    // **************************************************************************************//

    // ***************************** questa la voglio spiegata
    // ******************************//
    public FilteredList<Canzone> setNumberofRows(int num) {
        FilteredList<Canzone> filteredData = new FilteredList<>(
                list,
                song -> list.indexOf(song) < num);
        return filteredData;
    }
    // *************************************************************************************//

}
