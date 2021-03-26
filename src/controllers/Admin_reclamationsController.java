/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ReclamationService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Admin_reclamationsController implements Initializable {

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
    private Button btnprofil1;
    @FXML
    private Button btnoeuvres1;
    @FXML
    private ComboBox<String> filter;
    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> sujet;
    @FXML
    private TableColumn<Reclamation, String> description;
    private TableView<Reclamation> reclamationTable;
    private ObservableList<Reclamation> reclamations=FXCollections.observableArrayList();
    @FXML
    private TableView<Reclamation> reclamationsTable;
    @FXML
    private TableColumn<Reclamation, Date> date;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> cat = FXCollections.observableArrayList("evenements", "formations","oeuvres","tous les réclamations");
        filter.setItems(cat);
        ReclamationService recla = new ReclamationService();
        reclamations =(ObservableList<Reclamation>)recla.displayAll();
        reclamationsTable.setItems(reclamations);
        
        nom.setCellValueFactory(new PropertyValueFactory<>("reclamation_nom"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        reclamationsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                
                Reclamation r = reclamationsTable.getSelectionModel().getSelectedItem();
                int i = r.getReclamation_id();
                Admin_reclamationdetailsController.setI(i);
                try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admin_reclamationdetails.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
       
    });
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
                }
                    

    @FXML
    private void comboAction(ActionEvent event) {
        ReclamationService pdao = new ReclamationService();
        String x =filter.getValue();
        
        
        if(x=="formations"){
            String statut = "formation_id";
           reclamations=(ObservableList<Reclamation>) pdao.displaystatut(statut);
           reclamationsTable.setItems(reclamations);
            
        }
        if(x=="evenements"){
            String statut = "evenement_id";
            reclamations=(ObservableList<Reclamation>) pdao.displaystatut(statut);
            reclamationsTable.setItems(reclamations);
        }
        if(x=="oeuvres"){
            String statut = "oeuvrage_id";
           reclamations=(ObservableList<Reclamation>) pdao.displaystatut(statut);
           reclamationsTable.setItems(reclamations);
        }
        if(x=="tous les réclamations"){
            reclamations=(ObservableList<Reclamation>) pdao.displayAll();
           reclamationsTable.setItems(reclamations);
        }
    }
    
}
