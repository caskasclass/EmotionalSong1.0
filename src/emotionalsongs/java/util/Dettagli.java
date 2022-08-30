/**
 * Rappresenta un oggetto Dettagli. 
 * Racchiude l'id di un oggetto User e il commento da esso scritto.
 * @author Beatrice Bastianello
 * @author Nazar Viytyuk
 */
package emotionalsongs.java.util;

import java.io.Serializable;

public class Dettagli implements Serializable{

    private User user;
    private String commento;
    
    
    public Dettagli(User us,String com){
        this.user = us;
        this.commento= com;
    }

    public Dettagli(){
        
    }

    public User getUser(){
        return user;
    }

    public String getCommento(){
        return commento;
    }

    @Override
    public boolean equals(Object o) {
        Dettagli tmp = (Dettagli) o;
        if (this.user.equals(tmp.user)) {
            return true;
        }
        return false;
    }
    
}
