package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import main.java.util.User;

public class Homebuilder implements Initializable {
    @FXML private FlowPane mainbody;

    @Override
    public void initialize(URL urilink, ResourceBundle reb) {
       System.out.println("\n\nFunzia bene");
       for(int i=0;i<10;i++)
       {
        Label lab = new Label();
        lab.setText("Text here"+i);
        mainbody.getChildren().addAll(lab);
       }
       
    }

}
