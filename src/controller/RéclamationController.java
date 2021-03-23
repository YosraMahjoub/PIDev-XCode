/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Reclamation;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import static java.util.Locale.filter;
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
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RéclamationController implements Initializable {

    @FXML
    private Button btngérer;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnrole;
    @FXML
    private Button btnformation;
    @FXML
    private Button btneven;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button btnfavories;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btninfo;
    @FXML
    private Button btnsupprimer1;
    @FXML
    private Button reclamation;
    private ObservableList<Reclamation> reclamations=FXCollections.observableArrayList();

    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> sujet;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, Date> date;
    @FXML
    private TableView<Reclamation> reclamationTable;
    @FXML
    private ComboBox<String> filter;
    @FXML
    private Button ajouter_reclamation;
    @FXML
    private Button supp_reclamation;
    @FXML
    private Button modif_reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> cat = FXCollections.observableArrayList("evenements", "formations","oeuvres","tous les réclamations");
        filter.setItems(cat);
        ReclamationService recla = new ReclamationService();
        reclamations=(ObservableList<Reclamation>) recla.displayAll();
        reclamationTable.setItems(reclamations);
        
        nom.setCellValueFactory(new PropertyValueFactory<>("reclamation_nom"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
       
        reclamationTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                
                Reclamation r = reclamationTable.getSelectionModel().getSelectedItem();
                int i = r.getReclamation_id();
                ModifierréclamationController.setI(i);
                
                System.out.println(r.getReclamation_id());
                supp_reclamation.setOnAction(event1 -> {
                recla.delete(r);
        });
                modif_reclamation.setOnAction(event1 -> {
                 try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/modifierréclamation.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });}
        });
        ajouter_reclamation.setOnAction(event -> {
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ajoutreclamation.fxml"));
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
           reclamationTable.setItems(reclamations);
            
        }
        if(x=="evenements"){
            String statut = "evenement_id";
            reclamations=(ObservableList<Reclamation>) pdao.displaystatut(statut);
            reclamationTable.setItems(reclamations);
        }
        if(x=="oeuvres"){
            String statut = "oeuvrage_id";
           reclamations=(ObservableList<Reclamation>) pdao.displaystatut(statut);
           reclamationTable.setItems(reclamations);
        }
        if(x=="tous les réclamations"){
            reclamations=(ObservableList<Reclamation>) pdao.displayAll();
           reclamationTable.setItems(reclamations);
        }
        
    
    } 
    
}
