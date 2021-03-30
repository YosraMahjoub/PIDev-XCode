    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import entities.Download;
import entities.Formation;
import entities.Inscription;
import service.FileDownloader;
import service.FormationServices;
import service.InscriptionsServices;
import xcode_pidev.Main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.FileUploader;
import Iservice.MyListenerF;
import java.net.MalformedURLException;
import java.net.URLConnection;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class AfficherForController implements Initializable {

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
   
    @FXML
    private Button inscrit1;
    @FXML
    private Button inscrit;
    @FXML
    private Label titre;
    @FXML
    private Button apprenti;
    @FXML
    private Button back1;
    
     private MyListenerF myListener;
     private static Formation a ;
     Inscription i = new Inscription();
     FormationServices fs = FormationServices.getInstance();
     public static void setF (Formation f ){
        a=f;
    }
      private static Cours ac ;
      private  Cours c;
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button oeuvres;
    @FXML
    private Button profil;
    @FXML
    private Button Deconnexion;
    @FXML
    private Button reclamer;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG" + a.getImage());
        imgV.setImage(new Image(newFile2.toURI().toString()));
//            url = new URL("http://localhost/Formation/Images/");
//            URLConnection conn = url.openConnection();
//            InputStream in = conn.getInputStream();
//            imgV.setImage(new Image(in.toString()));
//      imgV.setImage(new Image(newFile2.g));
//        Image.setCellValueFactory((TreeTableColumn.CellDataFeatures<BonPlan, Image> param) ->  
//                new SimpleObjectProperty(new Image(param.getValue().getValue().getImg_bp())));
//Download d = new Download("http://localhost/Formation/Images/", "C:\\xampp\\htdocs\\Formation\\Images");
//textTOimg= 
////textTOimg="http://localhost/Formation/Images/"+textTOimg;
//
//      try{
//         String fileName = "C:\\xampp\\htdocs\\Formation\\Images";
//         String website = "http://localhost/Formation/Images"+fileName;
//         
//         System.out.println("Downloading File From: " + website);
//         
//         url = new URL(website);
//         InputStream inputStream = url.openStream();
//         OutputStream outputStream = new FileOutputStream(fileName);
//         byte[] buffer = new byte[2048];
//         
//         int length = 0;
//         
//         while ((length = inputStream.read(buffer)) != -1) {
//            System.out.println("Buffer Read of length: " + length);
//            outputStream.write(buffer, 0, length);
//         }
//         
//         inputStream.close();
//         outputStream.close();
//         
//      } catch(Exception e) {
//         System.out.println("Exception: " + e.getMessage());
//      }
                            adesco.setText(a.getDescription());
                            aprixo.setText(String.valueOf(a.getPrix()));
                            adomaino.setText(a.getDomaine());
                            lang.setText(a.getLangue());
                            niv.setText(a.getNiveau());
                            lieu.setText(a.getLieu());
                            duree.setText(a.getDuree());
                            date.setText(a.getDate());
                            titre.setText(a.getTitre());
                            
                            if (fs.inscritVisible(a.getFormation_id(), Main.connectedUser.getUser_id()))
                            {inscrit.setVisible(true);}
                            else {inscrit.setVisible(false);}
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
          
          
          
    }    

    @FXML
    private void backClient(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/InscriptionForm.fxml"));
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
          CoursListeController.f=a;// hedhi mesh teb3a el ajout ta3 inscrit hedhi naamloha
          //pour fixer formation pour laquelle on veut afficher ses cours 
           RatingController.ff=a;
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/CoursListe.fxml"));
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
        if (fs.inscritVisible(a.getFormation_id(), Main.connectedUser.getUser_id()))//test sur inscrit
          { try {
             CoursListeSANinscriController.f=a;
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/CoursListeSANSinscri.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
        }}
          else {   try {
             CoursListeController.f=a;
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/CoursListe.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
        } 
           }
     
            
               }

    @FXML
    private void backAprrenti(ActionEvent event) {
        
             try {
          //   CoursListeSANinscriController.f=a;
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/MesFormations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AfficherForController.class.getName()).log(Level.SEVERE, null, ex);
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
           }

    
    
     
    
//}//
