/**
 * Provides the Classes necessary to 
 * manage the files that are not java files
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.Managers;

import java.io.File;
import java.util.ArrayList;

import emotionalsongs.java.util.Commenti;

/**
 * This Class manages the comment in the application (type Commenti).
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class CommentiManager extends FileManager {

    /**Path to the file containing the comments (type Commenti) */
    final static String PATH="./DATA/Commenti.dati.txt";

    /**
     * Imports and returns the comments (type Commenti) in the file Commenti.dati.txt as an ArrayList
     * @return ArrayList of type Commenti objects
     */
    public static ArrayList<Commenti> readCommenti(){

        Object obj= readData(PATH);
        ArrayList<Commenti> emos = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            emos = castList(al);
          }
        return emos;
    }

    /**
     * Export the comments (type Commenti) to the file Commenti.dati.txt
     * @param emos ArrayList of type Commenti objects
     */
    public static void getEmozioni(ArrayList<Commenti> emos){

        getData(emos, PATH);
    }
  
    /**
     * Casts arrays from type Object to type Commenti
     * @param al generic arraylist
     * @return ArrayList of type Commenti
     */
    private static ArrayList<Commenti> castList(ArrayList<?> al) {
  
        ArrayList<Commenti> array = new ArrayList<Commenti>();
        for (Object obj : al) {
          if (obj instanceof Commenti) {
            array.add((Commenti) obj);
          }
        }
        return array;
      }
  
      /**
       * Checks if the file is empty
       * @return boolean value true if it's empty, false if it's not
       */
      public static boolean checkLengthFile(){
        if(new File(PATH).length() == 0){
          return true;
        }
        return false;
      }
}
