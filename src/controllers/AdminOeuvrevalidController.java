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
import javafx.scene.input.MouseEvent;
import service.OeuvrageService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AdminOeuvrevalidController implements Initializable {

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
    
    OeuvrageService os = new OeuvrageService();
     private static Oeuvre a ;
    
 public static void setOeuvre (Oeuvre o ){
        a=o;
    }
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button profil;
    @FXML
    private Button supprimer;
    @FXML
    private Label vendeur;
    @FXML
    private Button valid;
    @FXML
    private Button Deconnexion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       if (a.getIsvalid()==2){
           supprimer.setVisible(false);
       }
         File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + a.getImg());
          imgV.setImage(new Image(newFile2.toURI().toString()));
          anomo.setText("Nom :\n "+ a.getNom());
          aprixo.setText("Prix :\n "+ String.valueOf(a.getPrix())+" DT");
          adesco.setText("A propos : \n" +a.getDescription());
          adomaino.setText("Dans le domaine : \n" +a.getDoamine());
          aqteo.setText("Quantité : \n"+ String.valueOf(a.getQuantite()));
       
    }  
    
     private Optional<ButtonType> alert(String deux) {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setTitle("validation");
        alert.setHeaderText("validation");      
        alert.setContentText(deux);
        return alert.showAndWait();
    }


    @FXML
    private void supprimer(ActionEvent event) {
        
     if(alert("Voulez vous vraiment supprimer cet oeuvre de la liste").get()==ButtonType.OK)
       {
             os.nvalider(a);
             a.setIsvalid(2);
             System.out.println(a.getIsvalid());
              try { 
             Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminconsulterOeuvre.fxml"));
             Scene scene = new Scene(page1);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(AdminOeuvrevalidController.class.getName()).log(Level.SEVERE, null, ex);
         } 
       }   
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
    }

    @FXML
    private void gotoevents(ActionEvent event) {
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void voirprofil(MouseEvent event) {
        
    }

    @FXML
    private void valider(ActionEvent event) {
           if(alert("Voulez vous vraiment valider cet oeuvre").get()==ButtonType.OK)
       {
           os.valider(a);
           a.setIsvalid(1);
         
           System.out.println(a.getIsvalid());
                   
         try { 
             Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminconsulterOeuvre.fxml"));
             Scene scene = new Scene(page1);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(AdminOeuvrevalidController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
       }
    }
    
}
