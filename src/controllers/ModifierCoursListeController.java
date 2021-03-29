/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import entities.Formation;
import service.CoursServices;
import static controllers.CoursListeController.f;
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
import Iservice.MyListenerF;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class ModifierCoursListeController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    static Formation s;
    static Cours c;
     MyListenerF myListener;
    private List<Cours> listc =new ArrayList<>();
  private CoursServices cs = new CoursServices();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listc.addAll(cs.readAll(f));
                if (listc.size() > 0) {
                    System.out.println(listc.get(0));
                    myListener = new MyListenerF() {
                        @Override
                        public void onClickListener(MouseEvent event, Formation oeuvre) {
                           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                        
                        @Override
                        public void onClickListener(MouseEvent event, Cours cours) {
                            try { 
//AfficherCoursController.setF(f);
//                                AfficherCoursController.setC(cours);
                                 ModifierController.setFor(f);
                                 ModifierCoursController.aa=cours;
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ModifierCours.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(CoursListeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    
                }
                int column = 0;
                int row = 1;
                try {
                    for (int i = 0; i < listc.size(); i++) {
                        
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/views/CoursClick.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        CoursClickController itemController = fxmlLoader.getController();
                        itemController.setData(listc.get(i),myListener);
                        
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
                    Logger.getLogger(CoursListeController.class.getName()).log(Level.SEVERE, null, ex);
                }
           
    }    

    @FXML
    private void backForMod(ActionEvent event) {
        try {
           
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ModifierFor.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierCoursListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
