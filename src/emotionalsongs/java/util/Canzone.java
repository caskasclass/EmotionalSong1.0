/**
 * Provides the Classes that represent the main objects of the program
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a Canzone object.
 * It has the info about the song like title, author ecc..
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */

public class Canzone implements Serializable {

    /** Useful to manage object type Canzone */
    private static String PATH = "./DATA/Canzoni.dati.txt";
    /** Represents the ID of the song */
    private String IdCanzone;
    /** Represents the title of the song */
    private String Titolo;
    /** Represents the author of the song */
    private String Autore;
    /** Useful to manage object type Canzone */
    private Double DanceAbility;
    /** Useful to manage object type Canzone */
    private Double Energy;
    /** Useful to manage object type Canzone */
    private Double Loudness;
    /** Useful to manage object type Canzone */
    private Double Speechiness;
    /** Useful to manage object type Canzone */
    private Boolean Mode;
    /** Useful to manage object type Canzone */
    private Double Liveness;
    /** Useful to manage object type Canzone */
    private Double Tempo;
    /** Represents the year of the song */  
    private Date Anno;
    /** Represents the duration of the song */
    private Double Duration_ms;
    /** Represents the album containing the song */
    private String Album;
    /** Useful to manage object type Canzone */
    private Integer Time_signature;

    /**
     * Returns an object of type Canzone.
     * @param songId the Id of the Canzone object
     * @param titolo the title of the Canzone object
     * @param autore the author of the Canzone object
     * @param release_date the release_date of the Canzone object
     * @param danceability the danceability of the Canzone object
     * @param energy the energy of the Canzone object
     * @param loudness the loudness of the Canzone object
     * @param speechiness the speechiness of the Canzone object
     * @param mode the mode of the Canzone object
     * @param liveness the liveness of the Canzone object
     * @param tempo the tempo of the Canzone object
     * @param durata the duration of the Canzone object
     * @param album the album of the Canzone object
     * @param time_signature the time signature of the Canzone object
     */
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

    /**
     * Returns the Id of the Canzone object
     * @return Current Id of this Canzone object
     */
    public String getIdCanzone() {
        return this.IdCanzone;
    }

    /**
     * Returns the title of the Canzone object
     * @return Current title of this Canzone object
     */
    public String getTitolo() {
        return this.Titolo;
    }

    /**
     * Returns the author of the Canzone object
     * @return Current author of this Canzone object
     */
    public String getAutore() {
        return this.Autore;
    }

    /**
     * Returns the year of the Canzone object
     * @return Current year of this Canzone object
     */
    public String getAnno() {
        Calendar date = Calendar.getInstance();
        date.setTime(Anno);
        return Integer.toString(date.get(Calendar.YEAR));
    }

    /**
     * Returns the duration of the Canzone object
     * @return Current duration of this Canzone object
     */
    public String getDurata() {
        Double dur = this.Duration_ms / 60000;
        String duration = String.format("%.2f", dur);
        duration = duration.replace('.', ':');
        return duration;
    }

    /**
     * Returns the album of the Canzone object
     * @return Current album of this Canzone object
     */
    public String getAlbum() {
        return this.Album;
    }

}
