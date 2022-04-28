package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import emotionalsongs.java.Managers.FileManager;

public class Canzone implements Serializable {
    private static String PATH = "./src/emotionalsongs/resources/DataBaseBrutto/Canzoni.dati.txt";
    private String IdCanzone;
    private String Titolo;
    private String Autore;
    private Integer Anno;
    private double Durata;
    private String Album;
    private String Genere;

    public Canzone(String Titolo, String Autore, Integer Anno, Double Durata, String Album, String Genere) {

        this.IdCanzone = setIdCanzone();
        this.Titolo = Titolo;
        this.Autore = Autore;
        this.Anno = Anno;
        this.Durata = Durata;
        this.Album = Album;
        this.Genere = Genere;
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

    public Integer getAnno() {
        return this.Anno;
    }

    public Double getDurata() {
        return this.Durata;
    }

    public String getAlbum() {
        return this.Album;
    }

    public String getGenere() {
        return this.Genere;
    }

    private String setIdCanzone() {
        boolean unique = false;
        String id = "";
        // settare id controlland che non sia gia esistente
        ArrayList<Canzone> list = null;
        Object obj = FileManager.readData(PATH);
        if(obj!=null)
        {
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> al = (ArrayList<?>) obj;
                list = castList(al);
            }
            do {
                id = UUID.randomUUID().toString();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).IdCanzone.equals(id)) {
                        break;
                    }
                    if (i == list.size() - 1 && !(list.get(i).IdCanzone.equals(id))) {
                        unique = true;
                    }
                }
            } while (!unique);
        }else{
            id = UUID.randomUUID().toString();
        }
      

        return id;
    }

    private static ArrayList<Canzone> castList(ArrayList<?> al) {

        ArrayList<Canzone> array = new ArrayList<Canzone>();
        for (Object obj : al) {
            if (obj instanceof Canzone) {
                array.add((Canzone) obj);
            }
        }
        return array;
    }
}
