/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Relations;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.RelationsService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilController implements Initializable {

    @FXML
    private Label label_nom;
    @FXML
    private Label label_bio;
    @FXML
    private Label label_role;
    @FXML
    private Label label_domaine;
    @FXML
    private Button portfolio;
    @FXML
    private Button sabonner;
    @FXML
    private Label label_nbarticle;
    @FXML
    private Label label_jaime;
    @FXML
    private Label label_abonnees;
     private static int i;

    static void setUser(int ia) {
       i=ia;}
    static int getUser(){
        return i;
    }
        
    
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private ImageView img;
    @FXML
    private TextField imgpath;
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
        RelationsService rela = new RelationsService();
        ObservableList<Relations> list=FXCollections.observableArrayList();
        UserService pdao = new UserService();
        
        User u = pdao.displayById(i);
        System.out.println(u.getUser_id());
        Relations o = new Relations(UserService.getCurrentUser().getUser_id(),u.getUser_id());
        label_nom.setText(u.getUsername());
        label_bio.setText(u.getBio());
        label_role.setText(pdao.displayrole(u.getUsername()));
        img.setImage(new Image("http://localhost/PI/IMG/" +u.getImage()));
        list = rela.displayByfolloweeId(u.getUser_id());
       
        int x = rela.existfollow(o);
        int y = rela.nbfollowerByfolloweeId(u.getUser_id());
        label_abonnees.setText(""+y);
        //if(x!=0){sabonner.setText("abonné");} else{ sabonner.setText("s'abonner");}
           
            sabonner.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                    int y=0;
                    int x = rela.existfollow(o);
                    if(x!=0){
                        sabonner.setText("abonné");
                        rela.delete(o);
                        sabonner.setText("s'abonner");
                        y = rela.nbfollowerByfolloweeId(u.getUser_id());
                        label_abonnees.setText(""+y);
                        
                    }
                    else{
                    rela.insert(o);
                    sabonner.setText("abonné");
                    y = rela.nbfollowerByfolloweeId(u.getUser_id());
                   
                    label_abonnees.setText(""+y);
                    
            }
                
            }
                
            });
           
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
