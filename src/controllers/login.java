/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.email.durgesh.Email;
import entities.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.mail.MessagingException;
import service.UserService;
import utils.EmailSend;
import utils.hashpassword;

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
        UserService pdao = new UserService();
        btninsert.setOnAction(event -> {
            String email1 = email.getText().toString();
            String password1 = password.getText().toString();
        if (email.getText().isEmpty()
                || password.getText().isEmpty()
                
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();}
        if(!pdao.checkvalidité(email1)){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("compte effacé");
            alert.showAndWait();}
        
        else{
            try {

                User p = new User(email1, password1);
                //UserService pdao = new UserService();
                hashpassword hash = new hashpassword();
                //System.out.println(obj.toString());
                
                
                 if(!hash.checkPass(password1, pdao.displayAuth(email1).getPassword())){
                     Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setHeaderText("mot de passe incorrect");
                    alert.showAndWait();

                 } else {
                     
                     
                     String hashp = hash.hashPassword(password1);
                     //System.out.println(hashp);
                     String pass = pdao.displayAuth(email1).getPassword();
                     //System.out.println(pass);
                     User obj = pdao.displayEP(email1, pass);
                     //System.out.println(obj.toString());
                     UserService.setCurrentUser(obj);
                     
                     
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent page1 = null;
                    if(!pdao.checkmail(email1)){
                         try {

                             page1 = FXMLLoader.load(getClass().getResource("/views/confiremail.fxml"));
                             Scene scene = new Scene(page1);
                             //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                             stage.setScene(scene);
                             stage.show();
                        }
                     catch (IOException ex) {
                        Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);

            }
                         
                     }
                    else{
                    
                     if(obj.getRole().toLowerCase().contains("admin")){
                    page1  = FXMLLoader.load(getClass().getResource("/views/adminprofil.fxml")); 
                     }else{
                    page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                     }
                     //Parent page1 = FXMLLoader.load(getClass().getResource("/view/Userprofil.fxml"));
                     Scene scene = new Scene(page1);
                     stage.setScene(scene);
                     
//                     User i = UserService.getCurrentUser();
//                     System.out.println(i.toString());
                     stage.show();
                     
                     //System.out.println(UserService.getCurrentUser().toString());
                     
                }
                 }} catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        } });
        // TODO
        
    btninscri.setOnAction(event -> {
            
            try {
               
                     Parent page1 = FXMLLoader.load(getClass().getResource("/views/Signup.fxml"));
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
