package emotionalsongs.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.util.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controller Class for the file SignUpWindow.fxml .
 * @author Beatrice Bastianello, matricola 751864
 * @author Nazar Viytyuk, matricola 748964
 */
public class ControllerH {

    /**fxml element for graphics */
    @FXML
    private TextField btn_cf;

    /**fxml element for graphics */
    @FXML
    private TextField btn_ind;
    /**fxml element for graphics */
    @FXML
    private TextField btn_nome;
    /**fxml element for graphics */
    @FXML
    public TextField btn_username;
    /**fxml element for graphics */
    @FXML
    public TextField btn_mail;
    /**fxml element for graphics */
    @FXML
    public PasswordField btn_passwd;
    /**fxml element for graphics */
    @FXML
    public Button btn_registra;
    /**fxml element for graphics */
    @FXML
    public Label msgErr;

    /**
     * Metodo per la registrazione dell'utente. Agisce quando btn_registra viene premuto
     * @param e javafx action event 
     * @throws IOException IOexception
     */
    public void Registrazione(ActionEvent e) throws IOException {

        String[] s = {btn_username.getText(), btn_passwd.getText(), btn_mail.getText(), btn_nome.getText() 
       , btn_cf.getText(), btn_ind.getText()};
       List<String> s2 = Arrays.asList(s);
        if(s2.contains("")){
            msgErr.setText("non hai compilato tutti i campi");
        }
            
        else {
            User newuser = new User(btn_username.getText(), btn_passwd.getText(), btn_mail.getText(), btn_nome.getText().toLowerCase(), btn_cf.getText().toUpperCase(), btn_ind.getText().toLowerCase());
            ArrayList<User> users = UserManager.readUsers();
            if(users.contains(newuser)){
                msgErr.setText("utente già registrato");
            }
            else {
                users.add(newuser);
                Stage stage = (Stage) btn_registra.getScene().getWindow(); // chiusura della finestra
                stage.close();
            
            }
            UserManager.getUsers(users); 
            
        } 
        
        }
        
    /**
     * Moves the focus on anothe textfield when the key is pressed
     * @param key Enter key
     */
    public void focus(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_cf.requestFocus();
        }
    }
    /**
     * Moves the focus on anothe textfield when the key is pressed
     * @param key Enter key
     */
    public void focus2(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_username.requestFocus();
        }
    }
    /**
     * Moves the focus on anothe textfield when the key is pressed
     * @param key Enter key
     */
    public void focus3(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_mail.requestFocus();
        }
    }
    /**
     * Moves the focus on anothe textfield when the key is pressed
     * @param key Enter key
     */
    public void focus4(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_passwd.requestFocus();
        }
    }
    /**
     * Moves the focus on anothe textfield when the key is pressed
     * @param key Enter key
     */
    public void focus5(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_ind.requestFocus();
        }
    }
    /**
     * Moves the focus on anothe textfield when the key is pressed
     * @param key Enter key
     */
    public void focus6(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_registra.requestFocus();
        }
    }
}
