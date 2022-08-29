package emotionalsongs.java.Managers;

import java.util.ArrayList;

import emotionalsongs.java.util.Playlist;

public class PlaylistManager extends FileManager {
    final static String PATH="./src/emotionalsongs/resources/database/Playlist.dati.txt";
    
    
    public static ArrayList<Playlist> readPlaylist(){

        Object obj= readData(PATH);
        ArrayList<Playlist> plays = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            plays = castList(al);
          }
        return plays;
    }

    public static void getPlaylist(ArrayList<Playlist> plays){

        getData(plays, PATH);
    }

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
