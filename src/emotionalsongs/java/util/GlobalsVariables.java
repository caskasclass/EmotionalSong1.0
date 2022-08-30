package emotionalsongs.java.util;

import java.util.ArrayList;
import java.util.List;

import emotionalsongs.java.Managers.StyleManager;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class GlobalsVariables {

    public static BorderPane left_side_bpane;

    public static User currentUser;

// queste vanno azzerate quando si esce dall'app + quadno si crea (si pigia il bottone Salva) la playlist 
    public static ArrayList<Canzone> canzoni = new ArrayList<Canzone>();
    public static TableView<Canzone> addedSongs;
    public static List<Canzone> playlist =  new ArrayList<>();
    public static Playlist plist;
    

    
    public static String  PlaylistName ="";
    public static String  PlaylistImg ="";
    public static StyleManager style= new StyleManager();


    public static void cleardeleteFromPlaylistSessio()
    {
       playlist.clear();
    }
    public static void clearSession(){
        canzoni.clear();
        PlaylistImg = "";
        PlaylistName = "";
    }

    public static void exitSession(){
        currentUser= null;
    }

    public static ArrayList<Emozione> getEmotions(){
        ArrayList<Emozione> emList = new ArrayList<Emozione>();
        for(Emozione e : Emozione.values()){
            emList.add(e);
        }

        return emList;
    }
    
}

