/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import service.FormationServices;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class StatDomaineController implements Initializable {

    @FXML
    private BarChart<String, Integer> idstatart;
    @FXML
    private Button back;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            FormationServices f=new FormationServices();
        XYChart.Series<String, Integer> series = f.bestDomain();
        idstatart.setData(FXCollections.observableArrayList(series));
    }    

    @FXML
    private void adminNotif(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/StatDomaine.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StatDomaineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
