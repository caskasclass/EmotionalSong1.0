package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import emotionalsongs.java.Managers.FileManager;

/**
 * Represents a User object.
 * @author Beatrice Bastianello
 * @author Nazar Viytyuk
 */
public class User implements Serializable {
    private static String PATH = "./DATA/UtentiRegistrati.dati.txt";

    private String userId;
    private String username;
    private String password;
    private String email;
    private String nomecognome;
    private String codiceFisc;
    private String indirizzo;

    /**
     * Returns an object of type User.
     * @param u the username of the User object
     * @param p the password of the User object
     * @param e the email of the User object
     * @param nc the name and surname of the User object
     * @param cf the codice fiscale of the User object
     * @param ind the address of the User object
     */
    public User(String u, String p, String e, String nc, String cf, String ind) {
        userId = setId();
        username = u;
        password = p;
        email = e;
        nomecognome = nc;
        codiceFisc = cf;
        indirizzo = ind;
    }

    /**
     * Returns a random Id of the type User object
     * @return random Id of the type User object
     */
    private String setId() {
        boolean unique = false;
        String id = "";
        // settare id controlland che non sia gia esistente
        ArrayList<User> list = null;
        Object obj = FileManager.readData(PATH);
        if (obj != null) {
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> al = (ArrayList<?>) obj;
                list = castList(al);
            }
            do {
                id = UUID.randomUUID().toString();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).userId.equals(id)) {
                        break;
                    }
                    if (i == list.size() - 1 && !(list.get(i).userId.equals(id))) {
                        unique = true;
                    }
                }
            } while (!unique);
        } else {
            id = UUID.randomUUID().toString();
        }

        return id;
    }
    /**
     * Casts arrays from type Object to type User
     * @param al generic arraylist
     * @return ArrayList of type User
     */
    private static ArrayList<User> castList(ArrayList<?> al) {

        ArrayList<User> array = new ArrayList<User>();
        for (Object obj : al) {
            if (obj instanceof User) {
                array.add((User) obj);
            }
        }
        return array;
    }

    /**
     * Returns username of type User object
     * @return Current username of type User object
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns name and surname of type User object
     * @return Current nomecognome of type User object
     */
    public String getName() {
        return this.nomecognome;
    }

    /**
     * Returns Id of type User object
     * @return Current Id of type User object
     */
    public String getId() {
        return this.userId;
    }

    /**
     * Returns codice fiscale of type User object
     * @return Current codiceFisc of type User object
     */
    public String getCodiceFisc() {
        return this.codiceFisc;
    }

    /**
     * Returns address of type User object
     * @return Current address of type User object
     */
    public String getIndirizzo() {
        return this.indirizzo;
    }

    
    @Override
    public boolean equals(Object o) {
        User other = (User) o;
        if (this.username.equals(other.username) && this.password.equals(other.password)) 
                 {
            return true;
        } else {
            return false;
        }
    }

}
