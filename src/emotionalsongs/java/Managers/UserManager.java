/**
 * Provides the Classes necessary to 
 * manage the files that are not java files
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.Managers;

import java.util.ArrayList;

import emotionalsongs.java.util.User;

/**
 * This Class manages the users in the application (type Playlist).
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class UserManager extends FileManager {
    
  /**Path to the file containing the users (type User) */
    final static String PATH="./DATA/UtentiRegistrati.dati.txt";
    
    /**
    * Imports and returns the users (type User) in the file User.dati.txt as an ArrayList
    * @return ArrayList of type User objects
    */
    public static ArrayList<User> readUsers(){

        Object obj= readData(PATH);
        ArrayList<User> users = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            users = castList(al);
          }
        return users;
    }

    /**
    * Export the users (type User) to the file User.dati.txt
    * @param emos ArrayList of type User objects
    */
    public static void getUsers(ArrayList<User> users){

        getData(users, PATH);
    }

    /**
    * Casts arrays from type Object to type User
    * @param al generic arraylist
    * @return ArrayList of type User
    */
    public static ArrayList<User> castList(ArrayList<?> al) {

        ArrayList<User> array = new ArrayList<User>();
        for (Object obj : al) {
            if (obj instanceof User) {
                array.add((User) obj);
            }
        }
        return array;
    }
}
