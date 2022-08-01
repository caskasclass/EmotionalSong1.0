package emotionalsongs.java.util;

import java.util.ArrayList;

import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class GlobalsVariables {

    public static BorderPane left_side_bpane;


// queste vanno azzerate quando si esce dall'app + quadno si crea (si pigia il bottone Salva) la playlist 
    public static ArrayList<Canzone> canzoni = new ArrayList<Canzone>();
    public static TableView<Canzone> addedSongs;
    public static String  PlaylistName ="";
    public static String  PlaylistImg ="";


    public static void clearSession(){
        canzoni.clear();
        PlaylistImg = "";
        PlaylistName = "";
    }
    
}

