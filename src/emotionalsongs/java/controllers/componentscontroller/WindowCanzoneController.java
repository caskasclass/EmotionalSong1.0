package emotionalsongs.java.controllers.componentscontroller;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import emotionalsongs.java.Managers.CommentiManager;
import emotionalsongs.java.Managers.EmotionsManager;
import emotionalsongs.java.util.Canzone;
import emotionalsongs.java.util.CanzoneEvaluation;
import emotionalsongs.java.util.Commenti;
import emotionalsongs.java.util.Dettagli;
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
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
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
    private HBox hbox1;

    @FXML
    private VBox pieBox;

    private int indxCanzEv = 0;

    private Integer y = 0;
    @FXML
    private Label noComments;

    @FXML
    private Label percentuale;

    @FXML
    private ImageView nodataImg;

    @FXML
    private Button commentButt;

    @FXML
    private TextField commentField;
    @FXML
    private VBox commentsBox;
    @FXML
    private VBox commentsContainer;

    @FXML
    private Button saveButt;
    

    @FXML
    private HBox textBox;
    private int indxComm = 0;
    private ArrayList<Commenti> comments = new ArrayList<Commenti>();

    private Commenti com = new Commenti();

    private Dettagli d = new Dettagli();

    private User u = GlobalsVariables.currentUser;

    private ArrayList<CanzoneEvaluation> listValutazioni = new ArrayList<CanzoneEvaluation>();

    private CanzoneEvaluation valutazioneCanzone = new CanzoneEvaluation();

    private ValutazioneUtente valutazioneUtente = new ValutazioneUtente();

    private ArrayList<ChoiceBox<Integer>> list = new ArrayList<ChoiceBox<Integer>>();

    private ArrayList<Emozione> emList = GlobalsVariables.getEmotions();

    final String PATH = "./src/emotionalsongs/resources/database/Emozioni.dati.txt";

    Canzone c = null;

    Integer i = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (!EmotionsManager.checkLengthFile()) {
            listValutazioni = EmotionsManager.readEmozioni();
        }
        if (!CommentiManager.checkLengthFile()) {
            comments = CommentiManager.readCommenti();
        }

        createBoxes();
        setCanzone();
        setPieChart();
        setCommenti();
        setTextField();

    }

    public void getCanzone(Canzone canzone) {
        c = canzone;

    }

    public void setCanzone() {

        Info.setText(c.getAutore() + " • " + c.getAlbum()+ " • " + c.getAnno() + " • " + c.getDurata() + " min");
        titolo.setText(c.getTitolo());
        if (valutazioneCanzone.getValutazione().size() == 0 || valutazioneCanzone.getValutazione() == null) {
            Data.setText("Numero Valutazioni : 0");

        } else {
            Data.setText("Numero Valutazioni : " + valutazioneCanzone.getValutazione().size());
        }

    }

    private void createBoxes() {

        for (Emozione e : Emozione.values()) {
            VBox boxEmozione = new VBox();
            boxEmozione.setAlignment(Pos.CENTER);
            boxEmozione.setSpacing(5);
            boxEmozione.setPrefWidth(100);
            Label tipoEmozione = new Label(e.name());
            ChoiceBox<Integer> choiceBox = new ChoiceBox<Integer>();
            choiceBox.setPrefWidth(100);
            choiceBox.getStyleClass().add("transparent_box");
            setChoiceBox(choiceBox);
            boxEmozione.getChildren().addAll(tipoEmozione, choiceBox);
            list.add(choiceBox);
            emotionsContainer.getChildren().add(boxEmozione);
        }

    }

    private void setChoiceBox(ChoiceBox<Integer> choice) {

        if (u == null) {
            setClassData();
            int target = listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null));
            if (!(target == -1)) {
                valutazioneCanzone = listValutazioni.get(target);
            }
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

    private void setClassData() {
        if (listValutazioni.contains(new CanzoneEvaluation(c.getIdCanzone(), null))) {
            indxCanzEv = listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null));
            valutazioneCanzone = listValutazioni
                    .get(listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null)));
        } else {
            valutazioneCanzone = new CanzoneEvaluation(c.getIdCanzone(), new ArrayList<ValutazioneUtente>());
        }

    }

    //////////////////////// ok/////////////////////////////////
    private boolean getEvaluation() {

        if (listValutazioni.contains(new CanzoneEvaluation(c.getIdCanzone(), null))) {
            indxCanzEv = listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null));
            valutazioneCanzone = listValutazioni
                    .get(listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null)));
            if (valutazioneCanzone.getValutazione().contains(new ValutazioneUtente(null, u.getId()))) {
                valutazioneUtente = listValutazioni.get(indxCanzEv).getValutazione().get(listValutazioni.get(indxCanzEv)
                        .getValutazione().indexOf(new ValutazioneUtente(null, u.getId())));
                return true;
            } else {
                return false;
            }
        } else {
            valutazioneCanzone = new CanzoneEvaluation(c.getIdCanzone(), new ArrayList<ValutazioneUtente>());
        }
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

        if (!(listValutazioni.isEmpty() || listValutazioni == null)) {
            if(listValutazioni.contains(new CanzoneEvaluation(c.getIdCanzone(), null))){
                listValutazioni.remove(indxCanzEv);
            }
            
        }

        listValutazioni.add(valutazioneCanzone);

        EmotionsManager.getEmozioni(listValutazioni);

        if(pieBox.getChildren().contains(nodataImg)){
            pieBox.getChildren().remove(nodataImg);
            pieBox.getChildren().add(pieEmotions);
        }
        
        setPieChart();
        hbox1.getChildren().remove(saveButt);
        for (ChoiceBox<Integer> cb : list) {
            cb.setDisable(true);
        }
        if (valutazioneCanzone.getValutazione().size() == 0 || valutazioneCanzone.getValutazione() == null) {
            Data.setText("Numero Valutazioni : 0");

        } else {
            Data.setText("Numero Valutazioni : " + valutazioneCanzone.getValutazione().size());
        }

    }

    private void setPieChart() {


        ObservableList<PieChart.Data> Piedata = createList();
        if(Piedata.isEmpty()){
            pieBox.getChildren().remove(pieEmotions);

        }else{
            pieBox.getChildren().remove(nodataImg);
            pieEmotions.setData(Piedata);
            pieEmotions.setLegendSide(Side.LEFT);

            for (final PieChart.Data data : Piedata) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e ->{
                        String s = String.format("%.1f",data.getPieValue()/valutazioneCanzone.getValutazione().size());
                        percentuale.setText(data.getName()+" : "+ s);
                    }
                );
            }
            pieEmotions.setLegendSide(Side.LEFT);
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
            if(value != 0){
                PieChart.Data obj = new PieChart.Data(key.toString(), value);
                pieChartData.add(obj);
            }
            
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

    private void setCommenti() {

        if(u == null){
            commentsContainer.getChildren().remove(textBox);
        }
        
        if (isCommented()) {
            noComments.setText("Commenti");
            for (Dettagli d : comments.get(comments.indexOf(new Commenti(c.getIdCanzone(), null))).getDetails()) {
                HBox hb = new HBox();
                Label l = new Label(d.getUser().getUsername().toUpperCase() + " ha commentato: " + d.getCommento());
                hb.getChildren().add(l);
                hb.getStyleClass().add("hbox");
                commentsBox.getChildren().add(hb);
            }
        } else {
            if (comments.contains(new Commenti(c.getIdCanzone(), null))) {
                noComments.setText("Commenti");
                for (Dettagli d : comments.get(comments.indexOf(new Commenti(c.getIdCanzone(), null))).getDetails()) {
                    HBox hb = new HBox();
                    Label l = new Label(d.getUser().getUsername().toUpperCase() + " ha commentato: " + d.getCommento());
                    hb.getChildren().add(l);
                    hb.getStyleClass().add("hbox");
                    commentsBox.getChildren().add(hb);
                }
            } else {
                noComments.setText("Non ci sono commenti");
            }

        }
    }

    private boolean isCommented() {

        if (comments.contains(new Commenti(c.getIdCanzone(), null))) {
            indxComm = comments.indexOf(new Commenti(c.getIdCanzone(), null));
            com = comments.get(indxComm);
            if(u == null) {
                return true;
            } else {
                if (com.getDetails().contains(new Dettagli(u, null))) {
                    d = comments.get(indxComm).getDetails().get(comments.get(indxComm)
                            .getDetails().indexOf(new Dettagli(u, null)));
                    return true;
                } else {

                    return false;
                }
            }

        } else {
            com = new Commenti(c.getIdCanzone(), new ArrayList<Dettagli>());
        }
        return false;

    }

    public void getCommenti(ActionEvent e) {

        isCommented();
        d = new Dettagli(u, commentField.getText());
        com.addComment(d);
        if (!(comments.isEmpty() || comments == null)) {
            if(comments.contains(new Commenti(c.getIdCanzone(), null))){
                comments.remove(indxComm);
            } 
        }
        comments.add(com);
        CommentiManager.getEmozioni(comments);
        HBox hb = new HBox();
        Label l = new Label(d.getUser().getUsername().toUpperCase() + " ha commentato: " + d.getCommento());
        hb.getChildren().add(l);
        hb.getStyleClass().add("hbox");
        commentsBox.getChildren().add(hb);
        noComments.setText("Commenti");
        commentField.setText("");
    }
//non ho aggiusnto niente
    private void setTextField(){
        Pattern pattern = Pattern.compile(".{0,250}");
        TextFormatter<TextField> formatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
    });
    commentField.setTextFormatter(formatter);
    }
}
