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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller Class for the file WindowCanzone.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class WindowCanzoneController implements Initializable {

    /**fxml element for graphics */
    @FXML
    private Label labelNoUser;
    /**fxml element for graphics */
    @FXML
    private Label Data;
    /**fxml element for graphics */
    @FXML
    private Label Info;
    /**fxml element for graphics */
    @FXML
    private Label titolo;
    /**fxml element for graphics */
    @FXML
    private FlowPane emotionsContainer;
    /**fxml element for graphics */
    @FXML
    private PieChart pieEmotions;
    /**fxml element for graphics */
    @FXML
    private HBox hbox1;
    /**fxml element for graphics */
    @FXML
    private VBox pieBox;
    /**fxml element for graphics */
    @FXML
    private Label noComments;
    /**fxml element for graphics */
    @FXML
    private Label percentuale;
    /**fxml element for graphics */
    @FXML
    private ImageView nodataImg;
    /**fxml element for graphics */
    @FXML
    private TextField commentField;
    /**fxml element for graphics */
    @FXML
    private VBox commentsBox;
    /**fxml element for graphics */
    @FXML
    private VBox commentsContainer;
    /**fxml element for graphics */
    @FXML
    private Button saveButt;
    /**fxml element for graphics */
    @FXML
    private HBox textBox;
    /**Useful variable to manage comments */
    private int indxComm = 0;
    /**Useful variable to manage songs */
    private int indxCanzEv = 0;
    /**Useful variable to manage evaluations */
    private Integer y = 0;
    /**Useful ArrayList of type Commenti objects */
    private ArrayList<Commenti> comments = new ArrayList<Commenti>();
    /**Instantiates a type Commenti object*/
    private Commenti com = new Commenti();
    /**Instantiates a type Dettagli object*/
    private Dettagli d = new Dettagli();
    /**Sets the User to current*/
    private User u = GlobalsVariables.currentUser;
    /**Useful ArrayList of type CanzoneEvaluation objects */
    private ArrayList<CanzoneEvaluation> listValutazioni = new ArrayList<CanzoneEvaluation>();
    /**Instantiates a type CanzoneEvaluation object*/
    private CanzoneEvaluation valutazioneCanzone = new CanzoneEvaluation();
    /**Instantiates a type ValutazioneUtente object*/
    private ValutazioneUtente valutazioneUtente = new ValutazioneUtente();
    /**Useful ArrayList of type ChoiceBox<Integer> objects */
    private ArrayList<ChoiceBox<Integer>> list = new ArrayList<ChoiceBox<Integer>>();
    /**Useful ArrayList of type Emozione objects */
    private ArrayList<Emozione> emList = GlobalsVariables.getEmotions();
    /**Instantiates a type Canzone object to null*/
    Canzone c = null;

    /**Initializes the fxml file*/
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

    /**
     * Sets the song to the current one
     * @param canzone type Canzone object selected from a TableView
     */
    public void getCanzone(Canzone canzone) {
        c = canzone;

    }

    /**Sets the graphic of the song window */
    public void setCanzone() {

        Info.setText(c.getAutore() + " • " + c.getAlbum()+ " • " + c.getAnno() + " • " + c.getDurata() + " min");
        titolo.setText(c.getTitolo());
        if (valutazioneCanzone.getValutazione().size() == 0 || valutazioneCanzone.getValutazione() == null) {
            Data.setText("Numero Valutazioni : 0");

        } else {
            Data.setText("Numero Valutazioni : " + valutazioneCanzone.getValutazione().size());
        }

    }

    /**Sets the graphic with the fxml elements ChoiceBox for the evaluation of the emotions*/
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
    
    /**
     * Sets the fxml elements ChoiceBox
     * @param choice ChoiceBox that is going to be set
     */
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

    /**
     * Method that updates the HashMap object with a new value
     * @param e
     * @param i
     * @param map
     */
    private void updateMap(Emozione e, Integer i, HashMap<Emozione, Integer> map) {

        map.put(e, i);

    }

    /**Sets a the current evaluation */
    private void setClassData() {
        if (listValutazioni.contains(new CanzoneEvaluation(c.getIdCanzone(), null))) {
            indxCanzEv = listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null));
            valutazioneCanzone = listValutazioni
                    .get(listValutazioni.indexOf(new CanzoneEvaluation(c.getIdCanzone(), null)));
        } else {
            valutazioneCanzone = new CanzoneEvaluation(c.getIdCanzone(), new ArrayList<ValutazioneUtente>());
        }

    }

    /**
     * Returns if the song has already been evaluated by anyone, the current user or no one. Sets the field valutazioneCanzone based on that
     * @return boolean value true if it has already been evaluated by the current user, false in the other cases
     */
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

    /**
     * Method that initializes a new HashMap with enum class Emotion values as keys and values at 0
     * @return HashMap<Emozione, Integer> with values at 0
     */
    private HashMap<Emozione, Integer> createMap() {

        HashMap<Emozione, Integer> map = new HashMap<Emozione, Integer>();
        for (Emozione e : Emozione.values()) {
            map.put(e, 0);
        }
        return map;
    }
    
    /**
     * Gets the evaluations from the ChoiceBox elements for every enum class Emotion value and adds the total evaluation to Emozioni.dati.txt
     * @param e
     */
    public void inserisciEmozioniBrano(ActionEvent e) {
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

    /**Sets the graphic for the PieChart containing all the evaluations of the song*/
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

    /**
     * Creates the ObservableList object that contains the data that will be shown in the PieChart
     * @return
     */
    private ObservableList<PieChart.Data> createList() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
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

    /**Sets the graphic showing the comments of the song */
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

    /**
     * Returns if the song has been commented by anyone, the current user or no one. Sets the field com on that
     * @return true if the song has been commented by the current user or anyone andthe user is null, false in other cases
     */
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

    /**
     * Gets and adds to the file Commenti.data.txt the new type Commenti object when a button is clicked
     * @param e javafx action event
     */
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

    /**Sets the maximum length of a comment */
    private void setTextField(){
        Pattern pattern = Pattern.compile(".{0,250}");
        TextFormatter<TextField> formatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
    });
    commentField.setTextFormatter(formatter);
    }
}
