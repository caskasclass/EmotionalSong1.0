package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.HashMap;

public class ValutazioneUtente implements Serializable{
    
    private HashMap<Emozione,Integer> Values;
    private String IdUtente;

    public ValutazioneUtente(HashMap<Emozione,Integer> v, String id){
        this.Values = v;
        this.IdUtente = id;
    }
    public ValutazioneUtente(){
        
    }

    public HashMap<Emozione,Integer> getValutazione(){
        return Values;
    }

    public String getIdUtente(){
        return IdUtente;
    }

    @Override
    public boolean equals(Object o){
        
        ValutazioneUtente tmp = (ValutazioneUtente)o;
        if(this.IdUtente.equals(tmp.IdUtente)){
            return true;
        }
        return false;
    }
}
