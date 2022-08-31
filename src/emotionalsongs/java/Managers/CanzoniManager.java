/**
 * Provides the Classes necessary to 
 * manage the files that are not java files
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.Managers;

import java.util.ArrayList;

import emotionalsongs.java.util.Canzone;

/**
 * This Class manages the songs in the application (type Canzone).
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class CanzoniManager extends FileManager {

    /**Path to the file containing the songs (type Canzone) */
    final static String PATH="./DATA/Canzoni.dati.txt";
    
    /**
     * Imports and returns the songs (type Canzone) in the file Canzoni.dati.txt as an ArrayList
     * @return ArrayList of type Canzone objects
     */
    public static ArrayList<Canzone> readCanzoni(){

        Object obj= readData(PATH);
        ArrayList<Canzone> canzoni = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            canzoni = castList(al);
          }
        return canzoni;
    }

    /**
     * Export the songs (type Canzone) to the file Canzoni.dati.txt
     * @param canzoni ArrayList of type Canzone objects
     */
    public static void getCanzoni(ArrayList<Canzone> canzoni){

        getData(canzoni, PATH);
    }

    /**
     * Casts arrays from type Object to type Canzone
     * @param al generic arraylist
     * @return ArrayList of type Canzone
     */
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
