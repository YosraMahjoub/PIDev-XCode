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
import xcode.Iservice.MyListener;
import xcode.entity.Oeuvre;
import xcode.service.OeuvrageService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ConsulterOeuvreController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label nomO;
    @FXML
    private Label prixo;
    @FXML
    private ImageView imgO;
    @FXML
    private Label descO;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
        private Oeuvre oi;
    
    private Image image;
   private MyListener myListener;
    private List<Oeuvre> listOeuvre =new ArrayList<>();
    OeuvrageService os = new OeuvrageService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listOeuvre.addAll(os.afficherLOV());
           if (listOeuvre.size() > 0) {
               System.out.println(listOeuvre.get(0));
                setChosenO(listOeuvre.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event, Oeuvre oeuvre) {

                        setChosenO(oeuvre);
                    }
                };
            }
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/xcode/views/afficherOeuvre.fxml"));
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
        prixo.setText((o.getPrix())+"DT");
        descO.setText(o.getDescription());
                File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + o.getImg());

        //image = new Image(getClass().getResourceAsStream(o.getImg()));
        imgO.setImage(new Image(newFile2.toURI().toString()));
        oi=o;
    }



    
}
