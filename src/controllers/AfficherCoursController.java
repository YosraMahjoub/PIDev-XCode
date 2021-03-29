/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import entities.Formation;
import entities.Inscription;
import service.CoursServices;
import service.FormationServices;
import service.InscriptionsServices;
import service.TextFileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import controllers.ACCUEILController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class AfficherCoursController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Label desc;
    @FXML
    private ImageView file;
    @FXML
    private Label titre;
    @FXML
    private Label duree;
    @FXML
    private Label niveau;

    /**
     * Initializes the controller class.
     */
    private static Formation a;
     static Cours aa = new Cours();
    //  FormationServices fs = FormationServices.getInstance();

    public static void setF(Formation f) {
        a = f;
    }

    public static void setC(Cours fc) {
        aa = fc;
    }
     private TextFileReader reader = new TextFileReader();
    private static final Logger LOG = Logger.getLogger(AfficherCoursController.class.getName());
    @FXML 
	private TextArea linesTextArea;
    @FXML 
            Button showFile;
    @FXML
    TextField  urlTextField;
        private Future<List<String>> future;
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CoursServices cs = new CoursServices();
        List<Cours> cours = new ArrayList();
        cours.addAll(cs.readAll(a));

        //  TODO
        //tkharajli cours nafsou 
//        for (int i = 0; i < cours.size(); i++) {
//            File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + cours.get(i).getFile());
//            file.setImage(new Image(newFile2.toURI().toString()));
//            desc.setText(cours.get(i).getDescription());
//            titre.setText(cours.get(i).getTitre());
//            duree.setText(cours.get(i).getDuree());
//            niveau.setText(cours.get(i).getNiveau());
//        }
        // tkharajli cours nafsou 
//        File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + cours.get(0).getFile());
//        file.setImage(new Image(newFile2.toURI().toString()));
//        desc.setText(cours.get(0).getDescription());
//        titre.setText(cours.get(0).getTitre());
//        duree.setText(cours.get(0).getDuree());
//        niveau.setText(cours.get(0).getNiveau());

// tekhdemsh 
  File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + aa.getFile());
        file.setImage(new Image(newFile2.toURI().toString()));
        
        desc.setText(aa.getDescription());
        titre.setText(aa.getTitre());
        duree.setText(aa.getDuree());
        niveau.setText(aa.getNiveau());
      //  linesTextArea.setText(aa.getFile());
      urlTextField.setText(aa.getFile());
      
    }
    @FXML
	@SuppressWarnings("NestedAssignment")
	public void showFileLines(ActionEvent event) throws InterruptedException, ExecutionException {
            
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AfficherCoursFiles.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }

//             try {
//                 
//            FXMLLoader page1 = FXMLLoader.load(getClass().getResource("/View/AfficherCoursFiles.fxml"));
//           // Scene scene = new Scene(page1);
//           Parent root = (Parent) page1.load();
//            Stage stage =  new Stage();
//                    //(Stage) ((Node) event.getSource()).getScene().getWindow();
////            stage.initStyle(StageStyle.DECORATED);
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(AfficherCoursController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//		future = executorService.submit(new Callable<List<String>>() {
//			public List<String> call() throws Exception {
//				return reader.read(new File ("C:\\xampp\\htdocs\\PI\\IMG\\" + aa.getFile())
//                                );
//			}
//		});
//		
//		List<String> lines = future.get();
//		executorService.shutdownNow();
//		linesTextArea.clear();
//		for (String line : lines ) {
//			linesTextArea.appendText(line + "\n");
//		}
        
        }

    @FXML
    private void backFor(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/CoursListe.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @FXML
//    private void AjouerFormationController(ActionEvent event) {
//          
//          try {
//                Inscription i = new Inscription();
//                InscriptionsServices in =new InscriptionsServices();
//                in.ajouter(i);
//               
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Inscription Dialog");
//                alert.setHeaderText(null);
//                alert.setContentText("Welcome to the course !");
//                alert.show();
//                
//            } catch (SQLException ex) {
//                Logger.getLogger(AfficherCoursController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
}
