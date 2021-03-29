/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Relations;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
       i=ia;
        
    }
    @FXML
    private HBox hi;
    @FXML
    private Button oeuvres;
    @FXML
    private HBox hello;
    @FXML
    private Button ADD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RelationsService rela = new RelationsService();
        ObservableList<Relations> list=FXCollections.observableArrayList();
        UserService pdao = new UserService();
        String username ="";
        User u = pdao.displayusername(username);
        Relations o = new Relations(u.getUser_id(), UserService.getCurrentUser().getUser_id());
        label_nom.setText(u.getUsername());
        label_bio.setText(u.getBio());
        label_role.setText(pdao.displayrole(username));
        list = rela.displayByfolloweeId(u.getUser_id());
        label_abonnees.setText(""+list.size());
        int x = rela.existfollow(o);
        if(x!=0){
            sabonner.setText("abonné");
            sabonner.setOnAction(event -> {
            rela.delete(o);
            sabonner.setText("s'abonner");
        });
        }
        sabonner.setOnAction(event -> {
            rela.insert(o);
            sabonner.setText("abonné");
        });
        
    }    
    
}
