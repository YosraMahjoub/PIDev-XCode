/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.UserprofilController;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class changer_mdp implements Initializable {

    @FXML
    private Button btnprofil;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnrole;
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
    private VBox mot_actuel;
    @FXML
    private PasswordField mt_actuel;
    @FXML
    private PasswordField mot_nv;
    @FXML
    private PasswordField mot_conf;
    @FXML
    private Button btninsert;
     private User user;
    @FXML
    private Button btninfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnmdp.setOnAction(event -> {
            
            User p = new User(mot_nv.getText() );
            UserService pdao = UserService.getInstance();
            
            pdao.UpdateGérer(p);
        
            
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Userprofil.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/gérer_profil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnsupp.setOnAction(event -> {
        UserService pdao = UserService.getInstance();
            
            pdao.delete(user);
        });
        btninfo.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
