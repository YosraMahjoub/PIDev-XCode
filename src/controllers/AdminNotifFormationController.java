/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Iservice.MyListenerF;
import entities.Cours;
import entities.Formation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import service.FormationServices;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class AdminNotifFormationController implements Initializable {

    @FXML
    private Button home;
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
    private Button btninfo;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnuser;
    @FXML
    private Button stat;
    @FXML
    private Button reclamations;
    @FXML
    private Button btnsupp;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    private MyListenerF myListener;
  //  private List<Formation> listforADMIN =new ArrayList<>();
    private FormationServices fs = new FormationServices();
   
    @FXML
    private MenuItem Val;
    @FXML
    private MenuItem nonValidee;
    @FXML
    private Button stat1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            //            // TODO
            List<Formation> listforADMIN =new ArrayList<>();
            listforADMIN.addAll(fs.readAll());
            if (listforADMIN.size() > 0) {
                System.out.println(listforADMIN.get(0));
                myListener = new MyListenerF() {
                    @Override
                    public void onClickListener(MouseEvent event, Formation f) {
                        try {
                            ValidationAdmiController.setF(f);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ValidationAdmin.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        }
                        catch (IOException ex) {
                            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    @Override
                    public void onClickListener(MouseEvent event, Cours cours) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                    
                };
                
                
            }
            
            grid.getChildren().clear();
            int column = 0;
            int row = 1;
            for (int i = 0; i < listforADMIN.size(); i++) {
                
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/views/Adminclick.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    AdminclickController itemController = fxmlLoader.getController();
                    try {
                        itemController.setData(listforADMIN.get(i),myListener);
                    } catch (Exception ex) {
                        Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row);
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
@FXML
    private void gotoemploi(ActionEvent event) {
    }    

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
         try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminconsulterOeuvre.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
        
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
        try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/adminprofil.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    }


    @FXML
    private void deconnecter(ActionEvent event) {
        try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AdmindetailoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    }

   @FXML
    private void accueil(ActionEvent event) {
        try {
                                Parent page1 = FXMLLoader.load(getClass().getResource("/views/AccueilFront.fxml"));
                                Scene scene = new Scene(page1);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AdmindetailoeuvreController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    }
 @FXML
    private void gotoevents(ActionEvent event) {
    }
    @FXML
    private void gotoform(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/AdminNotifFormation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void statistics(ActionEvent event) {
        
      
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/StatDomaine.fxml"));
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene(root));
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                      
    }

        @FXML
        private void nonValidees(ActionEvent event) {
     List<Formation> listforADMIN =new ArrayList<>();
               listforADMIN.addAll(fs.afficherForAdmin());
        if (listforADMIN.size() > 0) {
               System.out.println(listforADMIN.get(0));
               myListener = new MyListenerF() {
                   @Override
                   public void onClickListener(MouseEvent event, Formation f) {
                          try {
                            ValidationAdmiController.setF(f);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ValidationAdmin.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } 
                          catch (IOException ex) {
                            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   } 

                   @Override
                   public void onClickListener(MouseEvent event, Cours cours) {
                       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }
               
               };
    
  }
       grid.getChildren().clear();
        int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < listforADMIN.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/Adminclick.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();
                   AdminclickController itemController = fxmlLoader.getController();
                itemController.setData(listforADMIN.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
              
                } catch (IOException ex) {
                   Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (Exception ex) {
            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void validees(ActionEvent event) {
    List<Formation> listforADMIN =new ArrayList<>();
                     listforADMIN.addAll(fs.afficherForClient());
        if (listforADMIN.size() > 0) {
               System.out.println(listforADMIN.get(0));
               myListener = new MyListenerF() {
                   @Override
                   public void onClickListener(MouseEvent event, Formation f) {
                          try {
                            ValidationAdmiController.setF(f);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/ValidationAdmin.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } 
                          catch (IOException ex) {
                            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   } 

                   @Override
                   public void onClickListener(MouseEvent event, Cours cours) {
                       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }
               
               };
    
    
                       }
       grid.getChildren().clear();
        int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < listforADMIN.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/Adminclick.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();
                   AdminclickController itemController = fxmlLoader.getController();
                itemController.setData(listforADMIN.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
              
                } catch (IOException ex) {
                   Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (Exception ex) {
            Logger.getLogger(AdminNotifFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                    }}
        
    

    
    
    

