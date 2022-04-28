package emotionalsongs.java.controllers;

import java.io.IOException;
import java.util.ArrayList;

import emotionalsongs.EmotionalSongs;
import emotionalsongs.java.Managers.FileManager;
import emotionalsongs.java.Managers.StyleManager;
import emotionalsongs.java.util.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerH {

    private static String PATH = "./src/emotionalsongs/resources/DataBaseBrutto/UtentiRegistrati.dati.txt";

    @FXML
    public TextField ue_field;
    @FXML
    public PasswordField passwd_field;
    @FXML
    public Label lb_allert;
    @FXML
    public Hyperlink reg_link;
    @FXML
    public TextField btn_username;
    @FXML
    public TextField btn_mail;
    @FXML
    public PasswordField btn_passwd;
    @FXML
    public Button btn_registra;
    @FXML
    public Button btn_login;

    StyleManager style = new StyleManager();

    public void Login(ActionEvent e) throws IOException {
        User loguser = new User(ue_field.getText(), passwd_field.getText(), ue_field.getText());
        ArrayList<User> users = (ArrayList<User>) FileManager.ReadUsers(PATH);
        if (users.contains(loguser)) {
            // int index = users.indexOf(loguser);//trova l'indice dell'user nella lista
            // User logged = users.get(index);
            // perché scrivere due righe di codice se puoi scrivere una
            User logged = users.get(users.indexOf(loguser)); // molto meglio
            System.out.println(logged.printUser());// testing ok!

            // chiudo questa finestra
            closewindow(btn_login);
            // apro la home
            buildHome(logged);
            // magari il cod di sorpa da ficcare in un private buildHome method
            lb_allert.setTextFill(Color.GREEN);
            lb_allert.setText("User trovato");

            // System.out.println("\n\n\nUser trovato\n\n\n");
        } else {
            lb_allert.setTextFill(Color.RED);
            lb_allert.setText("User non trovato");
            // System.out.println("\n\n\nUser non presente\n\n\n");
        }
    }

    // brutto brutto da vedere intendo la creazione di una nuova istanza del main
    public void SignUpWind(ActionEvent e) throws IOException {
        EmotionalSongs m = new EmotionalSongs();
        m.changeScene("resources/view/SignUpWindow.fxml");
    }

    public void SignUpUser(ActionEvent e) throws IOException {

        User newuser = new User(btn_username.getText(), btn_passwd.getText(), btn_mail.getText());
        ArrayList<User> users = FileManager.ReadUsers(PATH);
        if (users.contains(newuser)) {
            System.out.println("\n\n\nUtente gia registrato!!!\n\n\n");
        } else {
            users.add(newuser);
            System.out.println("\n\nuser aggiunto\n" + newuser.printUser() + "\n");
        }
        FileManager.getUsers(users, PATH);

        // le prossime due righe sono da mettere dentro il controllo tipo if
        // Nel sennso che se la registrazione è avvenuta con successo allora chiuedi
        // altrimenti nulla,
        // magari si stampa un messaggio di errore durante la registrazione!

        Stage stage = (Stage) btn_registra.getScene().getWindow(); // chiusura della finestra
        stage.close(); //

    }

    private void buildHome(User u) throws IOException {

       
        //borderPane.setTop(menuBar);
        Stage homewindow = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../../resources/view/HomeWindow.fxml"));
        Parent homeroot = (Parent) fxmlloader.load();
        Homebuilder homebuilder = fxmlloader.<Homebuilder>getController();
        
        homebuilder.setUser(u);
        Scene scene = new Scene(homeroot);
        scene.getStylesheets().add(style.mainStyle());
        homewindow.setScene(scene);
        homewindow.show();
    }

    private void closewindow(Node n) {
        Stage stage = (Stage) n.getScene().getWindow(); // chiusura della finestra
        stage.close();
    }
}
