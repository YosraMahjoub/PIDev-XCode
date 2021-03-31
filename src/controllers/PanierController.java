/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.OeuvreesController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Iservice.MyListener;
import entities.Commande;
import entities.ElementPanier;
import entities.Oeuvre;
import entities.PDFAP;
import entities.PanierHolder;
import entities.paniertemp;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import service.cmdservices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */



public class PanierController implements Initializable {

    @FXML
    private Button Valider;
    @FXML 
    private Button Supprimer;
    @FXML 
    private Button Retour;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label nomC;
    @FXML
    private Label prixC;
    @FXML
    private ImageView imgC;

    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
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
   
    
    private ElementPanier fi;
    
    private Image image;
    
    private PDFAP pdf;
    private Commande oi;
    private MyListener MyListener;
    private List<ElementPanier> ListF =new ArrayList<>();
    cmdservices os = new cmdservices();
    @FXML
    private Button Deconnexion;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
           ListF=PanierHolder.getInstance().getEP();
           
           if (ListF.size() > 0) {
               setChosenF(ListF.get(0));
                MyListener = new MyListener() {
                    @Override
                    public void onClickListener(ElementPanier facture) {

                        setChosenF(facture);
                    }

                   @Override
                   public void onClickListener(MouseEvent event, Oeuvre oeuvre) {}

                   @Override
                   public void onpressed(ActionEvent event, Oeuvre oeuvre) { }
                };
            }
           int column = 0;
            int row = 1;
           
                for (int i = 0; i < ListF.size(); i++) {

               try {
                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/Oeuvrees.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();
                   
                   OeuvreesController itemController = fxmlLoader.getController();
                   itemController.setData(ListF.get(i),MyListener);
                   
                   if (column == 1) {
                       column = 0;
                       row++;
                   }
                   
                   grid.add(anchorPane, column++, row); //(child,column,row)
                   //set grid width
                   grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                   grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                   grid.setMaxWidth(Region.USE_PREF_SIZE);
                   
                   //set grid height
                   grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                   grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                   grid.setMaxHeight(Region.USE_PREF_SIZE);
                   GridPane.setMargin(anchorPane, new Insets(10));
               } catch (IOException ex) {
                   Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
               }
                }
              
    }

        private void setChosenF(ElementPanier f) {
            nomC.setText(f.getOeuv().getNom());
            prixC.setText((f.getOeuv().getPrix())+"DT");
            //descO.setText(f.getDescription());
                File newFile2 = new File("C:\\Users\\Mega-PC\\Desktop\\XCode\\src\\img\\" + f.getOeuv().getImg());

             //image = new Image(getClass().getResourceAsStream(o.getImg()));
            imgC.setImage(new Image("http://localhost/PI/IMG/"+ f.getOeuv().getImg()));
             fi=f;
    }    
    
    
    

     @FXML
    private void DeletePanier(ActionEvent event) throws IOException {
        os.deletepaniertElem(1, fi.getOeuv().getOeuvrage_id());
        PanierHolder.getInstance().removeEP(fi);
        fi=null;
        
        String titre = "Supprimé!";
            String msg = "Oeuvre supprimé du panier ☺!";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            
            tray.setAnimationType(type);
            tray.setTitle(titre);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(4000));
            
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/Panier.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
      

    }
    
    
    @FXML
    public void Valider(ActionEvent event) throws IOException{
        List<ElementPanier> listEOp=PanierHolder.getInstance().getEP();        
        cmdservices a =new cmdservices();
        PDFAP f = new PDFAP();
        if(ListF.size()<1){
            
            
            String titre = "Echec !";
            String msg = "Votre panier est vide!";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            
            tray.setAnimationType(type);
            tray.setTitle(titre);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(4000));
            
            
            
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        }else{
                a.createPanier(1);
                f.PDFCreator();
            
            
        String titre = "Validé";
        String msg = " Panier validé avec succès ☺"
                + "Vous pouvez trouver votre facture sur votre bureau ☺";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setTitle(titre);
        tray.setMessage(msg);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(4000));
            
                            os.deletepaniert(1);
                            listEOp.clear();
            
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        }
       
    }
    
    @FXML 
    public void Retour(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    @FXML
    private void allerauxoeuvres(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
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
    private void accueil(ActionEvent event) {
    }
}
