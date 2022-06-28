package emotionalsongs.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.Managers.UserManager;
import emotionalsongs.java.util.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerH {

    // questi sotto servono per la view di SignUP

    @FXML
    public TextField btn_username;
    @FXML
    public TextField btn_mail;
    @FXML
    public PasswordField btn_passwd;
    @FXML
    public Button btn_registra;

    StyleManager style = new StyleManager();

    public void SignUpUser(ActionEvent e) throws IOException {

        User newuser = new User(btn_username.getText(), btn_passwd.getText(), btn_mail.getText());
        ArrayList<User> users = UserManager.readUsers();
        if (users.contains(newuser)) {
            System.out.println("\n\n\nUtente gia registrato!!!\n\n\n");
        } else {
            users.add(newuser);
            System.out.println("\n\nuser aggiunto\n" + newuser.printUser() + "\n");
        }
        UserManager.getUsers(users);

        // le prossime due righe sono da mettere dentro il controllo tipo if
        // Nel sennso che se la registrazione è avvenuta con successo allora chiuedi
        // altrimenti nulla,
        // magari si stampa un messaggio di errore durante la registrazione!

        Stage stage = (Stage) btn_registra.getScene().getWindow(); // chiusura della finestra
        stage.close(); //

    }
}
