/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import xcode.entity.Oeuvre;
import java.net.URL;
import java.nio.file.Files;
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
import xcode.service.OeuvrageService;
import org.apache.commons.io.FileUtils;
import xcode.utils.ControleSaisie;

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
    private Button MESO;
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
    
    
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> cat = FXCollections.observableArrayList("Peinture", "artisanat","décoration","sculpture","litérature");
        domaino.setItems(cat);
    
 
        final int initialValue = 1;
 
        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, initialValue);
 
        qteo.setValueFactory(valueFactory);   
       
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
        else {
        o1.setNom(nomo.getText());
        o1.setDescription(desco.getText());
        o1.setDoamine(nameCat);
        o1.setPrix(Float.parseFloat( prixo.getText()));
        o1.setQuantite( qteo.getValue());
        o1.setImg(file.getName());
        
        os.ajouterO(o1);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Oeuvre ajouté ☺ ");
            alert.setContentText("oeuvre ajouté avec sucèes ☺ ");
            alert.showAndWait();
        }
    }

    @FXML
    private void selectcat(ActionEvent event) {
        String s = domaino.getSelectionModel().getSelectedItem();
        nameCat = s ;
    }

    @FXML
    private void chercherI(ActionEvent event) throws FileNotFoundException, IOException  {

Stage primary = new Stage();
       File dest =new File("C:\\xampp\\htdocs\\PI\\IMG");
        
        FileChooser filechooser = new FileChooser();
        filechooser.setInitialDirectory(new File("C:\\Users\\pc\\Desktop\\IMAGE"));
        filechooser.setTitle("insérer image");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        file = filechooser.showOpenDialog(primary);
        FileUtils.copyFileToDirectory(file, dest);
        
        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + file.getName());

        FileInputStream input2 = new FileInputStream(newFile2);
        Image image2 = new Image(input2);
        txtimg.setText(newFile2.getName());
        imgV.setImage(image2);
        
        
        System.out.println(txtimg.getText());
        if(file!=null)
        {
        txtimg.setVisible(true);
        txtimg.setText(file.getName());
//            try {             Files.copy(file.toPath(),new File(path+"\\"+file.getName()).toPath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    else {
               System.out.println("Image introuvable");         
    }}

    @FXML
    private void annuler(ActionEvent event) {
       desco.setText("");
        nomo.setText(""); 
        prixo.setText("");
        
    }

    @FXML
    private void mesO(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/xcode/views/affmesoeuves.fxml"));
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
         {
           
       ;
        }
    }

    @FXML
    private void verifprix(KeyEvent event) {
        if (!controle.controleTextFieldChiffres(prixo, "que des chiffres", errorprix)) 
         {
           
       ;
        }
    }

 
}
    

