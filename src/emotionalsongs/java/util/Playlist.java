/**
 * Provides the Classes that represent the main objects of the program
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents an object Playlist. 
 * It has its Id, the Id of the user who created it, an icon, its name and a list of songs
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class Playlist implements Serializable {

    /**Represents the Id of the playlist */
    private String idPlaylist;
    /**Represents the Id of the user who created the playlist */
    private String idUser;
    /**Represents the icon of the playlist */
    private String png;
    /**Represents the name of the playlist */
    private String nomePlaylist;
    /**Represents the list of songs of the playlist */
    private ArrayList<Canzone> listaCanzoni;

    /**
     * Returns an object of the type Playlist.
     * @param np the name of the playlist
     * @param img the icon of the playlist
     * @param userId the Id of the user who created the playlist
     * @param idCanzoni the list of songs of the playlist
     */
    public Playlist(String np, String img, String userId, ArrayList<Canzone> idCanzoni) {
        idUser = userId;
        png = img;
        nomePlaylist = np;
        listaCanzoni = idCanzoni;
        idPlaylist = UUID.randomUUID().toString();
    }

    /**
     * Returns the Id of the Playlist object
     * @return Current Id of the Playlist object
     */
    public String getIDPlaylist() {
        return idPlaylist;
    }

    /**
     * Returns the icon of the Playlist object
     * @return Current icon of the Playlist object
     */
    public String getPng() {
        return png;
    }

    /**
     * Adds a song to the list of songs of the Playlist object
     */
    public void addCanzone(Canzone idCanzone) {
        listaCanzoni.add(idCanzone);
    }

    /**
     * Returns the name of the Playlist object
     * @return Current name of the Playlist object
     */
    public String getNomePlaylist() {
        return nomePlaylist;
    }

    /**
     * Returns the Id of the user of the Playlist object
     * @return Current Id of the user of the Playlist object
     */
    public String getOwner() {
        return idUser;
    }

    /**
     * Returns the list of songs of the Playlist object
     * @return Current list of songs Playlist object
     */
    public ArrayList<Canzone> getCanzoni() {
        return listaCanzoni;
    }

    /**
     * Compares Playlist objects based on fields idPlaylist and idUser
     */
    @Override
    public boolean equals(Object o){
        Playlist p = (Playlist) o;
        if(this.idPlaylist.equals(p.idPlaylist)&&this.idUser.equals(p.idUser)){
            return true;
        }
        else{
            return false;
        }
    }

}

