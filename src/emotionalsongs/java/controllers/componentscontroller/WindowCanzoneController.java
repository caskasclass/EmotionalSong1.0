package emotionalsongs.java.controllers.componentscontroller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.lang.model.util.ElementScanner14;

import emotionalsongs.java.Managers.EmotionsManager;
import emotionalsongs.java.Managers.FileManager;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.CanzoneEvaluation;
import emotionalsongs.java.util.Emozione;
import emotionalsongs.java.util.GlobalsVariables;
import emotionalsongs.java.util.User;
import emotionalsongs.java.util.ValutazioneUtente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class WindowCanzoneController implements Initializable {

    @FXML
    private Label labelNoUser;

    @FXML
    private Label labelNoUser2;

    @FXML
    private Label Album;

    @FXML
    private Label Data;

    @FXML
    private Label Info;

    @FXML
    private HBox prospetticoBar;

    @FXML
    private Label titolo;

    @FXML
    private FlowPane emotionsContainer;

    @FXML
    private PieChart pieEmotions;

    @FXML
    private Button saveButt;

    @FXML
    private HBox hbox1;

    @FXML
    private VBox pieBox;

    private int indxCanzEv=0;

    private Integer y = 0;

    private User u = GlobalsVariables.currentUser;

    private ArrayList<CanzoneEvaluation> listValutazioni = new ArrayList<CanzoneEvaluation>();

    private CanzoneEvaluation valutazioneCanzone = new CanzoneEvaluation();

    private ValutazioneUtente valutazioneUtente = new ValutazioneUtente();

    private ArrayList<ChoiceBox<Integer>> list = new ArrayList<ChoiceBox<Integer>>();

    private ArrayList<Emozione> emList = GlobalsVariables.getEmotions();

    final String PATH = "./src/emotionalsongs/resources/DataBaseBrutto/Emozioni.dati.txt";


    Canzone c = null;


    Integer i = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (!EmotionsManager.checkLengthFile()) {
            listValutazioni = EmotionsManager.readEmozioni();
        }

        setCanzone();
        createBoxes();
        setPieChart();

    }

    public void getCanzone(Canzone canzone) {
        c = canzone;

    }

    public void setCanzone() {

        Album.setText(c.getAlbum());
        Info.setText(c.getAutore() + " • " + c.getAnno() + " • " + c.getDurata() + " min");
        titolo.setText(c.getTitolo());
    }

    private void createBoxes() {

        for (Emozione e : Emozione.values()) {

            VBox boxEmozione = new VBox();
            boxEmozione.setPadding(new Insets(0, 0, 0, 0));
            boxEmozione.setPrefSize(100, 200);
            Label tipoEmozione = new Label(e.name());
            ChoiceBox<Integer> choiceBox = new ChoiceBox<Integer>();
            choiceBox.getStyleClass().add("transparent_box");
            setChoiceBox(choiceBox);
            boxEmozione.getChildren().addAll(tipoEmozione, choiceBox);
            list.add(choiceBox);
            emotionsContainer.getChildren().add(boxEmozione);
        }

    }

    private void setChoiceBox(ChoiceBox<Integer> choice) {

        if (u == null) {

            hbox1.getChildren().remove(saveButt);
            choice.setDisable(true);
            labelNoUser.setText("Per inserire la valutazione fare login");

        } else if (getEvaluation()) {
            hbox1.getChildren().remove(saveButt);
            labelNoUser.setText("Hai già valutato questa canzone");
            Object e = valutazioneUtente.getValutazione().keySet().toArray()[y];
            choice.setValue(valutazioneUtente.getValutazione().get(e));
            choice.setDisable(true);
            y++;
            
        } else {
            hbox1.getChildren().remove(labelNoUser);
            for (int i = 0; i < 5; i++) {
                choice.getItems().add(i + 1);
            }
        }
    }

    private void updateMap(Emozione e, Integer i, HashMap<Emozione, Integer> map) {

        map.put(e, i);

    }

    //////////////////////// ok/////////////////////////////////
    private boolean getEvaluation() {

        if (listValutazioni.contains(new CanzoneEvaluation(c.getIdCanzone(), null))) {
            indxCanzEv = listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null));
            if (listValutazioni.get(indxCanzEv).getValutazione().contains(new ValutazioneUtente(null, u.getId()))) {
                valutazioneUtente = listValutazioni.get(indxCanzEv).getValutazione().get(listValutazioni.get(indxCanzEv)
                        .getValutazione().indexOf(new ValutazioneUtente(null, u.getId())));
                return true;
            } else {
                valutazioneCanzone = new CanzoneEvaluation(c.getIdCanzone(), new ArrayList<ValutazioneUtente>());
                return false;
            }
        }else{
            
            valutazioneCanzone = new CanzoneEvaluation(c.getIdCanzone(), new ArrayList<ValutazioneUtente>());        }

        return false;

    }

    ////////////////////////////////////////////////////////////
    private HashMap<Emozione, Integer> createMap() {

        HashMap<Emozione, Integer> map = new HashMap<Emozione, Integer>();
        for (Emozione e : Emozione.values()) {
            map.put(e, 0);
        }
        return map;
    }
    ////////////////////////////////////////////////////////////

    public void setEmotionsValue(ActionEvent e) {
        int i = 0;
        HashMap<Emozione, Integer> newmap = createMap();


        for (ChoiceBox<Integer> choice : list) {
            updateMap(emList.get(i), choice.getValue(), newmap);
            i++;
        }
        
        valutazioneUtente = new ValutazioneUtente(newmap, u.getId());
        valutazioneCanzone.addEvaluation(valutazioneUtente);
        if(listValutazioni.isEmpty() || listValutazioni == null){
            listValutazioni.remove(new CanzoneEvaluation(c.getIdCanzone(),null));
        }
        listValutazioni.add(valutazioneCanzone);
        
        
        EmotionsManager.getEmozioni(listValutazioni);
        pieEmotions.setVisible(true);
        setPieChart(); 
        hbox1.getChildren().remove(saveButt);
        for(ChoiceBox<Integer> cb : list){
            cb.setDisable(true);
        }   


    }

    private void setPieChart() {

        pieEmotions.setTitle("Prospettico Emozioni");
        pieEmotions.setLegendSide(Side.LEFT);

        // Label lab= new Label("");
        // lab.setTextFill(Color.WHITE);
        // lab.setStyle("-fx-font: 24 Arials;");
        if (!(new File(PATH).length() == 0)) {

            pieEmotions.setData(createList());
            /*
             * for(final PieChart.Data obj : pieEmotions.getData()){
             * obj.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new
             * EventHandler<MouseEvent>() {
             * 
             * @Override
             * public void handle(MouseEvent e){
             * lab.setTranslateX(e.getSceneX());
             * lab.setTranslateY(e.getSceneY());
             * lab.setText(String.valueOf(obj.getPieValue())+ "%");
             * }
             * });
             * }
             */
        }

    }

    private ObservableList<PieChart.Data> createList() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        // int [] array = new int[9];
        HashMap<Emozione, Integer> tmp = createMap();

        for (CanzoneEvaluation canzEv : EmotionsManager.readEmozioni()) {
            if (canzEv.getIdCanzone().equals(c.getIdCanzone())) {
                for (ValutazioneUtente v : canzEv.getValutazione()) {
                    v.getValutazione().forEach((key, value) -> {
                        tmp.put(key, (tmp.get(key) + value));
                    });

                }
                break;
            }
        }

        tmp.forEach((key, value) -> {
            PieChart.Data obj = new PieChart.Data(key.toString(), value);
            pieChartData.add(obj);
        });
        return pieChartData;
    }

    public ArrayList<MenuItem> createArray(MenuButton menu) {
        ArrayList<MenuItem> array = castList(menu.getItems());
        return array;
    }

    public static ArrayList<MenuItem> castList(ObservableList<?> al) {

        ArrayList<MenuItem> array = new ArrayList<MenuItem>();
        for (Object obj : al) {
            if (obj instanceof MenuItem) {
                array.add((MenuItem) obj);
            }
        }
        return array;
    }

}
