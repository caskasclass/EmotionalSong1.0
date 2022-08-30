/*Beatrice Bastianello, matricola 751864
Nazar Viytyuk, matricola 748964
sede VA*/

package emotionalsongs.java.util;

import java.util.ArrayList;
import java.util.List;

import emotionalsongs.java.Managers.StyleManager;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * Useful Class that creates static variables and methods that will be used in the program.   
 * @author Beatrice Bastianello
 * @author Nazar Viytyuk
 */
public class GlobalsVariables {

    public static BorderPane left_side_bpane;
    public static User currentUser=null;
    public static ArrayList<Canzone> canzoni = new ArrayList<Canzone>();
    public static TableView<Canzone> addedSongs;
    public static List<Canzone> playlist =  new ArrayList<>();
    public static Playlist plist;
    public static String  PlaylistName ="";
    public static String  PlaylistImg ="";
    public static StyleManager style= new StyleManager();

    /**Removes all the songs (type Canzone)*/
    public static void cleardeleteFromPlaylistSessio()
    {
       playlist.clear();
    }

    /**Removes all the songs (type Canzone)*/
    public static void clearSession(){
        canzoni.clear();
        PlaylistImg = "";
        PlaylistName = "";
    }

    /**Sets the user null*/
    public static void exitSession(){
        currentUser= null;
    }

    /**Creates an ArrayList with all the emotions from the enum class Emozione*/
    public static ArrayList<Emozione> getEmotions(){
        ArrayList<Emozione> emList = new ArrayList<Emozione>();
        for(Emozione e : Emozione.values()){
            emList.add(e);
        }

        return emList;
    }
    
}

