package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Playlist implements Serializable {

    private String idPlaylist;
    private String idUser;
    private String png;
    private String nomePlaylist;
    private ArrayList<Canzone> listaCanzoni;

    public Playlist(String np, String img, String userId, ArrayList<Canzone> idCanzoni) {
        idUser = userId;
        png = img;
        nomePlaylist = np;
        listaCanzoni = idCanzoni;
        idPlaylist = UUID.randomUUID().toString();
    }

    public String getIDPlaylist() {
        return idPlaylist;
    }

    public String getPng() {
        return png;
    }

    public void addCanzone(Canzone idCanzone) {
        listaCanzoni.add(idCanzone);
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public String getOwner() {
        return idUser;
    }

    public ArrayList<Canzone> getCanzoni() {
        return listaCanzoni;
    }

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
