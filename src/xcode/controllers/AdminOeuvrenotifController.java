/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.controllers;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xcode.Iservice.MyListener;
import xcode.entity.Oeuvre;
import xcode.service.OeuvrageService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AdminOeuvrenotifController implements Initializable {
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
    @FXML
    private Button gest;
    @FXML
    private Button mdp;
    @FXML
    private Button role;
    @FXML
    private Button form;
    
    @FXML
    private Button oeuvres;
    
    private List<Oeuvre> listOeuvreInv =new ArrayList<>();
    OeuvrageService os = new OeuvrageService();
    private MyListener myListener;
    private Oeuvre oi;
    @FXML
    private Button event;
    @FXML
    private Button stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listOeuvreInv.addAll(os.afficherLONV());
           if (listOeuvreInv.size() > 0) {
               System.out.println(listOeuvreInv.get(0));
               myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                        try {
                            AdminOeuvrevalidController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/xcode/views/AdminOeuvrevalid.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminOeuvrenotifController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };       
           }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < listOeuvreInv.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/xcode/views/AdminaffO.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();
                   AdminaffOController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvreInv.get(i),myListener);

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
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
//            Parent page1 = FXMLLoader.load(getClass().getResource("/xcode/views/AdminOeuvrevalid.fxml"));
//            Scene scene = new Scene(page1);
//            Stage stage;
//    stage = (Stage) ((Node) event.get).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
}

    @FXML
    private void gotostat(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/xcode/views/stato.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
