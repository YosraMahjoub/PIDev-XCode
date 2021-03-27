/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import entities.Oeuvre;
import service.OeuvrageService;
import utils.ControleSaisie;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ModiferOController implements Initializable {

    @FXML
    private TextField desco;
    @FXML
    private TextField nomo;
    @FXML
    private TextField prixo;
    @FXML
    private ComboBox<String> domaino;
    @FXML
    private Spinner<Integer> qteo = new Spinner<Integer>();
    @FXML
    private Button annulero;
    @FXML
    private Button modifiero;
     private static Oeuvre s ;
    
    ControleSaisie controlem = new ControleSaisie();
    
    
    OeuvrageService os = new OeuvrageService();
    String nameCat = "" ;
    File file ;
    @FXML
    private ImageView imgV;
    
    @FXML
    private Label nomer;
    @FXML
    private Label prixerr;
    @FXML
    private Button oeuvres;
    @FXML
    private Button home;
    @FXML
    private Button emplo;
    @FXML
    private Button form;
    @FXML
    private Button event;
    @FXML
    private Button profil;
    @FXML
    private Button deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> cat = FXCollections.observableArrayList("Peinture", "artisanat","décoration","sculpture","litérature");
        domaino.setItems(cat);
    
        final int initialValue = (int) s.getQuantite();
 
        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, initialValue);
 
        qteo.setValueFactory(valueFactory); 
        
        try {
         File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + s.getImg());
          imgV.setImage(new Image(newFile2.toURI().toString()));
          nomo.setText(s.getNom());
          prixo.setText(String.valueOf(s.getPrix()));
          desco.setText(s.getDescription());
          domaino.setValue(s.getDoamine());
        } catch (Exception e) {
        }
        
       
    }    
       


    public static void setOeuvre (Oeuvre o ){
        s=o;
    }
    @FXML
    private void selectCat(ActionEvent event) {
        String s = domaino.getSelectionModel().getSelectedItem();
        nameCat = s ;
         
    }

    @FXML
    private void modifierO(ActionEvent event) {
    
     
        if (desco.getText().isEmpty()
                || nomo.getText().isEmpty()
                || prixo.getText().isEmpty()
                
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
        else {
        s.setNom(nomo.getText());
        s.setDescription(desco.getText());
        s.setDoamine(nameCat);
        s.setPrix(Float.parseFloat( prixo.getText()));
        s.setQuantite( qteo.getValue());
        
        
        os.modifierO(s);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Oeuvre modifié ☺ ");
            alert.setContentText("oeuvre modifié avec sucèes ☺ ");
            alert.showAndWait();
        }
    
    }
    @FXML
    private void CLICK(ActionEvent event) {
        
      try {
         File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + s.getImg());
          imgV.setImage(new Image(newFile2.toURI().toString()));
          nomo.setText(s.getNom());
          prixo.setText(String.valueOf(s.getPrix()));
          desco.setText(s.getDescription());
          domaino.setValue(s.getDoamine());
        } catch (Exception e) {
        }
        
    }
    

    private void ajouterO(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ajouterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficherMO(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/affmesoeuves.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @FXML
    private void verifn(KeyEvent event) {
        if (!controlem.controleTextFieldOnlyLetters(nomo, "que des lettres", nomer)) 
         { ;
        }
    }

    @FXML
    private void verifp(KeyEvent event) {
        if (!controlem.controleTextFieldChiffres(prixo, "que des chiffres", prixerr)) 
         { 
       ;
        }
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void gotoemplo(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
    }

    @FXML
    private void gotoevent(ActionEvent event) {
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }


  

}
