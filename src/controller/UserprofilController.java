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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class UserprofilController implements Initializable {

    @FXML
    private Button btngérer;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnrole;
    @FXML
    private Button btnformation;
    @FXML
    private Button btneven;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button btnfavories;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Label label_nom;
    @FXML
    private Label label_prenom;
    @FXML
    private Label label_adresse;
    @FXML
    private Label label_tel;
    @FXML
    private Label label_email;
    @FXML
    private Label label_bio;
    public static User u;
    private User user;
    UserService us = new UserService();
    @FXML
    private Button btninfo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        this.user = user;
        UserService pdao=UserService.getInstance();
        User obj = pdao.displayById(4);
        System.out.println(obj.toString());
        
        label_nom.setText(obj.getNom());
        label_email.setText(obj.getEmail());
        label_prenom.setText(obj.getPrenom());
        label_adresse.setText(obj.getAdresse());
        label_tel.setText(String.valueOf(obj.getNum_tel()));
        label_bio.setText(obj.getBio());
        
        btngérer.setOnAction(event -> {
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
        btnmdp.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/changer_mdp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnsupprimer.setOnAction(event -> {

            pdao.delete(user);
        });
        
        
            
        

        

    }
}
    
  
    

