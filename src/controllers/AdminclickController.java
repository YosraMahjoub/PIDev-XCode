/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Download;
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
public class AdminclickController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
      private MyListenerF myListener;
      private Formation f;
    @FXML
    private Label nameLabel1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
            myListener.onClickListener(event,f);
    }
        
    public void setData(Formation f, MyListenerF myListener) throws Exception {
        this.f  = f;
        this.myListener = myListener;
        nameLabel.setText(f.getTitre());
        nameLabel1.setText(f.getDescription());
        priceLable.setText((f.getPrix())+"DT");
//        Download d = new Download("C:\\xampp\\htdocs\\Formation\\Images","http://localhost/Formation/Images/");
//    d.doInBackground();
   // img.setImage(new Image(f.getImage()));
     File newFile = new File("C:\\xampp\\htdocs\\PI\\IMG" + f.getImage());

    img.setImage(new Image(newFile.toURI().toString()));
       
    }
}
