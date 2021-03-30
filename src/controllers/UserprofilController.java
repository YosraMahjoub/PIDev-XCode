/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ReclamationService;
import service.RelationsService;
import service.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    public User obj;
   
    
    @FXML
    private Button btninfo;
    @FXML
    private Button confirmer_num;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private Label label_abonnees;
    @FXML
    private Label label_jaime;
    @FXML
    private Label label_nbarticle;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;
    @FXML
    private Button reclamation;
    @FXML
    private Button apprentissage;
    @FXML
    private Label username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        UserService pdao=new UserService();
        RelationsService rela=new RelationsService();
        
        int id = UserService.getCurrentUser().getUser_id();
        System.out.println(id);
        User obj = pdao.displayById(id);
        
        
        
        username.setText(obj.getUsername());
        label_nom.setText(obj.getNom());
        label_email.setText(obj.getEmail());
        label_prenom.setText(obj.getPrenom());
        label_adresse.setText(obj.getAdresse());
        label_tel.setText(String.valueOf(obj.getNum_tel()));
        label_bio.setText(obj.getBio());
        if(!pdao.checknum(UserService.getCurrentUser().getEmail())){
            confirmer_num.setVisible(true);
        }else{
            confirmer_num.setVisible(false);
        }
        int y = rela.nbfollowerByfolloweeId(id);
        label_abonnees.setText(String.valueOf(y));
        
        btngérer.setOnAction(event -> {
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/gérer_profil.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/changer_mdp.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/réclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        btnsupprimer.setOnAction(event -> {
            if (alert("voulez vous vraiment supprimer le compte ?").get() == ButtonType.OK) {
            pdao.updatevalidité(id);
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }});
        confirmer_num.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/confirmer_num.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btninfo.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ReclamationService rec= new ReclamationService();
        if(rec.checkavertissement(id)){
            String titre = "Attention";
            String msg = "vous avez un avertissement sur " + rec.Nomprod_avertissement(id) ;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(titre);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(4000)); 
        }
       

        

        

    }

    @FXML
    private void gotooeuvre(ActionEvent event) {
     
                 try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/affmesoeuvres.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     private Optional<ButtonType> alert(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer ");
            alert.setHeaderText(null);
            alert.setContentText(x);
            return alert.showAndWait();
    }
}
    
  
    

