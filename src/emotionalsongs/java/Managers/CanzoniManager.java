package emotionalsongs.java.Managers;

import java.util.ArrayList;

import emotionalsongs.java.util.Canzone;

public class CanzoniManager extends FileManager {
    final static String PATH="./DATA/Canzoni.dati.txt";
    
    
    public static ArrayList<Canzone> readCanzoni(){

        Object obj= readData(PATH);
        ArrayList<Canzone> canzoni = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            canzoni = castList(al);
          }
        return canzoni;
    }

    public static void getCanzoni(ArrayList<Canzone> canzoni){

        getData(canzoni, PATH);
    }

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
