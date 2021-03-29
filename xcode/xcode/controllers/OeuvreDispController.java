/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import xcode.IService.Listener;
import xcode.entities.Oeuvrage;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class OeuvreDispController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    
    
    private Oeuvrage oeuvrage;
    private Listener Listener;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void click(MouseEvent event) {
        Listener.onClickListener(oeuvrage);
    }

public void setData(Oeuvrage oeuvrage, Listener Listener) {
        this.oeuvrage  = oeuvrage;
        this.Listener = Listener;
        nameLabel.setText(oeuvrage.getNom());
        priceLable.setText((oeuvrage.getPrix())+"DT");
                    File newFile2 = new File("C:\\Users\\Mega-PC\\Desktop\\XCode\\src\\xcode\\img\\"+oeuvrage.getImage());

        //image = new Image(getClass().getResourceAsStream(o.getImg()));
        img.setImage(new Image(newFile2.toURI().toString()));
        
//        Image image = new Image(getClass().getResourceAsStream(oeuvre.getImg()));
//        img.setImage(image);
    }    
    
}
