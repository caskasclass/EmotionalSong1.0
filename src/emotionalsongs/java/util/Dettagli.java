/**
 * Provides the Classes that represent the main objects of the program
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.util;

import java.io.Serializable;

/**
 * Represents an object Dettagli. 
 * It has the user who wrote the comment and the comment itself
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class Dettagli implements Serializable{

    /** Represents the user who wrote the comment*/
    private User user;
     /** Represents the user who wrote the comment*/
    private String commento;
    
    /**
     * Returns an object of the type Dettagli.
     * @param us the user who wrote the comment
     * @param com the comment
     */
    public Dettagli(User us,String com){
        this.user = us;
        this.commento= com;
    }

    /**
     * Returns an object of the type Dettagli with all fields null.
     */
    public Dettagli(){ 
        
    }

    /**
     * Returns the object User of the type object Dettagli 
     * @return Current object User of the type object Dettagli
     */
    public User getUser(){
        return user;
    }

    /**
     * Returns the comment of the type object Dettagli 
     * @return Current comment of the type object Dettagli
     */
    public String getCommento(){
        return commento;
    }

    /**
     * Compares Commenti objects based on the field user 
     */
    @Override
    public boolean equals(Object o) {
        Dettagli tmp = (Dettagli) o;
        if (this.user.equals(tmp.user)) {
            return true;
        }
        return false;
    }
    
}
