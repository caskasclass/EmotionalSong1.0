package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class Canzone implements Serializable {

    private static String PATH = "./src/emotionalsongs/resources/database/Canzoni.dati.txt";
    private String IdCanzone;// ok
    private String Titolo;// ok
    private String Autore;// ok
    private Double DanceAbility;// ok
    private Double Energy;// ok
    private Double Loudness;// ok
    private Double Speechiness;// ok
    private Boolean Mode;// ok
    private Double Liveness;// ok
    private Double Tempo;// ok
    private Date Anno;// ok
    private Double Duration_ms;// ok
    private String Album;// ok
    private Integer Time_signature;// ok

    public Canzone(String songId, String titolo, String autore, Date release_date, Double danceability, Double energy,
            Double loudness, Double speechiness, Boolean mode, Double liveness, Double tempo, Double durata,
            String album, Integer time_signature) {

        this.IdCanzone = songId;
        this.Titolo = titolo;
        this.Autore = autore;
        this.DanceAbility = danceability;
        this.Energy = energy;
        this.Loudness = loudness;
        this.Speechiness = speechiness;
        this.Mode = mode;
        this.Liveness = liveness;
        this.Tempo = tempo;
        this.Anno = release_date;
        this.Duration_ms = durata;
        this.Album = album;
        this.Time_signature = time_signature;
    }

    public String getIdCanzone() {
        return this.IdCanzone;
    }

    public String getTitolo() {
        return this.Titolo;
    }

    public String getAutore() {
        return this.Autore;
    }

    public String getAnno() {
        Calendar date = Calendar.getInstance();
        date.setTime(Anno);
        return Integer.toString(date.get(Calendar.YEAR));
    }

    public String getDurata() {
        Double dur = this.Duration_ms / 60000;
        String duration = String.format("%.2f", dur);
        duration = duration.replace('.', ':');
        return duration;
    }

    public String getAlbum() {
        return this.Album;
    }

}
