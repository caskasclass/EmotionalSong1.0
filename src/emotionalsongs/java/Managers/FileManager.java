package emotionalsongs.java.Managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {


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

  /*private static ArrayList<User> castList(ArrayList<?> al) {

    ArrayList<User> array = new ArrayList<User>();
    for (Object obj : al) {
      if (obj instanceof User) {
        array.add((User) obj);
      }
    }
    return array;
  }*/

}
