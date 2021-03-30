/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import service.UserService;
import utils.EmailSend;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminconfirmailController implements Initializable {

    @FXML
    private VBox mot_actuel;
    @FXML
    private PasswordField code;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnvalider;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;
    @FXML
    private Button btninfo;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnformation;
    @FXML
    private Button btnevenement;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button reclamation;
    @FXML
    private Button btnsupp;
    String x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao = new UserService();
        
        try {
            x = EmailSend.SendMail("aa");
        } catch (MessagingException ex) {
            Logger.getLogger(ConfiremailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfiremailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnvalider.setOnAction(event -> {
            if(!code.getText().equals(x)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                
                alert.setHeaderText("code érroné");
                alert.showAndWait();
            }
            else{
                //UserService pdao = new UserService();
                System.out.println(UserService.getCurrentUser().getUser_id());
                pdao.updatmailconfirmé(UserService.getCurrentUser().getUser_id());
                User obj = pdao.displayEP(UserService.getCurrentUser().getEmail(), UserService.getCurrentUser().getPassword());
                //System.out.println(obj.toString());
                UserService.setCurrentUser(obj);
                try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
        });
        btnannuler.setOnAction(event -> {
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       btnprofil.setOnAction(event -> {
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admingérercompte.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btninfo.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnmdp.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admin_changer_mdp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        reclamation.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admin_reclamations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnuser.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficher_user.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnsupp.setOnAction(event -> {
            if (alert("voulez vous vraiment supprimer le compte ?").get() == ButtonType.OK) {
            pdao.updatevalidité(UserService.getCurrentUser().getUser_id());
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }});
        
        
            
        

        

    }
    private Optional<ButtonType> alert(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer ");
            alert.setHeaderText(null);
            alert.setContentText(x);
            return alert.showAndWait();
    }   

   @FXML
    private void colorchange(KeyEvent event) {
        code.setStyle("-fx-background-color:" + code.getText()+";");
    }    

    @FXML
    private void renvoyer(MouseEvent event) {
        try {
            x = EmailSend.SendMail("aa");
        } catch (MessagingException ex) {
            Logger.getLogger(ConfiremailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfiremailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
