    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Cours;
import Entities.Formation;
import Entities.Inscription;
import IServices.MyListener;
import Services.FormationServices;
import Services.InscriptionsServices;
import Test.Main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class AfficherForController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Label adesco;
    @FXML
    private ImageView imgV;
    @FXML
    private Label lieu;
    @FXML
    private Label aprixo;
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

    /**
     * Initializes the controller class.
     */
    private MyListener myListener;
     private static Formation a ;
     
     FormationServices fs = FormationServices.getInstance();
     public static void setF (Formation f ){
        a=f;
    }
      private static Cours ac ;
      private  Cours c;
    @FXML
    private Button inscrit1;
    @FXML
    private Button inscrit;
    @FXML
    private Label titre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + a.getImage());
        imgV.setImage(new Image(newFile2.toURI().toString()));
         adesco.setText(a.getDescription());
        aprixo.setText(String.valueOf(a.getPrix()));
         adomaino.setText(a.getDomaine());
         lang.setText(a.getLangue());
          niv.setText(a.getNiveau());
          lieu.setText(a.getLieu());
          duree.setText(a.getDuree());
          date.setText(a.getDate());
          titre.setText(a.getTitre());
    }    

    @FXML
    private void backClient(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/InscriptionForm.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
@FXML
     private void AjouerFormationController(ActionEvent event) throws IOException {
        
          try {
                Inscription i = new Inscription();
                InscriptionsServices in =new InscriptionsServices();
                i.setU1(Main.connectedUser);
                Formation x =a;
                i.setF(x);
                
                              
                in.ajouter(i);
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Inscription Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Welcome to the course !");
                alert.show();
                
          // AfficherCoursController.setF(a);
          CoursListeController.f=a;
           
            Parent page1 = FXMLLoader.load(getClass().getResource("/View/CoursListe.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
                
            } catch (SQLException ex) {
                Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @FXML
    private void afficherCours(ActionEvent event) {
        
        try {
             CoursListeSANinscriController.f=a;
            Parent page1 = FXMLLoader.load(getClass().getResource("/View/CoursListeSANSinscri.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
               }
           }

    
    
     
    
//}//
