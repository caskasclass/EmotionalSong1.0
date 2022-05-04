package emotionalsongs.java.Managers;

import java.util.ArrayList;

import emotionalsongs.java.util.Emozione;

public class EmotionsManager extends FileManager {
  final static String PATH="./src/emotionalsongs/resources/DataBaseBrutto/Emozioni.dati.txt";
    
    
  public static ArrayList<Emozione> readEmozioni(){

      Object obj= readData(PATH);
      ArrayList<Emozione> emos = new ArrayList<>();
      if (obj instanceof ArrayList<?>) {
          ArrayList<?> al = (ArrayList<?>) obj;
          emos = castList(al);
        }
      return emos;
  }

  public static void getEmozioni(ArrayList<Emozione> emos){

      getData(emos, PATH);
  }

  private static ArrayList<Emozione> castList(ArrayList<?> al) {

      ArrayList<Emozione> array = new ArrayList<Emozione>();
      for (Object obj : al) {
        if (obj instanceof Emozione) {
          array.add((Emozione) obj);
        }
      }
      return array;
    }
}
