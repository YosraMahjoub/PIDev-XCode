/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Iservice.MylistenerR;
import entities.Reclamation;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.Locale.filter;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Button btnformation;
    @FXML
    private Button btneven;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button btnfavories;
    @FXML
    private Button btninfo;
    @FXML
    private Button reclamation;
    private ObservableList<Reclamation> reclamations=FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> filter;
    @FXML
    private Button ajouter_reclamation;
    @FXML
    private Button supp_reclamation;
    
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private Button mod_reclamation;
    @FXML
    private Button apprentissage;
    @FXML
    private Button btnsupp;
    @FXML
    private GridPane grid;
    private MylistenerR Mylistener;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button profil;
    @FXML
    private Button Deconnexion;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> cat = FXCollections.observableArrayList("evenements", "formations","oeuvres","tous les réclamations");
        filter.setItems(cat);
        ReclamationService recla = new ReclamationService();
        
        List<Reclamation> listrec =new ArrayList<>();
//        
        listrec.addAll(recla.displayAll());
        
        if (listrec.size() > 0) {
                
                Mylistener = new MylistenerR() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, Reclamation r) {
                       
                        int i = r.getReclamation_id();
                        ModifierréclamationController.setI(i);
                        supp_reclamation.setOnAction(event1 -> {
                            recla.delete(r);
                        });
                        mod_reclamation.setOnAction(event1 -> {
                            try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/modifierréclamation.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    
                };
                };}grid.getChildren().clear();
            int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listrec.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affreclamation.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffreclamationController itemController = fxmlLoader.getController();
                    itemController.setData(listrec.get(i),Mylistener);

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        ajouter_reclamation.setOnAction(event -> {
            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/ajoutreclamation.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/Userprofil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btngérer.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/gérer_profil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnmdp.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/changer_mdp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModifierréclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnsupp.setOnAction(event -> {
            UserService pdao = new UserService();
            if (alert("voulez vous vraiment supprimer le compte ?").get() == ButtonType.OK) {
            pdao.updatevalidité(UserService.getCurrentUser().getUser_id());
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }});
    }
        
     private Optional<ButtonType> alert(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer ");
            alert.setHeaderText(null);
            alert.setContentText(x);
            return alert.showAndWait();
    }   

        

    @FXML
    private void comboAction(ActionEvent event) {
         
        ReclamationService pdao = new ReclamationService();
        String x =filter.getValue();
        
        
        if(x=="formations"){
           String statut = "formation_id";
           ReclamationService recla = new ReclamationService();
           
           List<Reclamation> list =new ArrayList<>();
            list.addAll(pdao.displaystatut(statut));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                Mylistener = new MylistenerR() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, Reclamation r) {
                        
                        int i = r.getReclamation_id();
                        ModifierréclamationController.setI(i);
                        supp_reclamation.setOnAction(event1 -> {
                            recla.delete(r);
                        });
                        mod_reclamation.setOnAction(event1 -> {
                            try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/modifierréclamation.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {
                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affreclamation.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffreclamationController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),Mylistener);
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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
            
        
        if(x=="evenements"){
            String statut = "evenement_id";
            ReclamationService recla = new ReclamationService();
           
           List<Reclamation> list =new ArrayList<>();
            list.addAll(recla.displaystatut(statut));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                Mylistener = new MylistenerR() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, Reclamation r) {
                        
                        int i = r.getReclamation_id();
                        ModifierréclamationController.setI(i);
                        supp_reclamation.setOnAction(event1 -> {
                            recla.delete(r);
                        });
                        mod_reclamation.setOnAction(event1 -> {
                            try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/modifierréclamation.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affreclamation.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffreclamationController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),Mylistener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        }
        if(x=="oeuvres"){
            String statut = "oeuvrage_id";
           ReclamationService recla = new ReclamationService();
           
           List<Reclamation> list =new ArrayList<>();
            list.addAll(recla.displaystatut(statut));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                Mylistener = new MylistenerR() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, Reclamation r) {
                        
                        int i = r.getReclamation_id();
                        ModifierréclamationController.setI(i);
                        supp_reclamation.setOnAction(event1 -> {
                            recla.delete(r);
                        });
                        mod_reclamation.setOnAction(event1 -> {
                            try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/modifierréclamation.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affreclamation.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffreclamationController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),Mylistener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        }
        if(x=="tous les réclamations"){
           
           ReclamationService recla = new ReclamationService();
           
           List<Reclamation> list =new ArrayList<>();
            list.addAll(recla.displayAll());
            System.out.println(list.size());
            if (list.size() > 0) {
                
                Mylistener = new MylistenerR() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, Reclamation r) {
                        
                        int i = r.getReclamation_id();
                        ModifierréclamationController.setI(i);
                        supp_reclamation.setOnAction(event1 -> {
                            recla.delete(r);
                        });
                        mod_reclamation.setOnAction(event1 -> {
                            try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/modifierréclamation.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affreclamation.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffreclamationController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),Mylistener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        }
        
    
    } 

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
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

    @FXML
    private void accueil(ActionEvent event) {
    }
    
}
