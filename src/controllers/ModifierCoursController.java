/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import service.CoursServices;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class ModifierCoursController implements Initializable {

    @FXML
    private Button back1;
    @FXML
    private Button MESO;
    @FXML
    private TextField desc;
    @FXML
    private TextField titre;
    @FXML
    private TextField duree;
    @FXML
    private Button bimgo;
    @FXML
    private Button ajouterC;
    @FXML
    private TextField txtimg;
    @FXML
    private ComboBox<String> niv;
String nameCat="";
File file;
List <File> files ;
  static Cours aa ;
    @FXML
    private Button showFile;
    @FXML
    private TextField urlTextField;
    @FXML
    private ImageView img;
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button oeuvres;
    @FXML
    private Button profil;
    @FXML
    private Button Deconnexion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
            ObservableList<String> cat = FXCollections.observableArrayList("débutant", "intermédiaire","avancé");
        niv.setItems(cat);
        
        //File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + aa.getFile());
//         file.setReadOnly();
      if (img!=null){
      File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + aa.getFile());
      img.setImage(new Image(newFile2.toURI().toString()));
      
      }
        
            //  file.setImage(new Image(newFile2.toURI().toString()));
        desc.setText(aa.getDescription());
        titre.setText(aa.getTitre());
        duree.setText(aa.getDuree());
       niv.setValue(aa.getNiveau());
    }
        
 @FXML
	@SuppressWarnings("NestedAssignment")
	public void showFileLines(ActionEvent event) throws InterruptedException, ExecutionException, IOException {
            AfficherCoursFiles.c=aa;
         
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AfficherCoursFiles.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            }
//    @FXML
//    private void backClient(ActionEvent event) {
//        try {
//            Parent page1 = FXMLLoader.load(getClass().getResource("/views/InscriptionForm.fxml"));
//            Scene scene = new Scene(page1);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(ModifierCoursController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void acceuilFormafteur(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ACCUEIL.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chercherI(ActionEvent event) throws IOException {
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
        txtimg.setText(newFile2.getName());
        img.setImage(image2);
        
        
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
    private void ValiderModif(ActionEvent event) throws SQLException {
          Cours c = new Cours(); 
        c.setTitre(titre.getText());
        c.setDescription(desc.getText());
        c.setNiveau(nameCat);
        c.setDuree(duree.getText());
//         for (int i=0; i<files.size(); i++){
//      
//        c.setFile(files.get(i).getName());}

c.setFile(file.getName());
        CoursServices cs = new CoursServices();
        cs.update(c);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
      //  alert.setHeaderText("Formation modifié ☺ ");
            alert.setContentText("cours modifiée avec sucèes ☺ ");
            alert.showAndWait();
    }

    @FXML
    private void selectcat(ActionEvent event) {
          String s = niv.getSelectionModel().getSelectedItem();
        nameCat = s ;
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/InscriptionForm.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
}
