
package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a Canzone object.
 * @author Beatrice Bastianello
 * @author Nazar Viytyuk
 */

public class Canzone implements Serializable {

    private static String PATH = "./DATA/Canzoni.dati.txt";
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
     * @return current Id of this object
     */
    public String getIdCanzone() {
        return this.IdCanzone;
    }

    /**
     * Returns the title of the Canzone object
     * @return current title of this object
     */
    public String getTitolo() {
        return this.Titolo;
    }

    /**
     * Returns the author of the Canzone object
     * @return current author of this object
     */
    public String getAutore() {
        return this.Autore;
    }

    /**
     * Returns the year of the Canzone object
     * @return current year of this object
     */
    public String getAnno() {
        Calendar date = Calendar.getInstance();
        date.setTime(Anno);
        return Integer.toString(date.get(Calendar.YEAR));
    }

    /**
     * Returns the duration of the Canzone object
     * @return current duration of this object
     */
    public String getDurata() {
        Double dur = this.Duration_ms / 60000;
        String duration = String.format("%.2f", dur);
        duration = duration.replace('.', ':');
        return duration;
    }

    /**
     * Returns the album of the Canzone object
     * @return current album of this object
     */
    public String getAlbum() {
        return this.Album;
    }

}
