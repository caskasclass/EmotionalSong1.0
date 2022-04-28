package emotionalsongs.java.Managers;


public class StyleManager {
    
    private  String maincss = getClass().getResource("../../resources/css/application.css").toExternalForm();

    public  String mainStyle(){
        return maincss;
    }

    //per testare i path nella console.
    public  void  printPath()
    {
        System.out.println(maincss);
    }

}
