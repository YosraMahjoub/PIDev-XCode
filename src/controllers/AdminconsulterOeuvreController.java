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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Iservice.MyListener;
import entities.Oeuvre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import service.OeuvrageService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AdminconsulterOeuvreController implements Initializable {

    private Label nomO;
    private Label prixo;
    private ImageView imgO;
    private Label descO;
    @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;

    
    private MyListener myListener;
    OeuvrageService os = new OeuvrageService();
    private Oeuvre oi;
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
    private ComboBox<String>   combov;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> vald = FXCollections.observableArrayList("oeuvre validé", "oeuvre en attente","oeuvre non validé");
        combov.setItems(vald);
        
        
         if (os.nbNV(0)>= 1){
         
        String titre = "Oeuvre non valid ";
        String msg = "vous avez des noeuveux oeuvres non valid ! Veuillez les voir ";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.FADE;
        
        tray.setAnimationType(type);
        tray.setTitle(titre);
        tray.setMessage(msg);
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(5000));
           
        }
        
        
        List<Oeuvre> listOeuvre =new ArrayList<>();
           listOeuvre.addAll(os.displayAll());
           
           
           if (listOeuvre.size() > 0) {
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {
                           if (oeuvre.getIsvalid()==1){
           try {
                            AdmindetailoeuvreController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/admindetailoeuvre.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        }
        if (oeuvre.getIsvalid()==0){
             try {
                            AdminOeuvrevalidController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AdminOeuvrevalid.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        }
        if (oeuvre.getIsvalid()==2){
           
           try {
                            AdminOeuvrevalidController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AdminOeuvrevalid.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                        }
         
            
        }
                       
                    }

                    @Override
                    public void onpressed(ActionEvent event, Oeuvre oeuvre) { }
                };
            }
           int column = 0;
            int row = 1;
         
                for (int i = 0; i < listOeuvre.size(); i++) {

            try {
                
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
            } catch (IOException ex) {
                Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    private void supprimer(ActionEvent event) {
    if(alert("Voulez vous supprimer").get()==ButtonType.OK)
       {
        try {
            os.supprimerO(oi);
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminconsulterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }  
       }
    

    @FXML
    private void rechercher(ActionEvent event) {
        List<Oeuvre> listOeuvre =new ArrayList<>();
       listOeuvre.addAll(os.displayAll());
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
               
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                    }

                    @Override
                    public void onpressed(ActionEvent event, Oeuvre oeuvre) { }
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminconsulterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
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
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/stato.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void filtrer(ActionEvent event) {
        if(combov.getSelectionModel().getSelectedItem().equals("oeuvre validé")){
          
          List<Oeuvre> listOeuvreV =new ArrayList<>();
           listOeuvreV.addAll(os.afficherLOV());
            myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {
                        try {
                            AdmindetailoeuvreController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/admindetailoeuvre.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

              @Override
              public void onpressed(ActionEvent event, Oeuvre oeuvre) { }
                };
           
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
         
                for (int i = 0; i < listOeuvreV.size(); i++) {

              try {
                  FXMLLoader fxmlLoader = new FXMLLoader();
                  fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                  AnchorPane anchorPane = fxmlLoader.load();
                  
                  AfficherOeuvreController itemController = fxmlLoader.getController();
                  itemController.setData(listOeuvreV.get(i),myListener);
                  
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
              } catch (IOException ex) {
                  Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
              } }
        }
        
         if(combov.getSelectionModel().getSelectedItem().equals("oeuvre en attente")){
          
          List<Oeuvre> listOeuvreV =new ArrayList<>();
           listOeuvreV.addAll(os.afficherLONV());
            myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {
                          try {
                            AdminOeuvrevalidController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AdminOeuvrevalid.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

              @Override
              public void onpressed(ActionEvent event, Oeuvre oeuvre) { }
                };
           
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
         
                for (int i = 0; i < listOeuvreV.size(); i++) {

              try {
                  FXMLLoader fxmlLoader = new FXMLLoader();
                  fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                  AnchorPane anchorPane = fxmlLoader.load();
                  
                  AfficherOeuvreController itemController = fxmlLoader.getController();
                  itemController.setData(listOeuvreV.get(i),myListener);
                  
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
              } catch (IOException ex) {
                  Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
              } }
        }
         if(combov.getSelectionModel().getSelectedItem().equals("oeuvre non validé")){
          
          List<Oeuvre> listOeuvreV =new ArrayList<>();
           listOeuvreV.addAll(os.afficherLOR());
            myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {
                    
                    }

              @Override
              public void onpressed(ActionEvent event, Oeuvre oeuvre) { }
                };
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
         
                for (int i = 0; i < listOeuvreV.size(); i++) {

              try {
                  FXMLLoader fxmlLoader = new FXMLLoader();
                  fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                  AnchorPane anchorPane = fxmlLoader.load();
                  
                  AfficherOeuvreController itemController = fxmlLoader.getController();
                  itemController.setData(listOeuvreV.get(i),myListener);
                  
                  if (column == 3) {
                      column = 0;
                      row++;
                  }
                  grid.add(anchorPane, column++, row);
//                  //(child,column,row)

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
                  Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
              } }
        }
    }
}
