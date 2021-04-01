/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Formation;
import service.FormationServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import service.CoursServices;
import xcode_pidev.Main;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class ValidationAdmiController implements Initializable {

    @FXML
    private Button form;
    @FXML
    private Label adesco;
    @FXML
    private ImageView imgV;
    @FXML
    private Label lieu;
    @FXML
    private Label aprixo;
    @FXML
    private Button valider;
    @FXML
    private Label adomaino;
    @FXML
    private Label niv;
    @FXML
    private Label lang;
    @FXML
    private Label date;
    @FXML
    private Label duree;
    @FXML
    private Label titre;
    /**
     * Initializes the controller class.
     */
    
    private static Formation a ;
    private  Formation f;
     FormationServices fs = FormationServices.getInstance();
     public static void setF (Formation f ){
        a=f;
    }
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form1;
    @FXML
    private Button events;
    @FXML
    private Button oeuvres;
    @FXML
    private Button profil;
    @FXML
    private Button Deconnexion;
    @FXML
    private Button btninfo;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnevenement;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button stat;
    @FXML
    private Button reclamations;
    @FXML
    private Button btnsupp;
    @FXML
    private Button inscrit1;
    CoursServices cs= new CoursServices();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + a.getImage());
        imgV.setImage(new Image("http://localhost/PI/IMG/" + a.getImage()));
        titre.setText(a.getTitre());
         adesco.setText(a.getDescription());
        aprixo.setText(String.valueOf(a.getPrix()));
         adomaino.setText(a.getDomaine());
         lang.setText(a.getLangue());
          niv.setText(a.getNiveau());
          lieu.setText(a.getLieu());
          duree.setText(a.getDuree());
          date.setText(a.getDate());
          if (cs.coursVisible(a.getFormation_id())){inscrit1.setVisible(true);}
          else{inscrit1.setVisible(false);}
                 }    

    @FXML
    private void validerO(ActionEvent event) throws SQLException {
           //ValidationAdmiController.setF(f);
          if(alert("Voulez vous vraiment valider cette formation?").get()==ButtonType.OK)
          {  fs.valider(a); 
//          try {
//              Parent page1 = FXMLLoader.load(getClass().getResource("/views/AdminNotif.fxml"));
//              Scene scene = new Scene(page1);
//              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//              stage.setScene(scene);
//              stage.show();
//              } catch (IOException ex) {
//                  Logger.getLogger(ValidationAdmiController.class.getName()).log(Level.SEVERE, null, ex);
//              }     
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dialog");
        alert.setHeaderText(null);
     alert.setContentText("formation validée");
          }
            
          
    }
    private Optional<ButtonType> alert(String deux) {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setTitle("validation");
        alert.setHeaderText("validation");      
        alert.setContentText(deux);
        return alert.showAndWait();
    }
    @FXML
    private void acceuilFor(ActionEvent event) {
        
    
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AdminNotifFormation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ValidationAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/InscriptionForm.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ValidationAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotoevents(ActionEvent event) {
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void statistics(ActionEvent event) {
    }

    @FXML
    private void afficherCours(ActionEvent event) {
        if (cs.coursVisible(a.getFormation_id()))
        try {
            CoursListeController.x=0;
            CoursListeController.f=a;
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/CoursListe.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ValidationAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
