/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminAfficheruserdetailsController implements Initializable {

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
    private Button btninfo;
    @FXML
    private Button btnuser;
    @FXML
    private Label label_nom;
    @FXML
    private Label label_prenom;
    @FXML
    private Label label_adresse;
    @FXML
    private Label label_tel;
    @FXML
    private Label label_email;
    @FXML
    private Label label_bio;
    @FXML
    private Label label_role;
    @FXML
    private Label label_statut;
    private static int i;
    private static int x;
    @FXML
    private Button reclamation;
    @FXML
    private Label label_nb;
    @FXML
    private Label label_username;
    @FXML
    private Button retour;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;
    @FXML
    private Button btnsupp;
    @FXML
    private ImageView img;
    @FXML
    private Button reclamation1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao=new UserService();
        retour.setVisible(false);
        
        System.out.println(i);
        User obj = pdao.displayById(i);
        System.out.println(obj.toString());
        label_nom.setText(obj.getNom());
        label_email.setText(obj.getEmail());
        label_username.setText(obj.getUsername());
        label_prenom.setText(obj.getPrenom());
        label_adresse.setText(obj.getAdresse());
        label_tel.setText(String.valueOf(obj.getNum_tel()));
        label_bio.setText(obj.getBio());
        label_role.setText(obj.getRole());
        img.setImage(new Image("http://localhost/PI/IMG/" +obj.getImage()));
        String value = "";
        label_role.setText(value);
        label_statut.setText(obj.getValidité());
        if(x==5){
            retour.setVisible(true);
            retour.setOnAction(event -> {
                try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admin_reclamationdetails.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        }
        if(x==0){
            retour.setVisible(true);
            retour.setOnAction(event -> {
                try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficher_user.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        }
        
        
        btnprofil.setOnAction(event -> {
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admingérercompte.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btninfo.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnmdp.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admin_changer_mdp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        reclamation.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admin_reclamations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnuser.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficher_user.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnsupp.setOnAction(event -> {
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
        // TODO

    /**
     * @return the i
     */
    public static int getI() {
        return i;
    }

    /**
     * @param aI the i to set
     */
    public static void setI(int aI) {
        i = aI;
    }
    public static int getX() {
        return x;
    }

    /**
     * @param aI the i to set
     */
    public static void setX(int aI) {
        x = aI;
    }
    }    
    

