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
import entities.ElementPanier;

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
import entities.PanierHolder;
import entities.RatingO;
import java.util.List;
import service.FavorisOService;
import service.RatigoService;
import service.cmdservices;

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
     cmdservices os = new cmdservices();
     
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
    @FXML
    private Button p;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        AJOUTER AUX FAVORIS

//changer 1 par id
        if (fs.isclicked(a,1)!=0){ 
            heart.setStyle("-fx-background-color: #A65959; "); 
        }
        else if(fs.isclicked(a,1)==0) {
            heart.setStyle("-fx-background-color: #FFFFFF; ");
             heart.setStyle("-fx-border-color:  #000000 ");
        }
        
//        RATING
         new FadeIn(rats).play();
        
          imgV.setImage(new Image("http://localhost/PI/IMG/"+ a.getImg()));
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
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
//        changer 1 par id)
            if (rs.israted(a,1)==0){
        rs.creerRo((float)rats.getRating(),a,1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("RATING ");
            alert.setContentText("note ajouté avec sucèes ☺ ");
            alert.showAndWait();
        
    }
    else {
        rs.modifierRo((float)rats.getRating(),a,1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("RATTING  ");
            alert.setContentText("votre note a été modifié  ☺ ");
            alert.showAndWait();
        
    }
      pourcent.setText(String.valueOf(rs.pourcentage(a)*20)+"% ("+ String.valueOf(rs.nbu(a))+"avis)"); 


            }  

    @FXML
    private void favoris(ActionEvent event) {
//        changer 1 par id
        if (fs.isclicked(a,1)==0){
            fs.creerFo(a,1);
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
            //        changer 1 par id
            fs.supprimerFo(a,1);
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
        
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
//        changer 1 par id
        
    List<ElementPanier> listEOp=PanierHolder.getInstance().getEP();
                ElementPanier ep=new ElementPanier();

     
        boolean existsElem=false;
        int i;
        for(i=0;i<listEOp.size();i++)
        {
            if(listEOp.get(i).getOeuv().equals(a))
            {
                existsElem=true;
                ep=listEOp.get(i);
                
                 break;
            }

        }
         if (a.getQuantite()==0){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("hors stock ! ");
            alert.setContentText("hors stock! ");
            alert.showAndWait(); 
        }
        else {
        if(!existsElem)
        {   
            ep.setOeuv(a);
            ep.setQuantite(1);
            listEOp.add(ep);
            os.createPanierTemp(1,ep.getOeuv().getOeuvrage_id(),ep.getQuantite());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Element ajouté ! ");
            alert.setContentText("Element ajouté avec sucèes ! ");
            alert.showAndWait(); 
        }
        else
        {   
            if(a.getQuantite()>ep.getQuantite())
            {
            os.updatePanierTemp(1,ep.getOeuv().getOeuvrage_id(),1);
            System.out.println(a.getOeuvrage_id()+"    1");
            ep.setQuantite(ep.getQuantite()+1);
            
            listEOp.set(i, ep);
            
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText("Element existe ! ");
            alert.setContentText("Element existe deja, quantité incrementé ! ");
            alert.showAndWait(); 
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stock insuffisant ! ");
            alert.setContentText("Stock insuffisant! ");
            alert.showAndWait(); 
            }
        }
             
            PanierHolder.getInstance().setEP(listEOp);
        
    }}

    @FXML
    private void reclamer(ActionEvent event) {
    }

    @FXML
    private void voirpanier(ActionEvent event) {
        
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/Panier.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
