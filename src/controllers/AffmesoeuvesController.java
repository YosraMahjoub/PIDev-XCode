/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Iservice.MyListener;
import entities.ElementPanier;
import entities.Oeuvre;
import service.OeuvrageService;


/**
 * FXML Controller class
 *
 * @author pc
 */
public class AffmesoeuvesController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private GridPane grid;
    @FXML
    private Label nomO;
    @FXML
    private Label prixo;
    @FXML
    private ImageView imgO;
    @FXML
    private Label descO;
    @FXML
    private Button modifierO;
    @FXML
    private Button supprimerO;
    @FXML
    private ScrollPane scrollO;
    
    private Oeuvre oi;

    /**
     * Initializes the controller class.
     */
  
   
   private MyListener myListener;
    OeuvrageService os = new OeuvrageService();
    @FXML
    private Button ADD1;
    @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private Button oeuvres;
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button event;
    @FXML
    private Button profil;
    @FXML
    private Button deconnexion;
   
    
    

    
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<Oeuvre> listOeuvre =new ArrayList<>();
           listOeuvre.addAll(os.afficherLOBI(1));
           if (listOeuvre.size() > 0) {
                setChosenO(listOeuvre.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                        setChosenO(oeuvre);
                    }

                    @Override
                    public void onpressed(ActionEvent event, Oeuvre oeuvre) { }

                    @Override
                    public void onClickListener(ElementPanier facture) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
            }
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);

                if (column == 3) {
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
                }
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }

        
        
    }    

        private void setChosenO(Oeuvre o) {
        nomO.setText(o.getNom());
        prixo.setText((o.getPrix())+" DT");
        descO.setText(o.getDescription());

        //image = new Image(getClass().getResourceAsStream(o.getImg()));
        imgO.setImage(new Image("http://localhost/PI/IMG/"+ o.getImg()));
        oi=o;
    }
    
    @FXML
    private void ajouter(ActionEvent event) {
        //        changer 1 par id
        if (os.nbNV(2,1)>= 5){
         
        String titre = "Attention";
        String msg = "vous avez déjà 5 oeuvres non valid !";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tray.setAnimationType(type);
        tray.setTitle(titre);
        tray.setMessage(msg);
        tray.setNotificationType(NotificationType.WARNING);
        tray.showAndDismiss(Duration.millis(4000));
           
        }
        
      try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ajouterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @FXML
    private void modifierO(ActionEvent event) {
  
        
        try {
            ModiferOController.setOeuvre(oi);
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/modiferOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private Optional<ButtonType> alert(String deux)
    {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Suppression");      
        alert.setContentText(deux);
        return alert.showAndWait();
    }
    
    @FXML
    private void SupprimerO(ActionEvent event) {
       if(alert("Voulez vous supprimer").get()==ButtonType.OK)
       {
           os.supprimerO(oi);
           try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/affmesoeuvres.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        } }
    }


    @FXML
    private void rechercheO(ActionEvent event) {
      List<Oeuvre> listOeuvre =new ArrayList<>();
       listOeuvre.addAll(os.afficherLOBI(1));
        boolean a = listOeuvre.stream().anyMatch(o -> o.getNom().equalsIgnoreCase(recho.getText()));
        System.out.println(a);
          
        if (recho.getText().isEmpty()) {
           recho.setPromptText("veuillez remplir ce champs d'abord");
           recho.setStyle("-fx-text-inner-color: black;");
        }
        
        else if (a!=true) {
            int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);

                if (column == 3) {
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
                }
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Aucun oeuvre avec ce nom");
            alert.showAndWait();
            
        }
        else {
            List<Oeuvre> rechOeuvre;
            rechOeuvre =  listOeuvre.stream().filter(o -> o.getNom().equalsIgnoreCase(recho.getText())).collect(Collectors.toList());
            System.out.println(rechOeuvre.size() );
            
           if (rechOeuvre.size() > 0) {
              
               
                setChosenO(rechOeuvre.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                        setChosenO(oeuvre);
                    }

                    @Override
                    public void onpressed(ActionEvent event, Oeuvre oeuvre) {
                       }

                    @Override
                    public void onClickListener(ElementPanier facture) {}
                };
            }
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < rechOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(rechOeuvre.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); 

                //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
    
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void gotoevent(ActionEvent event) {
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }
}