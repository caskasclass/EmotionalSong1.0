/**
 * Provides the Classes that represent the main objects of the program
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.util;

import java.util.ArrayList;
import java.util.List;

import emotionalsongs.java.Managers.StyleManager;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


public class GlobalsVariables {

    /**Global fxml element */
    public static BorderPane left_side_bpane;
    /**Global User initialized to null */
    public static User currentUser=null;
    /**Global ArrayList of type Canzone objects */
    public static ArrayList<Canzone> canzoni = new ArrayList<Canzone>();
    /**Global fxml TableView of type Canzone objects */
    public static TableView<Canzone> addedSongs;
    /**Global List of type Canzone objects */
    public static List<Canzone> playlist =  new ArrayList<>();
    /**Global type Playlist object */
    public static Playlist plist;
    /**Global type String object representing name of a playlist*/
    public static String  PlaylistName ="";
    /**Global type String object representing image of a playlist*/
    public static String  PlaylistImg ="";
    /**Global type StyleManager object */
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

