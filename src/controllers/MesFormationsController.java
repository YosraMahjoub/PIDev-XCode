/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Cours;
import Entities.Formation;
import IServices.MyListener;
import Services.FormationServices;
import Test.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class MesFormationsController implements Initializable {

    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
    @FXML
    private Button note;
    @FXML
    private Button gest;
    @FXML
    private Button mdp;
    @FXML
    private Button role;
    @FXML
    private Button event;
    @FXML
    private Button favoris;
    @FXML
    private Button form;
    @FXML
    private Button event1;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    
    
      private MyListener myListener;
    private List<Formation> listforInscrit =new ArrayList<>();
    private FormationServices fs = new FormationServices();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listforInscrit.addAll(fs.read(Main.connectedUser.getUser_id()));
        if (listforInscrit.size() > 0) {
               System.out.println(listforInscrit.get(0));
               myListener = new MyListener() {
                   @Override
                   public void onClickListener(MouseEvent event, Formation f) {
                          try {
                            AfficherForController.setF(f);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/View/AfficherFor.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } 
                          catch (IOException ex) {
                            Logger.getLogger(AdminNotifController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
                for (int i = 0; i < listforInscrit.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/View/affichage.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();
                   AffichageController itemController = fxmlLoader.getController();
                itemController.setData(listforInscrit.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
              
                } catch (IOException ex) {
                   Logger.getLogger(AdminNotifController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
    }    

    @FXML
    private void notation(ActionEvent event) {
    }

    @FXML
    private void backClient(ActionEvent event) throws IOException {
        
        Parent page1 = FXMLLoader.load(getClass().getResource("/View/InscriptionForm.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show(); 
        
    }
    
}
