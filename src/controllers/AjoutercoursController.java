/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import entities.Formation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import service.CoursServices;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class AjoutercoursController implements Initializable {

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
     TextField txtimg;
   List <File>  files;
   File file;
    @FXML
    private Button ajouterC;
    @FXML
    private ComboBox<String> niv;
    
    String nameCat = "" ;
    
   // CoursServices cs = CoursServices();

    /**
     * Initializes the controller class.
     */
    
      static Formation f ;
      private static int i;
      public static void setI(int ai){
         i=ai;
      }
      public static int getI(){
          return i;
      }
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                ObservableList<String> cat = FXCollections.observableArrayList("débutant", "intermédiaire","avancé");
        niv.setItems(cat);
    
         }    


    @FXML
    private void ajouterC(ActionEvent event) throws SQLException {
          
        if (titre.getText().isEmpty()
                || desc.getText().isEmpty()
                || duree.getText().isEmpty()
                || txtimg.getText().isEmpty()
                || nameCat ==""
                
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
        else {
           Cours c = new Cours(); 
        c.setTitre(titre.getText());
        c.setDescription(desc.getText());
        c.setNiveau(nameCat);
        c.setDuree(duree.getText());
        //c.setFile(nameCat);
      //  c.setFormation_id(4);// the problem is here 
      
       c.setFile(file.getName());
//       for (int i=0; i<files.size(); i++){
//       c.setFile(files.get(i).getName());
//       }
        CoursServices cs = new CoursServices();
         
         c.setF(f);
         System.out.println("el couuuurus " + getI());
       // c.setFormation_id(f.getFormation_id());
        //cs.ajouter(c);
       cs.ajouter(c,i);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setHeaderText("Cours ajouté");
        alert.setContentText("Vous pouvez ajouter encore des cours");
        
        ButtonType ajouterC= new ButtonType("ajouter ");
        ButtonType buttonTypeTwo = new ButtonType("Non ");
        alert.getButtonTypes().setAll(ajouterC, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ajouterC){
    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Cours.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ACCUEILController.class.getName()).log(Level.SEVERE, null, ex);
            }
   
} 
else { Alert aa = new Alert(Alert.AlertType.INFORMATION);
        aa.setHeaderText("cours ajouté ☺ ");
            aa.setContentText("cours ajouté avec sucèes ☺ ");
            aa.showAndWait();
//
}
}
        
        
    }

    //@FXML
//    private void annuler(ActionEvent event) {
//        desc.setText("");
//        nomo.setText(""); 
//        prixo.setText("");
//    }
    
    
      @FXML
    private void chercherI(ActionEvent event) throws FileNotFoundException, IOException  {
// Stage primary = new Stage();
//       File dest =new File("C:\\xampp\\htdocs\\PI\\IMG");
//        
//        FileChooser filechooser = new FileChooser();
//        filechooser.setInitialDirectory(new File("C:\\"));
//        filechooser.setTitle("insérer image");
//      //  filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"), new FileChooser.ExtensionFilter("Text Files", "*.txt"));
//        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all", "*.*"));
//      file = filechooser.showOpenDialog(primary);
//        
//        
//        List <File> files = filechooser.showOpenMultipleDialog(primary);
//         
//     for (int i=0; i<files.size(); i++){
//        
//        FileUtils.copyFileToDirectory(files.get(i), dest);
//        
//        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + files.get(i).getName());
//
//        FileInputStream input2 = new FileInputStream(newFile2);
//        
//        Image image2 = new Image(input2);
//        txtimg.setText(newFile2.getName());
//     //   imgV.setImage(image2);
//       System.out.println(txtimg.getText());
//        if(files.get(i)!=null)
//        {
//        txtimg.setVisible(true);
//        txtimg.setText(files.get(i).getName());
////            try {             Files.copy(file.toPath(),new File(path+"\\"+file.getName()).toPath());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
//   
//     else {
//               System.out.println("Image introuvable");         
//    }}
    
    Stage primary = new Stage();
       File dest =new File("C:\\xampp\\htdocs\\PI\\IMG");
        
        FileChooser filechooser = new FileChooser();
        filechooser.setInitialDirectory(new File("C:\\"));
        filechooser.setTitle("insérer image");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"), new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        file = filechooser.showOpenDialog(primary);
        FileUtils.copyFileToDirectory(file, dest);
        
        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + file.getName());

        FileInputStream input2 = new FileInputStream(newFile2);
        Image image2 = new Image(input2);
        txtimg.setText(newFile2.getName());
     //   imgV.setImage(image2);
       System.out.println(txtimg.getText());
      //files.get(i)
        if(file!=null)
        {
        txtimg.setVisible(true);
        //s.get(i)
        txtimg.setText(file.getName());
//            try {             Files.copy(file.toPath(),new File(path+"\\"+file.getName()).toPath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    else {
               System.out.println("Image introuvable");         
    }
    }
    
    public void chercherI2 (ActionEvent event) throws FileNotFoundException, IOException  {
    
    
    
    
    }

    @FXML
    private void selectcat(ActionEvent event) {
        String s = niv.getSelectionModel().getSelectedItem();
        nameCat = s ;
    }
//
//    private void backClient(ActionEvent event) {
//           try {
//                Parent page1 = FXMLLoader.load(getClass().getResource("/views/InscriptionForm.fxml"));
//                Scene scene = new Scene(page1);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//                Logger.getLogger(ACCUEILController.class.getName()).log(Level.SEVERE, null, ex);
//            }
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
                Logger.getLogger(ACCUEILController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                Logger.getLogger(ACCUEILController.class.getName()).log(Level.SEVERE, null, ex);
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



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */







