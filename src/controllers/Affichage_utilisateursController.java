/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import service.UserService;
import Iservice.MyListener;
import java.awt.Insets;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Affichage_utilisateursController implements Initializable {

    @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private GridPane grid;
    private MyListener myListener;
    @FXML
    private Button vendeurs;
    @FXML
    private Button formateurs;
    @FXML
    private Button recruteurs;
    @FXML
    private Button tous;
    @FXML
    private HBox hi;
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
    @FXML
    private HBox hello;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao = new UserService();
        List<User> listUser =new ArrayList<>();
        
        listUser.addAll(pdao.displayAllrole());
        
        if (listUser.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/profil.fxml"));
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
                   fxmlLoader.setLocation(getClass().getResource("/views/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
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
    }

    @FXML
    private void vendeur(ActionEvent event) throws IOException {
        
        UserService pdao= new UserService();
        List<User> list =new ArrayList<>();
            list.addAll(pdao.displayvendeurs());
            System.out.println(list.size());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/profil.fxml"));
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
                   fxmlLoader.setLocation(getClass().getResource("/views/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
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

    @FXML
    private void formateur(ActionEvent event) throws IOException {
        
        
        UserService pdao= new UserService();
            List<User> list =new ArrayList<>();
            list.addAll(pdao.displayformateurs());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/profil.fxml"));
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
                   fxmlLoader.setLocation(getClass().getResource("/views/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
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

    @FXML
    private void recruteur(ActionEvent event) throws IOException {
        
        UserService pdao = new UserService();
            List<User> list =new ArrayList<>();
            list.addAll(pdao.displayrecruteurs());
            if (list.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/profil.fxml"));
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
                   fxmlLoader.setLocation(getClass().getResource("/views/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
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

    @FXML
    private void tous(ActionEvent event) {
        UserService pdao = new UserService();
        List<User> listUser =new ArrayList<>();
        
        listUser.addAll(pdao.displayAllrole());
        
        if (listUser.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/profil.fxml"));
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
                   fxmlLoader.setLocation(getClass().getResource("/views/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
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
