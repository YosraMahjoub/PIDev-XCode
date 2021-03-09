/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Afficher_userController implements Initializable {

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
    private ComboBox<String> filter;
    private ObservableList<User> persons=FXCollections.observableArrayList();
    @FXML
    private TableView<User> personsTable;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> cat = FXCollections.observableArrayList("vendeur", "formateur","recruteur","client","validé","non validé");
        filter.setItems(cat);
        
        UserService pdao=new UserService();
        
        //personsTable.setItems(persons);
        id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        persons=(ObservableList<User>) pdao.displayAll();
        personsTable.setItems(persons);
        
        
        personsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                
                User u = personsTable.getSelectionModel().getSelectedItem();
                System.out.println(u.getNom());
            }
        });
    }
        
        

    @FXML
    private void comboAction(ActionEvent event) {
        
        UserService pdao = new UserService();
        String x =filter.getValue();
        
        if(x=="vendeur"){
            String statut = "is_vendeur";
            persons = (ObservableList<User>) pdao.displaystatut(statut);
            personsTable.setItems(persons);
            
        }
        if(x=="formateur"){
            String statut = "is_formateur";
            persons =(ObservableList<User>)  pdao.displaystatut(statut); 
            personsTable.setItems(persons);
        }
        if(x=="recruteur"){
            String statut = "is_recruteur";
           persons = (ObservableList<User>) pdao.displaystatut(statut);  
           personsTable.setItems(persons);
        }
        if(x=="client"){
            persons =(ObservableList<User>)  pdao.displayrole();  
            personsTable.setItems(persons);
        }
        if(x=="validé"){
            int i=1;
            persons = (ObservableList<User>) pdao.displayvalidité(i);
            personsTable.setItems(persons);
        }
        if(x=="non validé"){
            int i=0;
            persons = (ObservableList<User>) pdao.displayvalidité(i);
            personsTable.setItems(persons);
        }
    }
    
}
