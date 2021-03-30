/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Iservice.MyListener;
import entities.FavorisO;
import entities.Oeuvre;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import service.FavorisOService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AfficherfavoController implements Initializable {

    @FXML
    private Button btninfo;
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
    private Button apprentissage;
    @FXML
    private Button reclamation;
    @FXML
    private Button btnsupprimer;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;
    
    FavorisOService fs = new FavorisOService();

    private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        List<Oeuvre> listf =new ArrayList<>();
           listf.addAll(fs.afflf(1));
           if (listf.size() > 0) {
               
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {
                        try {
                            RatingoController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Ratingo.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AfficherfavoController.class.getName()).log(Level.SEVERE, null, ex);
                        } }

                    @Override
                    public void onpressed(ActionEvent event, Oeuvre oeuvre) {
                            FavoController.oi=oeuvre;
                    }
                };
            }
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listf.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/favo.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    FavoController itemController = fxmlLoader.getController();
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
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }

    }    

    @FXML
    private void gotooeuvre(ActionEvent event) {
         try {
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AfficherfavoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
    }
    
}
