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
import javafx.scene.input.KeyEvent;
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
public class ConfiremailController implements Initializable {

    @FXML
    private Button btnprofil;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnformation;
    @FXML
    private Button btnevenement;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button btnfavories;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btninfo;
    @FXML
    private VBox mot_actuel;
    @FXML
    private PasswordField code;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnvalider;
    String x;
    
    EmailSend em = new EmailSend();
    @FXML
    private Label label;
    @FXML
    private Button reclamation;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;

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
        
        // TODO
        btnannuler.setOnAction(event -> {
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
        });
        btnsupp.setOnAction(event -> {
                            

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
    }    

    @FXML
    private void colorchange(KeyEvent event) {
        label.setStyle("-fx-background-color:" + code.getText()+";");
    }
    
}
