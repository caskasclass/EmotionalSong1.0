package emotionalsongs.java.Managers;


public class StyleManager {
    
    private  String maincss = "/emotionalsongs/resources/css/application.css";

    public  String mainStyle(){
        return maincss;
    }

    //per testare i path nella console.
    public  void  printPath()
    {
        System.out.println(maincss);
        
    }
    public String getStyle(String filename)
    {
        return getClass().getResource("/emotionalsongs/resources/css/"+filename+".css").toExternalForm();
    }

}
