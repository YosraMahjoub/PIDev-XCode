 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import entities.Oeuvre;
import entities.Upload;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
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
import service.OeuvrageService;
import org.apache.commons.io.FileUtils;
import service.FileUploader;
import utils.ControleSaisie;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjouterOeuvreController implements Initializable {

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
    private Button bimgo;
    @FXML
    private Button ajoutero;
    @FXML
    private Button annulero;
    @FXML
    private TextField txtimg;
    @FXML
    private ImageView imgV;
    
     ControleSaisie controle = new ControleSaisie();
    OeuvrageService os = new OeuvrageService();
     
    String nameCat = "" ;
    File file ;
    @FXML
    private Label errornom;
    @FXML
    private Label errorprix;
    @FXML
    private Button oeuvres;
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button profil;
    @FXML
    private Button events;
    @FXML
    private Button Deconnexion;
  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> cat = FXCollections.observableArrayList("Peinture", "Artisanat","Décoration","Sculpture","Litérature");
        domaino.setItems(cat);
    
        final int initialValue = 1;
 
        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, initialValue);
 
        qteo.setValueFactory(valueFactory);   
       
    
    } 
    
    
        private Optional<ButtonType> alert(String deux)
    {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajout");
        alert.setHeaderText("Oeuvre existe déja");      
        alert.setContentText(deux);
        return alert.showAndWait();
    }
    
    @FXML
    private void ajouterO(ActionEvent event) {
        Oeuvre o1 = new Oeuvre();
        if (desco.getText().isEmpty()
                || nomo.getText().isEmpty()
                || prixo.getText().isEmpty()
                || txtimg.getText().isEmpty()
                || nameCat ==""
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
            
        }
        
        else if ( os.exist(nomo.getText())!=0) {
             if(alert("Voulez vous le modifier ").get()==ButtonType.OK)
       {     try {
                     ModiferOController.setOeuvre(os.afficherbynom(nomo.getText()));
                     Parent page1 = FXMLLoader.load(getClass().getResource("/views/modiferOeuvre.fxml"));
                     Scene scene = new Scene(page1);
                     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                     stage.setScene(scene);
                     stage.show();
                 } catch (IOException ex) {
                     Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }
        }
        else {
        o1.setNom(nomo.getText());
        o1.setDescription(desco.getText());
        o1.setDoamine(nameCat);
        o1.setPrix(Float.parseFloat( prixo.getText()));
        o1.setQuantite( qteo.getValue());
        o1.setImg(file.getName());
//            System.out.println(o1.getIsvalid());
        os.ajouterO(o1,1);       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Oeuvre ajouté ☺ ");
            alert.setContentText("oeuvre ajouté avec sucèes ☺ ");
            alert.showAndWait();
        }
        
        String textTOimg = txtimg.getText();
        textTOimg = FileUploader.upload(textTOimg);
        textTOimg="http://localhost/PI/IMG/"+textTOimg;
    }

    @FXML
    private void selectcat(ActionEvent event) {
        String s = domaino.getSelectionModel().getSelectedItem();
        nameCat = s ;
    }

    @FXML
    private void chercherI(ActionEvent event) throws FileNotFoundException, IOException  {

        Stage primary = new Stage();
        
//        File dest =new File("C:\\xampp\\htdocs\\PI\\IMG");
        FileChooser filechooser = new FileChooser();
        filechooser.setInitialDirectory(new File("C:\\Users\\pc\\Desktop\\IMAGE"));
        filechooser.setTitle("insérer image");
        
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        file = filechooser.showOpenDialog(primary);
//        FileUtils.copyFileToDirectory(file, dest);
        
//        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + file.getName());
//        FileInputStream input2 = new FileInputStream(newFile2);
//        Image image2 = new Image(input2);
//        txtimg.setText(newFile2.getName());
//        imgV.setImage(image2);
//        
//        System.out.println(txtimg.getText());

        if(file!=null)
        {
       Upload u = new Upload();
       u.upload(file);
       txtimg.setText(file.getAbsolutePath());
       imgV.setImage(new Image("http://localhost/PI/IMG/"+ file.getName()));
     
        }
    else {
               System.out.println("Image introuvable");         
    }}

    @FXML
    private void annuler(ActionEvent event) {
     
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/affmesoeuvres.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

   
    @FXML
    private void verifNom(KeyEvent event) {
        if (!controle.controleTextFieldOnlyLetters(nomo, "que des lettres", errornom)) 
         { }
      
       
    }

    @FXML
    private void verifprix(KeyEvent event) {
        if (!controle.controleTextFieldChiffres(prixo, "que des chiffres", errorprix)) 
         { }
    }

    @FXML
    private void verifexist(KeyEvent event) {
           if( os.exist(nomo.getText())!=0) {
            errornom.setText("cet oeuvre existe déjà");    
        }
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
    }

    @FXML
    private void gotoevents(ActionEvent event) {
    }
}
    

