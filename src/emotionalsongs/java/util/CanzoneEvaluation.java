/**
 * Rappresenta un oggetto CanzoneEvaluation. 
 * Racchiude l'id di un oggetto Canzone e tutte le sue valutazioni tramite array di oggetti ValutazioneUtente.
 * @author Beatrice Bastianello
 * @author Nazar Viytyuk
 */
package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;


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
