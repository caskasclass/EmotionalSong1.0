package emotionalsongs.java.controllers.componentscontroller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
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
    private Label Ama;

    @FXML
    private Label Calm;

    @FXML
    private Label Joy;

    @FXML
    private Label Nos;

    @FXML
    private Label Pow;

    @FXML
    private Label Sad;

    @FXML
    private Label Sol;

    @FXML
    private Label Tend;

    @FXML
    private Label Tens;

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

    private User u = GlobalsVariables.currentUser;

    private ArrayList<ChoiceBox<Integer>> list = new ArrayList<ChoiceBox<Integer>>();

    private ArrayList<Emozione> emList = GlobalsVariables.getEmotions();

    final String PATH = "./src/emotionalsongs/resources/DataBaseBrutto/Emozioni.dati.txt";

    private HashMap<Emozione, Integer> newmap = createMap();


    Canzone c = null;

    // private CanzoneEvaluation canzone = new CanzoneEvaluation(c, v)

    Integer i = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    private void setChoiceBox(ChoiceBox<Integer> choice) {

        for (int i = 0; i < 5; i++) {
            choice.getItems().add(i + 1);
        }
    }

    private void createBoxes() {
        for (Emozione e : Emozione.values()) {

            VBox boxEmozione = new VBox();
            Label tipoEmozione = new Label(e.name());
            ChoiceBox<Integer> choiceBox = new ChoiceBox<Integer>();
            choiceBox.getStyleClass().add("transparent_box");
            setChoiceBox(choiceBox);
            boxEmozione.getChildren().addAll(tipoEmozione, choiceBox);
            list.add(choiceBox);
            emotionsContainer.getChildren().add(boxEmozione);
        }
    }

    private void updateMap(Emozione e, Integer i, HashMap<Emozione, Integer> map) {

        map.put(e, i);

    }

    private HashMap<Emozione, Integer> createMap() {

        HashMap<Emozione, Integer> map = new HashMap<Emozione, Integer>();
        for (Emozione e : Emozione.values()) {
            map.put(e, 0);
        }
        return map;
    }

    public void setEmotionsValue(ActionEvent e){
        int i=0;
        
        ValutazioneUtente tmp = new ValutazioneUtente(newmap, u.getId());
        ArrayList<CanzoneEvaluation> tmp3 = new ArrayList<CanzoneEvaluation>();
        if(!(new File(PATH).length() == 0)){
            tmp3 = EmotionsManager.readEmozioni();
        }

        for(ChoiceBox<Integer> choice : list){
            updateMap(emList.get(i),choice.getValue(),newmap);
            i++;
        }
        
        if(!EmotionsManager.readEmozioni().contains(new CanzoneEvaluation(c.getIdCanzone(), null)))
        {
            ArrayList<ValutazioneUtente> array =new ArrayList<ValutazioneUtente>();
            array.add(tmp);
            CanzoneEvaluation tmp2 = new CanzoneEvaluation(c.getIdCanzone(), array );
            tmp3.add(tmp2);
        }else{
            tmp3.get(tmp3.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null) )).addEvaluation(tmp);         
        }
        EmotionsManager.getEmozioni(tmp3);  
        setPieChart();
    }

    private void setPieChart() {

        pieEmotions.setTitle("Prospettico Emozioni");
        pieEmotions.setLegendSide(Side.LEFT);
        Label lab= new Label("");
        lab.setTextFill(Color.WHITE);
        lab.setStyle("-fx-font: 24 Arials;");
        if (!(new File(PATH).length() == 0)) {
            
            pieEmotions.setData(createList());
            for(final PieChart.Data obj : pieEmotions.getData()){
                obj.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e){
                        lab.setTranslateX(e.getSceneX());
                        lab.setTranslateY(e.getSceneY());
                        lab.setText(String.valueOf(obj.getPieValue())+ "%");
                    }
                });
            }
        }

    }

    private ObservableList<PieChart.Data> createList() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        //int [] array = new int[9];
        HashMap<Emozione, Integer> tmp = createMap();

        for (CanzoneEvaluation canzEv : EmotionsManager.readEmozioni()) {
            if (canzEv.getIdCanzone().equals(c.getIdCanzone())) {
                for (ValutazioneUtente v : canzEv.getValutazione()) {
                    /*for (Integer  : v.getValutazione().keySet()) {
                        for (int i : array){

                        }
                        pieChartData.add(new PieChart.Data(e.toString(), v.getValutazione().get(e)));
                    }*/
                    v.getValutazione().forEach((key,value)-> {
                        tmp.put(key, (tmp.get(key) + value));
                    });

                }
                break;
            }
        }
        
        tmp.forEach((key,value) -> {
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
