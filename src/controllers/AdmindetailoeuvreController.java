/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import entities.Oeuvre;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AdmindetailoeuvreController implements Initializable {

    @FXML
    private Button gest;
    @FXML
    private Button form;
    @FXML
    private Button event;
    @FXML
    private Button oeuvres;
    @FXML
    private Label adesco;
    @FXML
    private ImageView imgV;
    @FXML
    private Label aqteo;
    @FXML
    private Label aprixo;
    @FXML
    private Label adomaino;
    @FXML
    private Label anomo;
    @FXML
    private Button acc;
    @FXML
    private Button SUPP;

    /**
     * Initializes the controller class.
     */
    
         private static Oeuvre a ;
    
 public static void setOeuvre (Oeuvre o ){
        a=o;
    }
    @FXML
    private Button stat;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + a.getImg());
          imgV.setImage(new Image(newFile2.toURI().toString()));
          anomo.setText("Nom : "+ a.getNom());
          aprixo.setText("Prix : "+ String.valueOf(a.getPrix())+"DT");
          adesco.setText("A propos : " +a.getDescription());
          adomaino.setText("Dans le domaine : " +a.getDoamine());
          aqteo.setText("Quantité : "+ String.valueOf(a.getQuantite()));
       
    }    

    @FXML
    private void accueil(ActionEvent event) {
        
      
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminconsulterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdmindetailoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void oeuvre(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminconsulterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdmindetailoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void statque(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/stato.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(AdmindetailoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
