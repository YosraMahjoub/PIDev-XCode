/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xcode.entity.Commande;
import xcode.entity.Facture;
import xcode.services.cmdservices;
import xcode.services.oeuvrageservice;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class CommandeController implements Initializable {
//Configuration tab
    @FXML
    private AnchorPane Cmd;
    @FXML 
    private TableView<Facture> Panier;
    @FXML 
    private TableColumn<Facture, String> IDCol;
    @FXML 
    private TableColumn<Facture, String> ClientCol;
    @FXML 
    private TableColumn<Facture, String> OeuvCol;
    @FXML 
    private TableColumn<Facture, String> QuantCol;
    @FXML 
    private TableColumn<Facture, String> PrixCol;
    
    @FXML
    private Button Afficher;
    @FXML
    private Button Valider;
    @FXML 
    private Button Supprimer;
    @FXML
    private TextField Total;
   @FXML 
    private Button Retour;
   @FXML
    private Button Modifier;
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void Afficher(ActionEvent event) throws SQLException {
        cmdservices f =new cmdservices();
        
               
        List<Facture> list = f.getAll();  
        
        IDCol.setCellValueFactory(new PropertyValueFactory<>("commande_id"));
        
        ClientCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        OeuvCol.setCellValueFactory(new PropertyValueFactory<>("oeuvrage"));  
        
        QuantCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));        
        PrixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
          
        Panier.setItems(null);
        Panier.setItems((FXCollections.observableArrayList(list))); 
    }
   
    
    
     @FXML
        public void Supprimer(ActionEvent event) throws SQLException{
        cmdservices a =new cmdservices();
        int d = (int) Panier.getSelectionModel().getSelectedItem().getCommande_id();
        a.Delcmd(d);         
        Alert alert  =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("suppression du commande ");
        alert.setHeaderText("Supprimé!");
        alert.setContentText("La commande supprimé !");
        alert.showAndWait();
        cmdservices s =new cmdservices();
        List<Facture> list = s.getAll();  
        Panier.setItems((FXCollections.observableArrayList(list))); 
      
        }
    
    
    @FXML
    public void Valider(ActionEvent event){
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Succès !");
            alert.setHeaderText("Validé !");
            alert.setContentText("La commande est bien validé !");
            alert.showAndWait();
        }
    
    @FXML 
    public void Retour(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterOeuv.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
}
    
      
