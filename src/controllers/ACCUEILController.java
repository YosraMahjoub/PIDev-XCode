/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import entities.Formation;
import service.FormationServices;
import xcode_pidev.Main;
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
import javafx.scene.control.MenuItem;
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
import Iservice.MyListenerF;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class ACCUEILController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label prixo;
    @FXML
    private Label descO;
    @FXML
    private Button modifierO;
    @FXML
    private Button supprimer;
    @FXML
    private Button ADD;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView img;
    @FXML
    private Button inscritBTN;
    /**
     * Initializes the controller class.
     */
    private  Formation f;
     private Image image;
   private MyListenerF myListener;
    private List<Formation> listf =new ArrayList<>();
    FormationServices ff = new FormationServices();
    @FXML
    private TextField cher;
    @FXML
    private Label titre;
    @FXML
    private Button rechbtn;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listf.addAll(ff.readAll(Main.connectedUser.getUser_id()));
           if (listf.size() > 0) {
               System.out.println(listf.get(0));
                setChosenO(listf.get(0));
                myListener = new MyListenerF() {
                    
                    @Override
                    public void onClickListener(MouseEvent event ,Formation formation) {
                        setChosenO(formation);
                    }

                   @Override
                   public void onClickListener(MouseEvent event, Cours cours) {
                       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }
                };
            }
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listf.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affichage.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffichageController itemController = fxmlLoader.getController();
                    
                itemController.setData(listf.get(i),myListener);

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
                   Logger.getLogger(ACCUEILController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
         
    
    
        private void setChosenO(Formation ff) {
        titre.setText(ff.getTitre());
        prixo.setText((ff.getPrix())+"DT");
        descO.setText(ff.getDescription());
        File newFile2 = new File("C:\\xampp\\htdocs\\Formation\\Images" + ff.getImage());

        img.setImage(new Image(newFile2.toURI().toString()));
        f=ff;
    }
        
             
    @FXML
    private void loadSceneAndSendMessage( ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/AjouerFormation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouerFormationController.class.getName()).log(Level.SEVERE, null, ex);
            }}


    @FXML
    private void inscrit(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/InscriptionForm.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(InscrController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
           ModifierController.setFor(f);
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/modifierFor.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouerFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void suppr(ActionEvent event) throws IOException, SQLException {
         
              if(   alert("Voulez vous supprimer cette formation").get()==ButtonType.OK)
              {  ff.delete(f);
              Parent page1 = FXMLLoader.load(getClass().getResource("/views/ACCUEIL.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
                      }
    }
       private Optional<ButtonType> alert(String deux)
    {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        
        alert.setHeaderText("Suppression");      
        alert.setContentText(deux);
        return alert.showAndWait();
    }    

    @FXML
    private void chercher(ActionEvent event) throws SQLException {
//          if(cher.getText()!=f.getTitre())
//               // equals(""))
     listf.addAll(ff.readAll());
        boolean a = listf.stream().anyMatch(f -> f.getTitre().equalsIgnoreCase(cher.getText()));
        if (a!=true)
      //  if(cher.getText()!=f.getTitre())
        {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
     
            alert.setContentText("pas de formation avec ce nom");
            alert.showAndWait();
        }
//                
//        
//        }
    else{
        
        ArrayList<Formation> listf = (ArrayList<Formation>) ff.rechercherFor(cher.getText()); 
            //  data.clear();
             // data = FXCollections.observableArrayList(liste);           
          if (listf.size() > 0) {
               System.out.println(listf.get(0));
               
                setChosenO(listf.get(0));
                
                myListener = new MyListenerF() {
                    
                    @Override
                    public void onClickListener(MouseEvent event ,Formation formation) {
                       try {
                            AfficherForController.setF(f);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AfficherFor.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                           Logger.getLogger(InscrController.class.getName()).log(Level.SEVERE, null, ex);
                   
                };
    }

                   @Override
                   public void onClickListener(MouseEvent event, Cours cours) {
                       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }
             };
        }
          int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listf.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/AfficherForClick.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherForClickController itemController = fxmlLoader.getController();
                    
                itemController.setData(listf.get(i),myListener);

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
                  Logger.getLogger(InscrController.class.getName()).log(Level.SEVERE, null, ex);
               } 
           

    }
        
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void rechercheO(ActionEvent event) {
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
            Logger.getLogger(ACCUEILController.class.getName()).log(Level.SEVERE, null, ex);
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

    


    

