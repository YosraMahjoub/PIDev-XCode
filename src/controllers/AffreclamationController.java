/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Iservice.MylistenerR;
import entities.Reclamation;
import entities.User;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffreclamationController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label sujet;
    @FXML
    private Label description;
    @FXML
    private Label date;
    private MylistenerR myListener;
    private Reclamation r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(event,r);
    }
    void setData(Reclamation r, MylistenerR myListener) {
        this.r=r;
        this.myListener=myListener;
        nom.setText(r.getReclamation_nom());
        sujet.setText(r.getSujet());
        description.setText(r.getDescription());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(r.getDate());       
        date.setText(strDate);
        
    }
    
    
}
