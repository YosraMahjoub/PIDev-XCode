/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Upload;
import entities.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import service.FileUploader;
import service.UserService;
import utils.controlsaisie;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Gérer_profilController implements Initializable {

    @FXML
    private TextField text_nom;
    @FXML
    private TextField text_prenom;
    @FXML
    private TextField text_adresse;
    @FXML
    private TextField text_num;
    private TextField text_email;
    @FXML
    private TextField test_bio;

    @FXML
    private Button btninsert;
    @FXML
    private TextField text_username;
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
    controlsaisie controle = new controlsaisie();
    @FXML
    private Label err_prenom;
    @FXML
    private Label err_adresse;
    @FXML
    private Label err_num;
    @FXML
    private Label err_email;
    @FXML
    private Label err_username;
    @FXML
    private ImageView img;
    private User user;
    File file;
    @FXML
    private Button btninfo;
    @FXML
    private Button reclamation;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private Button btnannuler;
    @FXML
    private Button apprentissage;
    @FXML
    private TextField imgpath;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button profil;
    @FXML
    private Button Deconnexion;
    @FXML
    private HBox hello;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao = new UserService();
        int id =UserService.getCurrentUser().getUser_id(); 
                

       
        User obj = pdao.displayById(id);
        text_nom.setText(obj.getNom());
        text_prenom.setText(obj.getPrenom());
        text_username.setText(obj.getUsername());
        text_adresse.setText(obj.getAdresse());
        text_num.setText(String.valueOf(obj.getNum_tel()));
        
        test_bio.setText(obj.getBio());
        img.setImage(new Image("http://localhost/PI/IMG/" +obj.getImage()));
        
        
        
        
        btninsert.setOnAction(event -> {

            if (text_nom.getText().isEmpty()
                    || text_prenom.getText().isEmpty()
                    || text_username.getText().isEmpty()
                    || text_adresse.getText().isEmpty())
                    {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText("Veuillez remplir tous les champs");
                alert.showAndWait();
            } else {
                
                User p = new User(text_nom.getText(), text_prenom.getText(), text_username.getText(), text_adresse.getText(), Integer.parseInt(text_num.getText()),  test_bio.getText(),file.getName() );
            
               
                System.out.println(id);

                pdao.UpdateGérer(p,id);
                User user = pdao.displayById(id);
                UserService.setCurrentUser(user);
                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(Gérer_profilController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                Logger.getLogger(Gérer_profilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btnannuler.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Gérer_profilController.class.getName()).log(Level.SEVERE, null, ex);
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
    
        
     
        btninfo.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Gérer_profilController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Gérer_profilController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
    private Optional<ButtonType> alert(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer ");
            alert.setHeaderText(null);
            alert.setContentText(x);
            return alert.showAndWait();
    }   

    @FXML
    private void verifprenom(KeyEvent event) {
        if (!controle.controleTextFieldOnlyLetters(text_prenom, "pas des chiffres", err_prenom)) {
            ;
        }
    }

    @FXML
    private void verifinom(KeyEvent event) {
        if (!controle.controleTextFieldOnlyLetters(text_nom, "pas des chiffres", err_prenom)) {
            ;
        }
    }

    @FXML
    private void verifnum(KeyEvent event) {
        if (!controle.controleTextFieldNonNumerique(text_num, "que des chiffres", err_num)) {
            ;
        }
    }

    @FXML
    private void verifusername(KeyEvent event) {
        if (!controle.controleTextFieldOnlyLetters(text_username, "pas des chiffres", err_username)) {
            ;
        }
    }

   @FXML
    private void Chercheimg(MouseEvent event) throws IOException {
        Stage primary = new Stage();
       //File dest =new File("C:\\xampp\\htdocs\\PI\\IMG");
        
        FileChooser filechooser = new FileChooser();
        filechooser.setInitialDirectory(new File("C:\\"));
        filechooser.setTitle("insérer image");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        file = filechooser.showOpenDialog(primary);
//        FileUtils.copyFileToDirectory(file, dest);
//        
//        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + file.getName());
//
//        FileInputStream input2 = new FileInputStream(newFile2);
//        Image image2 = new Image(input2);
//        img.setImage(image2);
        
        if(file!=null){
            Upload u = new Upload();
            u.upload(file);
            imgpath.setText(file.getAbsolutePath());
            img.setImage(new Image("http://localhost/PI/IMG/" +file.getName()));
        }else{
            System.out.println("image introuvable");
        }
    }
     public void imgggg(){
     String textTOimg = imgpath.getText();
        textTOimg = FileUploader.upload(textTOimg);
        textTOimg="http://localhost/PI/IMG"+ textTOimg;}

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
    }

    @FXML
    private void gotoevents(ActionEvent event) {
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void accueil(ActionEvent event) {
    }
    

}
