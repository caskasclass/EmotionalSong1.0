/**
 * Rappresenta un oggetto Commenti. 
 * Racchiude l'id di un oggetto Canzone e i commenti a lui attribuiti tramite array di oggetti Dettagli.
 * @author Beatrice Bastianello
 * @author Nazar Viytyuk
 */
package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;

public class Commenti implements Serializable{

    private String IdCanzone;
    private ArrayList<Dettagli> Details;

    public Commenti(String Id, ArrayList<Dettagli> det){
        this.Details = det;
        this.IdCanzone = Id;
    }

    public Commenti(){

    }

    public String getIdCanzone(){
        return IdCanzone;
    }

    public ArrayList<Dettagli> getDetails(){
        return Details;
    }

    public void addComment(Dettagli e){
        Details.add(e);
    }
    
    @Override
    public boolean equals(Object o) {
        Commenti tmp = (Commenti) o;
        if (this.IdCanzone.equals(tmp.IdCanzone)) {
            return true;
        }
        return false;
    }
    //override equals con ID
}
