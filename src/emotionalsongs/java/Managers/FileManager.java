/**
 * Provides the Classes necessary to 
 * manage the files that are not java files
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.Managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This Class manages the txt files in the application.
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class FileManager {


  /**
   * Import the data from the file which has this path
   * @param PATH path to the file
   * @return ArrayList of generic objects
   */
  public static Object readData(String PATH) {
    Object obj = null;
    try {
      FileInputStream fileInputStream = new FileInputStream(PATH);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      obj = objectInputStream.readObject();
      objectInputStream.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException IOe) {
      IOe.printStackTrace();
    }
    return obj;

  }

  /**
   * Export the data to the file which has this path
   * @param data ArrayList of generic objects
   * @param PATH path to the file
   */
  public static void getData(ArrayList<?> data,String PATH) {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(PATH);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(data);
      objectOutputStream.flush();
      objectOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
