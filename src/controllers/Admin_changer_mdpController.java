/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.io.IOException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.UserService;
import utils.hashpassword;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Admin_changer_mdpController implements Initializable {

    @FXML
    private VBox mot_actuel;
    @FXML
    private PasswordField mt_actuel;
    @FXML
    private PasswordField mot_nv;
    @FXML
    private PasswordField mot_conf;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnprofil1;
    @FXML
    private Button btnmdp1;
    @FXML
    private Button btnformation1;
    @FXML
    private Button btnevenement1;
    @FXML
    private Button btnoeuvres1;
    @FXML
    private Button btninfo1;
    @FXML
    private Button btnuser1;
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
    @FXML
    private Button btnsupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao = new UserService();
       btninsert.setOnAction(event -> {
            hashpassword hash = new hashpassword();
            User p = new User(mot_nv.getText() );
            
            int id =UserService.getCurrentUser().getUser_id(); 
            String email = UserService.getCurrentUser().getEmail(); 
            System.out.println(id);
            if(!(hash.checkPass(mt_actuel.getText(), pdao.displayAuth(email).getPassword()))){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText("le mot de passe actuel est incorrect");
                alert.showAndWait();
            }
            if(! mot_nv.getText().equals(mot_conf.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setHeaderText("les mots de passe ne sont pas identiques");
                    alert.showAndWait();
            }else{
            String hashp = hash.hashPassword(mot_conf.getText());
            pdao.updateMDP(id, hashp);
            User obj = pdao.displayById(id);
            System.out.println(obj.toString());
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
       btnprofil1.setOnAction(event -> {
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
       btninfo1.setOnAction(event -> {
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
       btnuser1.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/afficher_user.fxml"));
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
        btnsupp.setOnAction(event -> {
                            

            pdao.updatevalidité(UserService.getCurrentUser().getUser_id());
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Gérer_profilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
