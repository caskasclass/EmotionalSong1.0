package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable {

    private String idUser;
    private String nomePlaylist;
    private ArrayList<Integer> listaCanzoni;

    public Playlist(String np, String userId, Integer idCanzone) {
        idUser = userId;
        nomePlaylist = np;
        listaCanzoni.add(idCanzone);
    }


    public void addCanzone(Integer idCanzone) {
        listaCanzoni.add(idCanzone);
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public String getOwner() {
        return idUser;
    }

    public ArrayList<Integer> getCanzoni() {
        return listaCanzoni;
    }

}
