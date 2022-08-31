package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an object Commenti. 
 * It has the Id of a song and an array of its comments made by users
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class Commenti implements Serializable{

    /** Represents the id of the song */
    private String IdCanzone;
    /** Represents the comments of the song */
    private ArrayList<Dettagli> Details;

    /**
     * Returns an object of the type Commenti.
     * @param Id the Id of the song of the Commenti Object
     * @param det the comments of the song of the Commenti Object
     */
    public Commenti(String Id, ArrayList<Dettagli> det){
        this.Details = det;
        this.IdCanzone = Id;
    }
    /**
     * Returns an object of the type Commenti with all fields null.
     */
    public Commenti(){

    }

    /**
     * Returns the Id of the song 
     * @return Current Id of the song in which there are the comments
     */
    public String getIdCanzone(){
        return IdCanzone;
    }

    /**
     * Returns the comments of the song 
     * @return Current comments of the song 
     */
    public ArrayList<Dettagli> getDetails(){
        return Details;
    }

    /**
     * Adds a comment of the type Dettagli to the array of comments
     */
    public void addComment(Dettagli e){
        Details.add(e);
    }
    
    /**
     * Compares Commenti objects based on the field IdCanzone 
     */
    @Override
    public boolean equals(Object o) {
        Commenti tmp = (Commenti) o;
        if (this.IdCanzone.equals(tmp.IdCanzone)) {
            return true;
        }
        return false;
    }
}
