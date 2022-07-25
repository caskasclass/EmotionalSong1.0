package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ResourceBundle;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.FxmlLoader;

import emotionalsongs.java.util.SongTableView;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class songRepositoryController implements Initializable {

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
    FxmlLoader loader = new FxmlLoader();

    FxmlLoader obj = new FxmlLoader();

    @Override
    public void initialize(URL urilink, ResourceBundle reb) {
        
        SongTableView table = new SongTableView(repository, album, songindex, anno, autore, durata, titolo);
        table.initialize();
        table.addButton(optionbutton);

        //*********************** questa tanta roba *********************************// 
        table.cercaBranoMusicale(cercaCanzone,repository.getItems().size());
        //***************************************************************************//
        
        //table.setNumberofCells(25);
    }


    public void InserisciEmozioniBrano(ActionEvent e) {

        System.out.println("funzia");
        Pane ui = obj.getPane("emotions");
        songPane.setCenter(ui);
    }

}