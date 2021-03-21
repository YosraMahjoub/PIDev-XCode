/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xcode.Iservice.MyListener;
import xcode.entity.Oeuvre;
import xcode.service.OeuvrageService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AdminconsulterOeuvreController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label nomO;
    @FXML
    private Label prixo;
    @FXML
    private ImageView imgO;
    @FXML
    private Label descO;
    @FXML
    private Button affd;
    @FXML
    private Button supprimerO;
    @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
    @FXML
    private Button dashboard;
    @FXML
    private HBox taper;

   
    
    
    
    private MyListener myListener;
    OeuvrageService os = new OeuvrageService();
    private Oeuvre oi;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Oeuvre> listOeuvre =new ArrayList<>();
           listOeuvre.addAll(os.afficherLOV());
           
           
           if (listOeuvre.size() > 0) {
               System.out.println(listOeuvre.get(0));
                setChosenO(listOeuvre.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                        setChosenO(oeuvre);
                    }
                };
            }
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/xcode/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);

                if (column == 3) {
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
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }

        
        
    }    

        private void setChosenO(Oeuvre o) {
        nomO.setText(o.getNom());
        prixo.setText((o.getPrix())+"DT");
        descO.setText(o.getDescription());
                File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + o.getImg());

        //image = new Image(getClass().getResourceAsStream(o.getImg()));
        imgO.setImage(new Image(newFile2.toURI().toString()));
        oi=o;
       
    }    

    @FXML
    private void dashbord(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/xcode/views/AdminOeuvrenotif.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
       private Optional<ButtonType> alert(String deux)
    {
        Alert alert = new Alert( Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Suppression");      
        alert.setContentText(deux);
        return alert.showAndWait();
    }
    @FXML
    private void supprimer(ActionEvent event) {
    if(alert("Voulez vous supprimer").get()==ButtonType.OK)
       {
           os.supprimerO(oi);
           try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/xcode/views/adminconsulterOeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }  
       }
    }

    @FXML
    private void rechercher(ActionEvent event) {
        List<Oeuvre> listOeuvre =new ArrayList<>();
       listOeuvre.addAll(os.afficherLOV());
        boolean a = listOeuvre.stream().anyMatch(o -> o.getNom().equalsIgnoreCase(recho.getText()));
        System.out.println(a);
          
        if (recho.getText().isEmpty()) {
           recho.setPromptText("veuillez remplir ce champs d'abord");
           recho.setStyle("-fx-text-inner-color: black;");
        }
        
        else if (a!=true) {
            int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/xcode/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);

                if (column == 3) {
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
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Aucun oeuvre avec ce nom");
            alert.showAndWait();
            
        }
        else {
            List<Oeuvre> rechOeuvre;
            rechOeuvre =  listOeuvre.stream().filter(o -> o.getNom().equalsIgnoreCase(recho.getText())).collect(Collectors.toList());
            System.out.println(rechOeuvre.size() );
            
           if (rechOeuvre.size() > 0) {
               System.out.println(rechOeuvre.get(0));
               
                setChosenO(rechOeuvre.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                        setChosenO(oeuvre);
                    }
                };
            }
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < rechOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/xcode/views/afficherOeuvre.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOeuvreController itemController = fxmlLoader.getController();
                itemController.setData(rechOeuvre.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); 

                //(child,column,row)
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
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
    }

    @FXML
    private void afficherd(ActionEvent event) {
        
        try {
            AdmindetailoeuvreController.setOeuvre(oi);
            Parent page1 = FXMLLoader.load(getClass().getResource("/xcode/views/admindetailoeuvre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminconsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       
    }
    
}
