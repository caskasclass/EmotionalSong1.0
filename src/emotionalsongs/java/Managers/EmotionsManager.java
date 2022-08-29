package emotionalsongs.java.Managers;

import java.io.File;
import java.util.ArrayList;

import emotionalsongs.java.util.CanzoneEvaluation;

public class EmotionsManager extends FileManager {
  final static String PATH="./src/emotionalsongs/resources/database/Emozioni.dati.txt";
    
    
  public static ArrayList<CanzoneEvaluation> readEmozioni(){

      Object obj= readData(PATH);
      ArrayList<CanzoneEvaluation> emos = new ArrayList<>();
      if (obj instanceof ArrayList<?>) {
          ArrayList<?> al = (ArrayList<?>) obj;
          emos = castList(al);
        }
      return emos;
  }

  public static void getEmozioni(ArrayList<CanzoneEvaluation> emos){

      getData(emos, PATH);
  }

  private static ArrayList<CanzoneEvaluation> castList(ArrayList<?> al) {

      ArrayList<CanzoneEvaluation> array = new ArrayList<CanzoneEvaluation>();
      for (Object obj : al) {
        if (obj instanceof CanzoneEvaluation) {
          array.add((CanzoneEvaluation) obj);
        }
      }
      return array;
    }

    public static boolean checkLengthFile(){
      if(new File(PATH).length() == 0){
        return true;
      }
      return false;
    }
    
}
