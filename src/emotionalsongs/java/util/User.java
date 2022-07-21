package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import emotionalsongs.java.Managers.FileManager;

public class User implements Serializable {
    private static String PATH = "./src/emotionalsongs/resources/DataBaseBrutto/UtentiRegistrati.dati.txt";

    private String userId;
    private String username;
    private String password;
    private String email;
    private String nomecognome;
    private String codiceFisc;
    private String indirizzo;

    public User(String u, String p, String e, String nc, String cf, String ind) {
        userId = setId();
        username = u;
        password = p;
        email = e;
        nomecognome = nc;
        codiceFisc = cf;
        indirizzo = ind;
    }

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

    private static ArrayList<User> castList(ArrayList<?> al) {

        ArrayList<User> array = new ArrayList<User>();
        for (Object obj : al) {
            if (obj instanceof User) {
                array.add((User) obj);
            }
        }
        return array;
    }

    public String printUser() {
        return ("\nUsername : " + username + "\nPassword : " + password + "\nEmail : " + email + "\nId : " + userId);
    }

    // da sviluppare motodi getParametro() perché sono privati.
    public String getUsername() {
        return this.username;
    }
    public String getName() {
        return this.nomecognome;
    }
    public String getCodiceFisc() {
        return this.codiceFisc;
    }
    public String getIndirizzo() {
        return this.indirizzo;
    }

    @Override
    public boolean equals(Object o) {
        User other = (User) o;
        /*
         * if (((this.email == other.email) && (this.password == other.password)) ||
         * ((this.username == other.username) && (this.password == other.password))) {
         * return true;
         * } else {
         * return false;
         * }
         */
        if ((this.email.equals(other.email) && this.password.equals(other.password))
                || (this.username.equals(other.username) && this.password.equals(other.password)) 
                || (this.nomecognome.equals(other.nomecognome) && this.password.equals(other.password))
                || (this.codiceFisc.equals(other.codiceFisc) && this.password.equals(other.password))
                || (this.indirizzo.equals(other.indirizzo) && this.password.equals(other.password)))  {
            return true;
        } else {
            return false;
        }
    }

}
