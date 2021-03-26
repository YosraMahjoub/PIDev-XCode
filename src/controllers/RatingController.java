/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Formation;
import Entities.RatingEntity;
import Services.RatingSer;
import Test.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class RatingController implements Initializable {

    @FXML
    private Rating rate;
RatingEntity r = new RatingEntity();
Rating ra;
RatingSer rs = new RatingSer();
static Formation ff;
    @FXML
    private Label msg;
    @FXML
    private Button submit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rate.setPartialRating(true);
         rate.setUpdateOnHover(true);
         
         	rate.ratingProperty().addListener(new ChangeListener<Number>() {
//           @Override 
//                    public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
////             //   msg.setText("Rating : "+ t1.toString());
////            }

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                msg.setText("Rating : "+ t1.toString()); //To change body of generated methods, choose Tools | Templates.
            }
        });
     
        System.out.println(rate.getRating());

                } 

    @FXML
    private void applyR(ActionEvent event) throws SQLException {
       
        try {
            r.setValue( rate.getRating());
              r.setU1(Main.connectedUser);
              Formation x=ff;
              r.setF(x);// nheb nekhou id te3ha 
          
            rs.rating(r,ff);
            
//ra.setRating(rate.getRating());
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("confirmation");
alert.setHeaderText("notation ajoutée");
alert.setContentText("Merci pour votre contribution!!");
alert.show();
Parent page1 = FXMLLoader.load(getClass().getResource("/View/CoursListe.fxml"));
Scene scene = new Scene(page1);
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
stage.setScene(scene);
stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
        }

 
       
    
    
    
}
}
