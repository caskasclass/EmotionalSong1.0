package emotionalsongs.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import emotionalsongs.java.Managers.StyleManager;
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
// ****************************************
// Controller della finestra Regisrtazione!
// ****************************************
public class ControllerH {

    // questi sotto servono per la view di SignUP
    @FXML
    private TextField btn_cf;

    @FXML
    private TextField btn_ind;

    @FXML
    private TextField btn_nome;

    @FXML
    public TextField btn_username;
    @FXML
    public TextField btn_mail;
    @FXML
    public PasswordField btn_passwd;
    @FXML
    public Button btn_registra;
    @FXML
    public Label msgErr;

    StyleManager style = new StyleManager();

    public void Registrazione(ActionEvent e) throws IOException {

        //problemi con trim per ora l'ho tolto
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
         // le prossime due righe sono da mettere dentro il controllo tipo if
        // Nel sennso che se la registrazione è avvenuta con successo allora chiuedi
        // altrimenti nulla,
        // magari si stampa un messaggio di errore durante la registrazione!
        
    public void focus(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_cf.requestFocus();
        }
    }
    public void focus2(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_username.requestFocus();
        }
    }
    public void focus3(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_mail.requestFocus();
        }
    }
    public void focus4(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_passwd.requestFocus();
        }
    }
    public void focus5(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_ind.requestFocus();
        }
    }
    public void focus6(KeyEvent key)
    {
        if(key.getCode().equals(KeyCode.ENTER))
        {
            this.btn_registra.requestFocus();
        }
    }
}
