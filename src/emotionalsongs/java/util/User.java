package emotionalsongs.java.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import emotionalsongs.java.Managers.FileManager;

public class User implements Serializable {
    private static String PATH = "./src/emotionalsongs/resources/database/UtentiRegistrati.dati.txt";

    private String userId;
    private String username;
    private String password;
    private String email;
    private String nomecognome;
    private String codiceFisc;
    private String indirizzo;
    private boolean guest;

    public User(String u, String p, String e, String nc, String cf, String ind,boolean gst) {
        userId =  UUID.randomUUID().toString();
        username = u;
        password = p;
        email = e;
        nomecognome = nc;
        codiceFisc = cf;
        indirizzo = ind;
        guest = gst;
    }
    public boolean  getGuest(){
        return guest;
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
    public String getId() {
        return this.userId;
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
        if (this.username.equals(other.username) && this.password.equals(other.password)) 
                 {
            return true;
        } else {
            return false;
        }
    }

}
