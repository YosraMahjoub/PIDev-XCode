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
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import service.FileUploader;
import service.UserService;
import utils.controlsaisie;
import utils.hashpassword;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SignupController implements Initializable {
    controlsaisie controle = new controlsaisie();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btninsert;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private Label btnForgot;
    @FXML
    private TextField adresse;
    @FXML
    private TextField num_tel;
    File file ;
    @FXML
    private ImageView img;
    private User user;
    @FXML
    private Button btnconn;
    @FXML
    private TextField imgpath;
    @FXML
    private Label err_username;
    @FXML
    private Label err_nom;
    @FXML
    private Label err_prenom;
    @FXML
    private Label err_tel;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao= new UserService();
        // TODO
        btninsert.setOnAction(event -> {
            if (nom.getText().isEmpty()
                || prenom.getText().isEmpty()
                || username.getText().isEmpty()
                || adresse.getText().isEmpty()
                || email.getText().isEmpty()
                || password.getText().isEmpty()
                    
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();}
            if(!controle.isEmailAdress(email.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText("veuillez fournir adresse email valide");
                alert.showAndWait();
            }
            if(pdao.existmail(email.getText())!=0){
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText("adresse mail existe déja");
                alert.showAndWait();
            }
            if(imgpath.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText("veuillez fournir une photo de profil");
                alert.showAndWait();
            }
                
            
            else{
                hashpassword hash = new hashpassword();
                String hashp = hash.hashPassword(password.getText());
                System.out.println(hashp);
                
                
                
            
            
            
            User p = new User(nom.getText(), prenom.getText(), username.getText(),hashp,adresse.getText(),Integer.parseInt(num_tel.getText()),email.getText(),file.getName());
            pdao.insert(p);
            
            
            User obj = pdao.displayEP(email.getText(), hashp);
            UserService.setCurrentUser(obj);
           
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           Parent page1=null;
            if(obj.getRole().toLowerCase().contains("admin")){
                    try { 
                        page1  = FXMLLoader.load(getClass().getResource("/views/adminconfirmail.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }else{
                    try {
                        page1 = FXMLLoader.load(getClass().getResource("/views/confiremail.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     }
             //Parent page1 = FXMLLoader.load(getClass().getResource("/view/Userprofil.fxml"));
                     Scene scene = new Scene(page1);
                     stage.setScene(scene);
                     
//                     User i = UserService.getCurrentUser();
//                     System.out.println(i.toString());
                     stage.show();
             
            }});
        btnconn.setOnAction(event -> {
                Parent page2;
            try {
                page2 = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                 Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        });
        
       
        
        
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
    private void verifprenom(KeyEvent event) {
        if (!controle.controleTextFieldOnlyLetters(prenom, "pas des chiffres", err_prenom)) {
            ;
        }
    }

    @FXML
    private void verifinom(KeyEvent event) {
        if (!controle.controleTextFieldOnlyLetters(nom, "pas des chiffres", err_prenom)) {
            ;
        }
    }

    @FXML
    private void verifnum(KeyEvent event) {
        if (!controle.controleTextFieldNonNumerique(num_tel, "que des chiffres", err_tel)) {
            ;
        }
    }

    @FXML
    private void verifusername(KeyEvent event) {
        if (!controle.controleTextFieldOnlyLetters(username, "pas des chiffres", err_username)) {
            ;
        }
    }
    
}
