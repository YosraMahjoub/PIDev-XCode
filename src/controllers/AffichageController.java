/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Formation;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Iservice.MyListenerF;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class AffichageController implements Initializable {

    
        private Formation f;
    private MyListenerF myListener;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Label titre;
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }

    @FXML
    private void click(MouseEvent event) {
           myListener.onClickListener(event,f);
    }
    public void setData(Formation f, MyListenerF myListener) {
        this.f  = f;
        this.myListener = myListener;
        titre.setText(f.getTitre());
        nameLabel.setText(f.getDescription());
        priceLable.setText((f.getPrix())+"DT");
                   // File newFile= new File("C:\\xampp\\htdocs\\Formation\\Images" + f.getImage());
    //File newFile= new File("C:\\xampp\\htdocs\\PI\\IMG" + f.getImage());
        //image = new Image(getClass().getResourceAsStream(o.getImg()));
        img.setImage(new Image("http://localhost/PI/IMG/"+ f.getImage()));
        

    } 
}
        
  
       
    

