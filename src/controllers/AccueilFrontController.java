/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Iservice.MyListener;
import entities.Oeuvre;
import service.OeuvrageService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AccueilFrontController implements Initializable {

    @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private ScrollPane scrollO11;
    @FXML
    private GridPane grido;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane gride;
    @FXML
    private ScrollPane scrollO1;
    @FXML
    private GridPane gridf;
  private MyListener myListener;
    
    OeuvrageService os = new OeuvrageService();
    @FXML
    private ScrollPane scrolaccueil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //set grid width
                scrolaccueil.setMinWidth(Region.USE_COMPUTED_SIZE);
                scrolaccueil.setPrefWidth(Region.USE_COMPUTED_SIZE);
                scrolaccueil.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                scrolaccueil.setMinHeight(Region.USE_COMPUTED_SIZE);
               scrolaccueil.setPrefHeight(Region.USE_COMPUTED_SIZE);
                scrolaccueil.setMaxHeight(Region.USE_PREF_SIZE);
      
         List<Oeuvre> listOeuvre =new ArrayList<>();
        listOeuvre.addAll(os.afficherLOV());
           if (listOeuvre.size() > 0) {
                
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event, Oeuvre oeuvre) {
                         try {
                            RatingoController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Ratingo.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
     scrollO11.setPrefHeight(215);
                grido.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grido.setMinWidth(Region.USE_COMPUTED_SIZE);
                grido.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grido.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grido.setMinHeight(Region.USE_COMPUTED_SIZE);
                grido.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grido.setMaxHeight(Region.USE_PREF_SIZE);
                
                
             
                //set grid height
               
            
               
                
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
           
           
           
       
         
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);
 
                gride.add(anchorPane, column++, row); //(child,column,row)
                
                gride.setMinWidth(Region.USE_COMPUTED_SIZE);
                gride.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gride.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gride.setMinHeight(Region.USE_COMPUTED_SIZE);
                gride.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gride.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);
 
                gridf.add(anchorPane, column++, row); //(child,column,row)
                
                gridf.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridf.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridf.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridf.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridf.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridf.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }    
    
}
