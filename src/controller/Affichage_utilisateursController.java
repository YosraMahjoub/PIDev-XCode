/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService pdao = new UserService();
        List<User> listUser =new ArrayList<>();
        
        listUser.addAll(pdao.displayAll());
        
        if (listUser.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/view/profil.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listUser.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/view/affuser.fxml"));
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
    private void vendeur(ActionEvent event) {
        UserService pdao= new UserService();
        List<User> listV =new ArrayList<>();
            listV.addAll(pdao.displayvendeurs());
            if (listV.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/view/profil.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listV.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/view/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
                    itemController.setData(listV.get(i),myListener);
                    

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
    private void formateur(ActionEvent event) {
        
        UserService pdao= new UserService();
            List<User> listF =new ArrayList<>();
            listF.addAll(pdao.displayformateurs());
            if (listF.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/view/profil.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listF.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/view/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
                    itemController.setData(listF.get(i),myListener);

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
    private void recruteur(ActionEvent event) {
        UserService pdao = new UserService();
            List<User> listR =new ArrayList<>();
            listR.addAll(pdao.displayrecruteurs());
            if (listR.size() > 0) {
                
                myListener = new MyListener() {
                    

                    @Override
                    public void onClickListener(MouseEvent event, User u) {
                        try {
                            ProfilController.setUser(u.getUser_id());
                            Parent page1 = FXMLLoader.load(getClass().getResource("/view/profil.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Affichage_utilisateursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
        int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listR.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/view/affuser.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AffuserController itemController = fxmlLoader.getController();
                    itemController.setData(listR.get(i),myListener);

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
