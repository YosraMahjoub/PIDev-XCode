/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilController implements Initializable {

    @FXML
    private Label label_nom;
    @FXML
    private Label label_bio;
    @FXML
    private Label label_role;
    @FXML
    private Label label_domaine;
    @FXML
    private Button portfolio;
    @FXML
    private Button sabonner;
    @FXML
    private Label label_nbarticle;
    @FXML
    private Label label_jaime;
    @FXML
    private Label label_abonnees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao = new UserService();
        String username ="";
        User u = pdao.displayusername(username);
        label_nom.setText(u.getUsername());
        label_bio.setText(u.getBio());
        label_role.setText(pdao.displayrole(username));
        
    }    
    
}
