/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xcode.Iservice.MyListener;
import xcode.entity.Oeuvre;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AdminaffOController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    
     private Oeuvre oeuvre;
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
    }    

    @FXML
    private void click(MouseEvent event) {
    
    myListener.onClickListener(event,oeuvre);
     
                    }
           
    
    public void setData(Oeuvre oeuvre, MyListener myListener) {
        this.oeuvre  = oeuvre;
        this.myListener = myListener;
        nameLabel.setText(oeuvre.getNom());
        priceLable.setText((oeuvre.getPrix())+"DT");
                    File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + oeuvre.getImg());

        img.setImage(new Image(newFile2.toURI().toString()));
       
    } 
}
