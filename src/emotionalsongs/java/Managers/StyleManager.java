/**
 * Provides the Classes necessary to 
 * manage the files that are not java files
 * @see package.emotionalsongs.java
 */
package emotionalsongs.java.Managers;

/**
 * This Class manages the css files in the application.
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class StyleManager {
    
    /**Path to the css file containing the main style of the application*/
    private  String maincss = "/emotionalsongs/resources/css/application.css";

    /**
     * Returns the css file containing the main style of the application
     * @return css file containing the main style of the application
     */
    public  String mainStyle(){
        return maincss;
    }

    /**
     * Gets the stylesheet (as String) of the css file which has this filename
     * @param filename name of the css file to get
     * @return stylesheet (as String) of the css file which has this filename
     */
    public String getStyle(String filename)
    {
        return getClass().getResource("/emotionalsongs/resources/css/"+filename+".css").toExternalForm();
    }

}
