package emotionalsongs.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.util.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        
        User newuser = new User(btn_username.getText(), btn_passwd.getText(), btn_mail.getText(), btn_nome.getText().toLowerCase(), btn_cf.getText().toUpperCase(), btn_ind.getText().toLowerCase());
        ArrayList<User> users = UserManager.readUsers();
        if (users.contains(newuser)) {
            System.out.println("tente già registrato\n");
            //msgErr.setText("utente già registrato");
            //non funzia
            
        } else {
            users.add(newuser);
            System.out.println("\n\nuser aggiunto\n" + newuser.printUser() + "\n");
        }
        UserManager.getUsers(users);

        // le prossime due righe sono da mettere dentro il controllo tipo if
        // Nel sennso che se la registrazione è avvenuta con successo allora chiuedi
        // altrimenti nulla,
        // magari si stampa un messaggio di errore durante la registrazione!
        /*if( btn_username.getText() != null && btn_passwd.getText() != null && btn_mail.getText() != null && btn_nome.getText() != null 
            && btn_cf.getText() != null && btn_ind.getText() != null)
            {
                msgErr.setText("non hai compilato tutti i campi");
                users.remove(newuser);
                //label con "non hai compilato tutti i campi"
            }
        else{
            //
        }*/
        Stage stage = (Stage) btn_registra.getScene().getWindow(); // chiusura della finestra
        stage.close();
        

    }
}
