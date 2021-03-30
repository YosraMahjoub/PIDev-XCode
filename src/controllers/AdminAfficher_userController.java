/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Iservice.MyListener;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminAfficher_userController implements Initializable {

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
    private ComboBox<String> filter;
    private ObservableList<User> persons=FXCollections.observableArrayList();
    @FXML
    private Button reclamation;
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;
    @FXML
    private Button btnsupp;
    @FXML
     private GridPane grid;
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> cat = FXCollections.observableArrayList("vendeur", "formateur","recruteur","client","validé","non validé","les utilisateurs");
        filter.setItems(cat);
        
        UserService pdao=new UserService();
        List<User> listUser =new ArrayList<>();
//        
        listUser.addAll(pdao.displayAllrole());
        
        if (listUser.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            AdminAfficheruserdetailsController.setI(u.getUser_id());
                            AdminAfficheruserdetailsController.setX(0);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
            int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listUser.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(listUser.get(i),myListener);

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
       
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
        reclamation.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/admin_reclamations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnsupp.setOnAction(event -> {
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
        
        UserService pdao = new UserService();
        String x =filter.getValue();
        if(x=="les utilisateurs"){
           
            List<User> list =new ArrayList<>();
            list.addAll(pdao.displayAll());
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                             AdminAfficheruserdetailsController.setI(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),myListener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
        }
        
        if(x=="vendeur"){
            String statut = "is_vendeur";
            
            List<User> list =new ArrayList<>();
            list.addAll(pdao.displaystatut(statut));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            AdminAfficheruserdetailsController.setI(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),myListener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
        }
        if(x=="formateur"){
            String statut = "is_formateur";
            List<User> list =new ArrayList<>();
            list.addAll(pdao.displaystatut(statut));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                             AdminAfficheruserdetailsController.setI(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),myListener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
        if(x=="recruteur"){
            String statut = "is_recruteur";
            List<User> list =new ArrayList<>();
            list.addAll(pdao.displaystatut(statut));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                             AdminAfficheruserdetailsController.setI(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),myListener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
        if(x=="client"){
            
            List<User> list =new ArrayList<>();
            list.addAll( pdao.displayrole());
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                             AdminAfficheruserdetailsController.setI(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),myListener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
            
        }
        if(x=="validé"){
            int y=1;
            
            List<User> list =new ArrayList<>();
            list.addAll( pdao.displayvalidité(y));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                             AdminAfficheruserdetailsController.setI(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),myListener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
        if(x=="non validé"){
            int y=0;
            
            List<User> list =new ArrayList<>();
            list.addAll( pdao.displayvalidité(y));
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                             AdminAfficheruserdetailsController.setI(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Adminafficheruserdetails.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            } grid.getChildren().clear();
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < list.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/affuserpouradmin.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserpouradminController itemController = fxmlLoader.getController();
                    itemController.setData(list.get(i),myListener);
                    

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
                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
    }
    
}
