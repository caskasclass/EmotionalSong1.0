package emotionalsongs.java.Managers;

import java.util.ArrayList;

import emotionalsongs.java.util.Canzone;

public class CanzoniManager extends FileManager {
    final static String PATH="./src/emotionalsongs/resources/DataBaseBrutto/Canzoni.dati.txt";
    
    
    public static ArrayList<Canzone> readCanzoni(){

        Object obj= readData(PATH);
        ArrayList<Canzone> users = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            users = castList(al);
          }
        return users;
    }

    public static void getCanzoni(ArrayList<Canzone> users){

        getData(users, PATH);
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
