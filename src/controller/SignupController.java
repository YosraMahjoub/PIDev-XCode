/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
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

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                
            
            else{
                hashpassword hash = new hashpassword();
                String hashp = hash.hashPassword(password.getText());
                System.out.println(hashp);
                
                
                
            UserService pdao = new UserService();
            
            User p = new User(nom.getText(), prenom.getText(), username.getText(),hashp,adresse.getText(),Integer.parseInt(num_tel.getText()),email.getText() );
            pdao.insert(p);
            
            
            User obj = pdao.displayEP(email.getText(), hashp);
            UserService.setCurrentUser(obj);
           
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/confiremail.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            }});
        
        
                }    

    @FXML
    private void Chercheimg(MouseEvent event) throws IOException {
        Stage primary = new Stage();
       File dest =new File("C:\\xampp\\htdocs\\PI\\IMG");
        
        FileChooser filechooser = new FileChooser();
        filechooser.setInitialDirectory(new File("C:\\"));
        filechooser.setTitle("insérer image");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        file = filechooser.showOpenDialog(primary);
        FileUtils.copyFileToDirectory(file, dest);
        
        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + file.getName());

        FileInputStream input2 = new FileInputStream(newFile2);
        Image image2 = new Image(input2);
        img.setImage(image2);
        
    }
    
}
