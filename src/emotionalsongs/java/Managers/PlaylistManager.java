/**
 * Provides the Classes necessary to 
 * manage the files that are not java files
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.Managers;

import java.util.ArrayList;

import emotionalsongs.java.util.Playlist;

/**
 * This Class manages the playlists in the application (type Playlist).
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class PlaylistManager extends FileManager {

    /**Path to the file containing the playlists (type Playlist) */
    final static String PATH="./DATA/Playlist.dati.txt";
    
    /**
    * Imports and returns the playlists (type Playlist) in the file Playlist.dati.txt as an ArrayList
    * @return ArrayList of type Playlist objects
    */
    public static ArrayList<Playlist> readPlaylist(){

        Object obj= readData(PATH);
        ArrayList<Playlist> plays = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            plays = castList(al);
          }
        return plays;
    }

    /**
    * Export the playlists (type Playlist) to the file Playlist.dati.txt
    * @param emos ArrayList of type Playlist objects
    */
    public static void getPlaylist(ArrayList<Playlist> plays){

        getData(plays, PATH);
    }

    /**
    * Casts arrays from type Object to type Playlist
    * @param al generic arraylist
    * @return ArrayList of type Playlist
    */
    private static ArrayList<Playlist> castList(ArrayList<?> al) {

        ArrayList<Playlist> array = new ArrayList<Playlist>();
        for (Object obj : al) {
          if (obj instanceof Playlist) {
            array.add((Playlist) obj);
          }
        }
        return array;
      }
}
