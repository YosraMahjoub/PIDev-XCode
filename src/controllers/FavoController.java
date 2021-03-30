/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Iservice.MyListener;
import entities.Oeuvre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.FavorisOService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FavoController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button heart;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
 private Oeuvre oeuvre;
 public static Oeuvre oi ;
  private MyListener myListener;
    FavorisOService fs = new FavorisOService();
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Afavoris(ActionEvent event) {
        try {
            myListener.onpressed(event, oeuvre);
            
            if (fs.isclicked(oi)==0){
                fs.creerFo(oi);
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
                fs.supprimerFo(oi);
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
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/afficherfavo.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FavoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void click(MouseEvent event) {
          myListener.onClickListener(event,oeuvre);
    }
    
     public void setData(Oeuvre oeuvre, MyListener myListener) {
        this.oeuvre  = oeuvre;
        this.myListener = myListener;
        nameLabel.setText(oeuvre.getNom());
        priceLable.setText((oeuvre.getPrix())+"DT");
        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + oeuvre.getImg());
       

        img.setImage(new Image(newFile2.toURI().toString()));
        img.setFitHeight(150);
        img.setFitWidth(150);
    
}
     
   
}
