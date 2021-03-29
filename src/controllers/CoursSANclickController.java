/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Iservice.MyListenerF;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class CoursSANclickController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label desc;
    @FXML
    private ImageView img;
private Cours c;
private MyListenerF myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData(Cours c, MyListenerF myListener) {
        this.c  = c;
        this.myListener = myListener;
        titre.setText(c.getTitre());
        desc.setText((c.getDescription()));
      File newFile = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + c.getFile());

        img.setImage(new Image(newFile.toURI().toString()));
       
    }
}
