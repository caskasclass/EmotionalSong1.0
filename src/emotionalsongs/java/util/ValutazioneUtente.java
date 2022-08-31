package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Represents an object ValutazioneUtente. 
 * It has the Id of the user who evaluates the song and the evaluation itself
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class ValutazioneUtente implements Serializable{
    
    /**Represents the evaluation of the song. 
     * <p>It's an hashmap that has the values of the enum class Emozione as keys</p> */
    private HashMap<Emozione,Integer> Values;
    /**Represents the Id of the user who evaluates the song */
    private String IdUtente;

    /**
     * Returns the object of type ValutazioneUtente.
     * @param v evaluation of the song
     * @param id of the user who evaluates the song
     */
    public ValutazioneUtente(HashMap<Emozione,Integer> v, String id){
        this.Values = v;
        this.IdUtente = id;
    }

    /**
     * Returns the object of type ValutazioneUtente with all fields null.
     */
    public ValutazioneUtente(){
        
    }

    /**
    * Returns the evaluation of the ValutazioneUtente object
    * @return Current evaluation of the ValutazioneUtente object
    */
    public HashMap<Emozione,Integer> getValutazione(){
        return Values;
    }

    /**
    * Returns the Id of the ValutazioneUtente object
    * @return Current Id of the ValutazioneUtente object
    */
    public String getIdUtente(){
        return IdUtente;
    }

     /**
     * Compares ValutazioneUtente objects based on the field IdUtente 
     */
    @Override
    public boolean equals(Object o){
        
        ValutazioneUtente tmp = (ValutazioneUtente)o;
        if(this.IdUtente.equals(tmp.IdUtente)){
            return true;
        }
        return false;
    }
}
