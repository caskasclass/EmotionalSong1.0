package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;


public class Playlist implements Serializable {

    private String idPlaylist;
    private String idUser;
    private String png;
    private String nomePlaylist;
    private ArrayList<String> listaCanzoni;

    public Playlist(String np, String img, String userId, ArrayList<String>idCanzoni) {
        idUser = userId;
        png = img;
        nomePlaylist = np;
        listaCanzoni = idCanzoni;
        idPlaylist = UUID.randomUUID().toString();
    }

    public String getIDPlaylist()
    {
        return idPlaylist;
    }


    public String  getPng(){
        return png;
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

}
