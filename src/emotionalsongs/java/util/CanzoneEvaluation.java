package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.control.Label;

public class CanzoneEvaluation implements Serializable {

    private String IdCanzone;
    private ArrayList<ValutazioneUtente> Value;

    public CanzoneEvaluation(String id, ArrayList<ValutazioneUtente> v) {

        this.IdCanzone = id;
        this.Value = v;

    }
    public CanzoneEvaluation() {

    }
    

    public String getIdCanzone() {
        return IdCanzone;
    }

    public ArrayList<ValutazioneUtente> getValutazione() {
        return Value;
    }
    public void addEvaluation(ValutazioneUtente e){
        Value.add(e);
        //Integer s= Value.size();
        //l.setText("Numero Valutazioni: " + s.toString());
    }

    @Override
    public boolean equals(Object o) {
        CanzoneEvaluation tmp = (CanzoneEvaluation) o;
        if (this.IdCanzone.equals(tmp.IdCanzone)) {
            return true;
        }
        return false;
    }

}