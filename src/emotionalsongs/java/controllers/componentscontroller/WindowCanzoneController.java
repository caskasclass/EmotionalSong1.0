package emotionalsongs.java.controllers.componentscontroller;

import java.net.URL;
import java.util.ResourceBundle;

import emotionalsongs.java.util.Canzone;
import javafx.fxml.Initializable;


public class WindowCanzoneController implements Initializable {
    Canzone c = null;
    

   
    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
    }

    public Canzone getCanzone(){
        return c;
    }
    public void setCanzone(Canzone canzone)
    {
        c = canzone;
    }

   
    
}
