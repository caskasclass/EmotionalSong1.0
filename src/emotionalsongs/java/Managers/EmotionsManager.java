package emotionalsongs.java.Managers;

import java.io.File;
import java.util.ArrayList;

import emotionalsongs.java.util.CanzoneEvaluation;

/**
 * This Class manages the evaluations in the application (type CanzoneEvaluation).
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class EmotionsManager extends FileManager {

  /**Path to the file containing the evaluations (type CanzoneEvaluation) */
  final static String PATH="./DATA/Emozioni.dati.txt";
    
  /**
  * Imports and returns the evaluations (type CanzoneEvaluation) in the file CanzoneEvaluation.dati.txt as an ArrayList
  * @return ArrayList of type CanzoneEvaluation objects
  */
  public static ArrayList<CanzoneEvaluation> readEmozioni(){

      Object obj= readData(PATH);
      ArrayList<CanzoneEvaluation> emos = new ArrayList<>();
      if (obj instanceof ArrayList<?>) {
          ArrayList<?> al = (ArrayList<?>) obj;
          emos = castList(al);
        }
      return emos;
  }

  /**
  * Export the evaluations (type CanzoneEvaluation) to the file CanzoneEvaluation.dati.txt
  * @param emos ArrayList of type CanzoneEvaluation objects
  */
  public static void getEmozioni(ArrayList<CanzoneEvaluation> emos){

      getData(emos, PATH);
  }

  /**
    * Casts arrays from type Object to type CanzoneEvaluation
    * @param al generic arraylist
    * @return ArrayList of type CanzoneEvaluation
    */
  private static ArrayList<CanzoneEvaluation> castList(ArrayList<?> al) {

      ArrayList<CanzoneEvaluation> array = new ArrayList<CanzoneEvaluation>();
      for (Object obj : al) {
        if (obj instanceof CanzoneEvaluation) {
          array.add((CanzoneEvaluation) obj);
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
