/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import animatefx.animation.FadeIn;
import animatefx.animation.Flash;
import animatefx.animation.Tada;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import entities.Oeuvre;
import entities.RatingO;
import service.FavorisOService;
import service.RatigoService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class RatingoController implements Initializable {

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
    private Button back;
     private static Oeuvre a ;
     @FXML
    private Label pourcent;
     
     RatigoService rs = new RatigoService();
     FavorisOService fs = new FavorisOService();
     
    /**
     * Initializes the controller class.
     */
    public static void setOeuvre (Oeuvre o ){
        a=o;
    } 
    
    @FXML
    private Rating rats;
    @FXML
    private Label nb;
    @FXML
    private Button heart;
    @FXML
    private FontAwesomeIconView reco;
    @FXML
    private Button oeuvres;
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
    private Button deconnexion;
    @FXML
    private Button panier;
    @FXML
    private Button reclam;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        AJOUTER AUX FAVORIS
        if (fs.isclicked(a)!=0){ 
            heart.setStyle("-fx-background-color: #A65959; "); 
        }
        else if(fs.isclicked(a)==0) {
            heart.setStyle("-fx-background-color: #FFFFFF; ");
             heart.setStyle("-fx-border-color:  #000000 ");
        }
        
//        RATING
         new FadeIn(rats).play();
        
        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + a.getImg());
          imgV.setImage(new Image(newFile2.toURI().toString()));
          anomo.setText( a.getNom());
          aprixo.setText("Prix : "+ String.valueOf(a.getPrix())+"DT");
          adesco.setText(a.getDescription());
          adomaino.setText("Dans le domaine : " +a.getDoamine());
          aqteo.setText("Quantités restantes : "+ String.valueOf(a.getQuantite()));
          pourcent.setText(String.valueOf(rs.pourcentage(a)*20)+"% ("+ String.valueOf(rs.nbu(a))+"avis)");
          
        final int initialValue = 1;
        SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
      
        
       
       rats.setPartialRating(true);
         rats.setUpdateOnHover(true);
         
         	rats.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                float z =(float) ((double) Math.round(t1.doubleValue()*1000d)/1000d*20);
                if (t1.intValue()*20 <= 25){
            nb.setText( String.valueOf(z)+"%");
                nb.setTextFill(Color.RED);} 
                
                else if (t1.floatValue()*20 < 50){
               nb.setText( String.valueOf(z)+"%");
                nb.setTextFill(Color.ORANGERED);} 
                
                else  if (t1.floatValue()*20 == 50){
               nb.setText( String.valueOf(z)+"%");
                nb.setTextFill(Color.ORANGE);} 
                
                       else if (t1.floatValue()*20 < 75){
                 nb.setText( String.valueOf(z)+"%");
                         nb.setTextFill(Color.LAWNGREEN);}  
                
                       else if (t1.floatValue()*20 <= 100){
                    nb.setText( String.valueOf(z)+"%");
                         nb.setTextFill(Color.GREEN);}  
                }
               
        });
       }    


    @FXML
    private void back(ActionEvent event) {
       try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/consulterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }


    @FXML
    private void noterat(MouseEvent event) {
        
        new Tada(nb).play();
        
            if (rs.israted(a)==0){
        rs.creerRo((float)rats.getRating(),a);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("RATING ");
            alert.setContentText("note ajouté avec sucèes ☺ ");
            alert.showAndWait();
        
    }
    else {
        rs.modifierRo((float)rats.getRating(),a);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("RATTING  ");
            alert.setContentText("votre note a été modifié  ☺ ");
            alert.showAndWait();
        
    }
      pourcent.setText(String.valueOf(rs.pourcentage(a)*20)+"% ("+ String.valueOf(rs.nbu(a))+"avis)"); 


            }  

    @FXML
    private void favoris(ActionEvent event) {
        if (fs.isclicked(a)==0){
            fs.creerFo(a);
            heart.setStyle("-fx-background-color: #A65959; "); 
         
        String msg = "Oeuvre ajouté à la liste des favoris ";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setMessage(msg);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
           
        
        }
        else {
            fs.supprimerFo( a);
            heart.setStyle("-fx-background-color: #FFFFFF; "); 
            heart.setStyle("-fx-border-color:  #000000 ");
             String msg = "Oeuvre suprimé la liste des favoris ";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setMessage(msg);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.millis(2000));
               
            
        }
    }

    @FXML
    private void reclamerO(MouseEvent event) {
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void accueil(ActionEvent event) {
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
    private void panier(ActionEvent event) {
    }

    @FXML
    private void reclamer(ActionEvent event) {
    }

    
    
}
