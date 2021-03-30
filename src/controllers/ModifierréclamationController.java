/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.ReclamationService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierréclamationController implements Initializable {

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
    private Button btnsupp;
    @FXML
    private Button btninfo;
    @FXML
    private Button reclamation;
    
    @FXML
    private TextField text_nomProd;
    @FXML
    private Label err_prod;
    @FXML
    private Button btninsert;
    @FXML
    private Text nom;
    @FXML
    private TextField text_name;
    @FXML
    private Label err_name;
    @FXML
    private Text sujet;
    @FXML
    private TextField description;
    private static int i;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;
    @FXML
    private Button annuler;
    @FXML
    private Button apprentissage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rec= new ReclamationService();
        
        
        
        Reclamation r =  rec.displayById(i);
        
        text_name.setText(r.getReclamation_nom());
        description.setText(r.getDescription());
        
        
        
        sujet.setText(rec.displayType(i).getSujet());
        text_nomProd.setText(rec.displayproduit(i));
        
        
        
        btninsert.setOnAction(event -> {
            if (text_name.getText().isEmpty()
                || text_name.getText().isEmpty()
                || text_nomProd.getText().isEmpty()
                || description.getText().isEmpty()
                
               ){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();}
                
             else{
                Reclamation r1 = new Reclamation(i, UserService.getCurrentUser().getUser_id(), text_name.getText(), description.getText());
                rec.update(r1);
                
            }
                
       
        });
        reclamation.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/réclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        annuler.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/réclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btninfo.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnprofil.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/gérer_profil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnmdp.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/changer_mdp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnsupp.setOnAction(event -> {
            UserService pdao = new UserService();
            if (alert("voulez vous vraiment supprimer le compte ?").get() == ButtonType.OK) {
            pdao.updatevalidité(UserService.getCurrentUser().getUser_id());
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }});
    }
        
     private Optional<ButtonType> alert(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer ");
            alert.setHeaderText(null);
            alert.setContentText(x);
            return alert.showAndWait();
    }   
        
                



    @FXML
    private void verifinom(KeyEvent event) {
    }

    @FXML
    private void verifusername(KeyEvent event) {
    }
    public static int getI() {
        return i;
    }

    /**
     * @param aI the i to set
     */
    public static void setI(int aI) {
        i = aI;
    }
    
}
