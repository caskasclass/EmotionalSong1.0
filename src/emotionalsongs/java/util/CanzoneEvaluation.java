/**
 * Provides the Classes that represent the main objects of the program
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an object CanzoneEvaluation. 
 * It has the Id of a song and an array of its evaluations by the different users,
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class CanzoneEvaluation implements Serializable {

    /** Represents the id of the song */
    private String IdCanzone;
    /** Represents the evaluations by the different users of the song */
    private ArrayList<ValutazioneUtente> Value;

    /**
     * Returns an object of type CanzoneEvaluation.
     * @param id the Id of the song
     * @param v the evaluations by the different users of the song
     */
    public CanzoneEvaluation(String id, ArrayList<ValutazioneUtente> v) {

        this.IdCanzone = id;
        this.Value = v;

    }

    /**
     * Returns an object of type CanzoneEvaluation with all null fields.
     */
    public CanzoneEvaluation() {

    }
    
    /**
     * Returns the Id of the type object CanzoneEvaluation.
     * @return Current Id of the type object CanzoneEvaluation
     */
    public String getIdCanzone() {
        return IdCanzone;
    }

    /**
     * Returns the evaluations by the different users of the type object CanzoneEvaluation.
     * @return Current evaluations by the different users of the type object CanzoneEvaluation
     */
    public ArrayList<ValutazioneUtente> getValutazione() {
        return Value;
    }

    /**
     * Adds an evaluation to the type object CanzoneEvaluation
     * @param e new evaluation of the type ValutazioneUtente
     */
    public void addEvaluation(ValutazioneUtente e){
        Value.add(e);
    }

    /**
     * Compares CanzoneEvaluation objects based on fields IdCanzone 
     */
    @Override
    public boolean equals(Object o) {
        CanzoneEvaluation tmp = (CanzoneEvaluation) o;
        if (this.IdCanzone.equals(tmp.IdCanzone)) {
            return true;
        }
        return false;
    }

}
