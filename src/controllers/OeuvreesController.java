/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Iservice.MyListener;
import entities.Commande;
import entities.ElementPanier;
import entities.Oeuvre;
import entities.User;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class OeuvreesController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label QteLabel;
    @FXML
    private Label priceTLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;  
    
    

    private ElementPanier elementPanier;
    private MyListener MyListener;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void click(MouseEvent event) {
        MyListener.onClickListener(elementPanier);
    }

public void setData(ElementPanier facture, MyListener MyListener) {
        float prixTotal= facture.getOeuv().getPrix()*facture.getQuantite();
        this.elementPanier  = facture;
        this.MyListener = MyListener;
        nameLabel.setText(facture.getOeuv().getNom());
        priceLable.setText((facture.getOeuv().getPrix())+"DT");
        QteLabel.setText((facture.getQuantite()+""));
        priceTLabel.setText((prixTotal)+"");
        
                    File newFile2 = new File("C:\\Users\\Mega-PC\\Desktop\\XCode\\src\\img\\"+facture.getOeuv().getImg());

        //image = new Image(getClass().getResourceAsStream(o.getImg()));
        img.setImage(new Image("http://localhost/PI/IMG/"+ facture.getOeuv().getImg()));
        
//        Image image = new Image(getClass().getResourceAsStream(oeuvre.getImg()));
//        img.setImage(image);
    }    
    
    
}
