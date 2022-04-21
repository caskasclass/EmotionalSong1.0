package main.java.util;


import java.io.Serializable;

public class User implements Serializable {

    String username;
    String password;
    String email;

    public User(String u, String p, String e) {
        username = u;
        password = p;
        email = e;
    }

    public String printUser() {
        return ("\nUsername : " + username + "\nPassword : " + password + "\nEmail : " + email + "\n");
    }

    // da sviluppare motodi getParametro() perché sono privati.

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
                || (this.username.equals(other.username) && this.password.equals(other.password))) {
            return true;
        } else {
            return false;
        }
    }

}
