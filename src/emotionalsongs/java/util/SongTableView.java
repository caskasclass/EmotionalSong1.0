/**
 * Provides the Classes that represent the main objects of the program
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import emotionalsongs.java.Managers.CanzoniManager;
import emotionalsongs.java.Managers.PlaylistManager;
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
import javafx.scene.control.Label;
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

/**
 * Represents a SongTableView object. fxml TableView
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class SongTableView {

    /**fxml element for graphics */
    @FXML
    private TextField cercaCanzone;
    /**fxml element for graphics */
    @FXML
    private TableView<Canzone> repository;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> album;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> songindex;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Integer> anno;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> autore;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Double> durata;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, String> titolo;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> optionbutton;
    /**fxml element for graphics */
    @FXML
    private TableColumn<Canzone, Void> addbutton;

    /**Useful object to load fxml file*/
    FxmlLoader obj = new FxmlLoader();

    /**Useful ArrayList of type Canzone objects that are in the Canzoni.data.txt file*/
    private final ArrayList<Canzone> songs = CanzoniManager.readCanzoni();

    /**Useful ObservableList of type Canzone objects that will fill the TableView*/
    private final ObservableList<Canzone> list = FXCollections.observableArrayList(songs);

    /**
     * Returns an object of type SongTableView.
     * @param Repository the TableView of the songs
     * @param album2 column for albums of the songs
     * @param indx column for indexes of the songs
     * @param anno2 column for years of the songs
     * @param autore2 column for authors of the songs
     * @param durata2 column for durations of the songs
     * @param titolo2 column for titles of the songs
     */
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

    /**Sets the TableView with all the songs in Canzoni.data.txt and makes the row of a song accessible */
    public void initialize() {

        visualizzaEmozioneBrano();
        setIndex();
        setColumns();
        repository.setItems(list);

    }

    /**
     * /**Sets the TableView of a certain playlist and makes the row of a song accessible
     * @param track the list of songs of the playlist
     */
    public void initializePlaylistList(ObservableList<Canzone> track) {

        visualizzaEmozioneBrano();
        setIndex();
        setColumns();
        repository.setItems(track);

    }

    /**Sets the TableView of the playlist that is being created*/
    public void initializeAdded() {

        setIndex();
        setColumns();
        repository.setItems(FXCollections.observableArrayList(GlobalsVariables.canzoni));

    }

    /**
     * Sets the TableView with the first num songs in Canzoni.data.txt and makes the row of a song accessible 
     * @param num number of songs displayed
     */
    public void initializeFiltered(int num) {

        visualizzaEmozioneBrano();
        setIndex();
        setColumns();
        repository.setItems(setNumberofRows(num));

    }

    /**
     * Adds a new column containing labels to a certain TableView
     * @param optionbutton2 the new column that will be added containing labels
     */
    public void addButton(TableColumn<Canzone, Void> optionbutton2) {
        this.optionbutton = optionbutton2;
        Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>> cellFactory = new Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>>() {
            @Override
            public TableCell<Canzone, Void> call(final TableColumn<Canzone, Void> param) {
                final TableCell<Canzone, Void> cell = new TableCell<Canzone, Void>() {


                    private final Label btn = new Label("•••");

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

    /**
     * Adds a new column containing buttons that lets the user remove a song from the TableView of a playlist
     * @param deletebutton the new column that will be added containing buttons
     */
    public void deleteFromPlaylist(TableColumn<Canzone, Void> deletebutton) {
        this.optionbutton = deletebutton;
        Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>> cellFactory = new Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>>() {
            @Override
            public TableCell<Canzone, Void> call(final TableColumn<Canzone, Void> param) {
                final TableCell<Canzone, Void> cell = new TableCell<Canzone, Void>() {

                    MenuItem mi1 = new MenuItem("Rimuovi dalla playlist");

                    private final MenuButton btn = new MenuButton("•••", null, mi1);

                    {
                        mi1.setOnAction((ActionEvent event) -> {
                            Canzone canzone = getTableView().getItems().get(getIndex());
                            ObservableList<Canzone> list =  repository.getItems();
                            list.remove(canzone);
                            repository.setItems(list);
                            repository.refresh();
                            ArrayList<Playlist> tmp = PlaylistManager.readPlaylist();
                            tmp.remove(tmp.indexOf(GlobalsVariables.plist));
                            GlobalsVariables.plist.getCanzoni().remove(canzone);
                            tmp.add(GlobalsVariables.plist);
                            PlaylistManager.getPlaylist(tmp);
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

    /**
     * Adds a new column containing buttons that lets the user add a song to the TableView of a playlist which is being created
     * @param buttonadder the new column that will be added containing buttons
     */
    public void addToPlylistButton(TableColumn<Canzone, Void> buttonadder) {
        this.addbutton = buttonadder;
        Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>> cellFactory = new Callback<TableColumn<Canzone, Void>, TableCell<Canzone, Void>>() {
            @Override
            public TableCell<Canzone, Void> call(final TableColumn<Canzone, Void> param) {
                final TableCell<Canzone, Void> cell = new TableCell<Canzone, Void>() {

                    private final Button btn = new Button("ADD");
                    {

                        btn.setOnAction((ActionEvent event) -> {
                            Canzone canzone = getTableView().getItems().get(getIndex());
                            refresh(canzone);
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

    /**
     * Method that lets the user search for a certain song in the TableView. The TableView updates itself whith a filtered list of songs based on author, title or year.
     * @param CercaCanzone fxml TextField in which the user writes to search the song
     */
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

    /**
     * Updated the TableView with the list of songs without c
     * @param c the type Canzone object representing the song
     */
    private void refresh(Canzone c) {
    
        list.remove(c);
        repository.setItems(list);
        repository.refresh();
        cercaBranoMusicale(cercaCanzone);

    }
    
    /**
     * Method that sets the center of the BorderPane with the graphic of the song canz
     * @param canz the type Canzone object representing the song
     */
    private void ShowSongWindow(Canzone canz) {
        try {
            FXMLLoader load = obj.getComponentsLoader("WindowCanzone");
            WindowCanzoneController windowCanzoneController = new WindowCanzoneController();
            windowCanzoneController.getCanzone(canz);
            load.setController(windowCanzoneController);
            Pane ui = load.load();
            GlobalsVariables.left_side_bpane.setCenter(ui);
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }

    }
    
    /**
     * Method that filters the List of type Canzone objects taking the first num of them
     * @param num number of type Canzone objects taken
     * @return FilteredList<Canzone> object containing num type Canzone objects
     */
    public FilteredList<Canzone> setNumberofRows(int num) {
        FilteredList<Canzone> filteredData = new FilteredList<>(
                list,
                song -> list.indexOf(song) < num);
        return filteredData;
    }

    /**Sets the column of indexes */
    private void setIndex(){
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
    }

    /**Sets the first row of the TableView with the column factors */
    private void setColumns(){

        album.setCellValueFactory(new PropertyValueFactory<Canzone, String>("album"));
        titolo.setCellValueFactory(new PropertyValueFactory<Canzone, String>("titolo"));
        autore.setCellValueFactory(new PropertyValueFactory<Canzone, String>("autore"));
        anno.setCellValueFactory(new PropertyValueFactory<Canzone, Integer>("anno"));
        durata.setCellValueFactory(new PropertyValueFactory<Canzone, Double>("durata"));

    }

    /**Sets the rows of the TableView clickable and shows the song window */
    private void visualizzaEmozioneBrano(){

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
                            ShowSongWindow(c);
                        }
                    }

                }
            });
            return row;
        });
    }
    
}
