package emotionalsongs.java.util;

import java.io.Serializable;

public class EmozioneAssociativa implements Serializable{
    private Integer Voto;
    private Emozione emozione;
    private String idCanzone;
    private String idUtente;

    public EmozioneAssociativa(Integer v, Emozione em,String idCanz,String idUten)
    {
        Voto = v;
        emozione = em;
        idCanzone = idCanz;
        idUtente = idUten;
    }
    public Integer getVoto()
    {
        return Voto;
    }
    public Emozione getEmozione(){
        return emozione;
    }
    public String getIdCanzone(){
        return idCanzone;
    }
    public String getIdUtente(){
        return idUtente;
    }
}
