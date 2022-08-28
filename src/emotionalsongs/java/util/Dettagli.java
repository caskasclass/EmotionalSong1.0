package emotionalsongs.java.util;

import java.io.Serializable;

public class Dettagli implements Serializable{

    private User Utente;
    private String commento;
    
    
    public Dettagli(User Id,String com){
        this.Utente = Id;
        this.commento= com;
    }

    public Dettagli(){
        
    }

    public User getIdUtente(){
        return Utente;
    }

    public String getCommento(){
        return commento;
    }

    @Override
    public boolean equals(Object o) {
        Dettagli tmp = (Dettagli) o;
        if (this.Utente.equals(tmp.Utente)) {
            return true;
        }
        return false;
    }
    
}
