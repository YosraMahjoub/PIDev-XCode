/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class login implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button btninsert;
    @FXML
    private Label btnForgot;

    private ObservableList<User> persons=FXCollections.observableArrayList();
    @FXML
    private Button btninscri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btninsert.setOnAction(event -> {
            String email1 = email.getText().toString();
            String password1 = password.getText().toString();
        if (email.getText().isEmpty()
                || password.getText().isEmpty()
                
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();}
        else{
            try {
                User p = new User(email1, password1);
                UserService pdao = UserService.getInstance();
                 if(!(pdao.displayAuth(email1, password1) == true)){
                 } else {
                     Parent page1 = FXMLLoader.load(getClass().getResource("/view/Userprofil.fxml"));
                     Scene scene = new Scene(page1);
                     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                     stage.setScene(scene);
                     stage.show();
                }
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        } });
        // TODO
        
    btninscri.setOnAction(event -> {
            
            try {
               
                     Parent page1 = FXMLLoader.load(getClass().getResource("/view/Signup.fxml"));
                     Scene scene = new Scene(page1);
                     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                     stage.setScene(scene);
                     stage.show();
                }
             catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        });
        // TODO
    }    
    
}
