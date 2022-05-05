package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable {

    private String idUser;
    private String nomePlaylist;
    private ArrayList<String> listaCanzoni;

    public Playlist(String np, String userId, String idCanzone) {
        idUser = userId;
        nomePlaylist = np;
        listaCanzoni.add(idCanzone);
    }


    public void addCanzone(String idCanzone) {
        listaCanzoni.add(idCanzone);
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public String getOwner() {
        return idUser;
    }

    public ArrayList<String> getCanzoni() {
        return listaCanzoni;
    }
 

    //creare print playlist
    /*public String printPlaylist(String s){
        for (String nome : listaCanzoni) {
           println()
            
        }
    }*/

}
