/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Reclamation;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.ReclamationService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutreclamationController implements Initializable {

    @FXML
    private Button btnprofil;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnformation;
    @FXML
    private Button btnevenement;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button btnfavories;
    @FXML
    private ImageView img;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btninfo;
    @FXML
    private Button reclamation;
    @FXML
    private ComboBox<String> filter;
    @FXML
    private TextField text_nomProd;
    @FXML
    private Button btninsert;
    @FXML
    private Text nom;
    @FXML
    private TextField text_name;
    @FXML
    private Label err_prod;
    @FXML
    private Label err_name;
    @FXML
    private TextField description;
    @FXML
    private Text nom_prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> cat = FXCollections.observableArrayList("evenement", "formation","oeuvre");
        filter.setItems(cat);
        ReclamationService recla = new ReclamationService();
        btninsert.setOnAction(event -> {
            if (text_name.getText().isEmpty()
                || text_name.getText().isEmpty()
                || text_nomProd.getText().isEmpty()
                || description.getText().isEmpty()
                
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();}
                else{
                Reclamation o = new Reclamation(UserService.getCurrentUser().getUser_id(), text_name.getText(), description.getText());
                String x =filter.getValue();
                if(x=="evenement"){
                    try {
                        recla.insert(o, "evenement_id" , x,"nom",text_nomProd.getText());
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutreclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                if(x=="formation"){
                    try {
                        recla.insert(o, "formation_id" , x,"titre",text_nomProd.getText());
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutreclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                if(x=="oeuvre"){
                    try {
                        recla.insert(o, "oeuvrage_id" , "oeuvrage","nom",text_nomProd.getText());
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutreclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
            
                
        });
        reclamation.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/admin_reclamations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void Chercheimg(MouseEvent event) {
    }

    @FXML
    private void comboAction(ActionEvent event) {
        String x =filter.getValue();
        if(x=="evenement"){
           nom_prod.setText("nom de l'evenement");
            
        }
        if(x=="formation"){
           nom_prod.setText("nom de la formation");
            
        }
        if(x=="oeuvre"){
           nom_prod.setText("nom de l'oeuvre");
            
        }
    }

    @FXML
    private void verifinom(KeyEvent event) {
    }

    @FXML
    private void verifusername(KeyEvent event) {
    }
    
}
