package emotionalsongs.java.Managers;

public class PlaylistManager extends FileManager {
    final static String PATH="./src/emotionalsongs/resources/DataBaseBrutto/UtentiRegistrati.dati.txt";
    
    
    /*public static ArrayList<User> readUsers(){

        Object obj= readData(PATH);
        ArrayList<User> users = new ArrayList<>();
        if (obj instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) obj;
            users = castList(al);
          }
        return users;
    }

    public static void getUsers(ArrayList<User> users){

        getData(users, PATH);
    }

    private static ArrayList<User> castList(ArrayList<?> al) {

        ArrayList<User> array = new ArrayList<User>();
        for (Object obj : al) {
          if (obj instanceof User) {
            array.add((User) obj);
          }
        }
        return array;
      }*/
}
