/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xcode.IService.MyListener;
import xcode.entity.Commande;
import xcode.entity.ElementPanier;
import xcode.entity.Facture;
import xcode.entity.PDFAP;
import xcode.entity.PanierHolder;

import xcode.services.cmdservices;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */



public class PanierController implements Initializable {

    @FXML
    private Button Valider;
    @FXML 
    private Button Supprimer;
    @FXML 
    private Button Retour;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label nomC;
    @FXML
    private Label prixC;
    @FXML
    private ImageView imgC;

    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
    
    private ElementPanier fi;
    
    private Image image;
    
    private PDFAP pdf;
    private Commande oi;
    private MyListener MyListener;
    private List<ElementPanier> ListF =new ArrayList<>();
    cmdservices os = new cmdservices();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           ListF=PanierHolder.getInstance().getEP();
           if (ListF.size() > 0) {
               System.out.println(ListF.get(0));
               setChosenF(ListF.get(0));
                MyListener = new MyListener() {
                    @Override
                    public void onClickListener(ElementPanier facture) {

                        setChosenF(facture);
                    }
                };
            }
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < ListF.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("Oeuvrees.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    OeuvreesController itemController = fxmlLoader.getController();
                itemController.setData(ListF.get(i),MyListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                
                
                
                } catch (IOException ex) {
                   Logger.getLogger(AjouterOeuvController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }

        private void setChosenF(ElementPanier f) {
            nomC.setText(f.getOeuv().getNom());
            prixC.setText((f.getOeuv().getPrix())+"DT");
            //descO.setText(f.getDescription());
                File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + f.getOeuv().getImage());

             //image = new Image(getClass().getResourceAsStream(o.getImg()));
            imgC.setImage(new Image(newFile2.toURI().toString()));
             fi=f;
    }    
    
    
    

     @FXML
    private void DeletePanier(ActionEvent event) {
            List<ElementPanier> listEOp=PanierHolder.getInstance().getEP();
                ElementPanier ep=new ElementPanier();

     
        boolean existsElem=false;
        int i;
        for(i=0;i<listEOp.size();i++)
        {
            if(listEOp.get(i).getOeuv().equals(oi))
            {
                existsElem=true;
                listEOp.remove(i);
                break;
            }  
            //listEOp.set(i, ep);

            System.out.println("Supprimé"); 
       
        }
    }
    
    
    @FXML
    public void Valider(ActionEvent event) throws IOException{
        List<ElementPanier> listEOp=PanierHolder.getInstance().getEP();        
        cmdservices a =new cmdservices();
        PDFAP f = new PDFAP();
                a.createPanier(1);
                f.PDFCreator();
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Succès !");
            alert.setHeaderText("Validé !");
            alert.setContentText("La commande est bien validé !");
            alert.showAndWait();
            listEOp.clear();
            
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterOeuv.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
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
