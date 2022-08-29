package emotionalsongs.java.Managers;

import java.io.File;
import java.util.ArrayList;

import emotionalsongs.java.util.Commenti;

public class CommentiManager extends FileManager {
    final static String PATH="./src/emotionalsongs/resources/DataBaseBrutto/Commenti.dati.txt";

    public static ArrayList<Commenti> readCommenti(){

        Object obj= readData(PATH);
        ArrayList<Commenti> emos = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            emos = castList(al);
          }
        return emos;
    }
    public static void getEmozioni(ArrayList<Commenti> emos){

        getData(emos, PATH);
    }
  
    private static ArrayList<Commenti> castList(ArrayList<?> al) {
  
        ArrayList<Commenti> array = new ArrayList<Commenti>();
        for (Object obj : al) {
          if (obj instanceof Commenti) {
            array.add((Commenti) obj);
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
