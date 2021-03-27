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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import Iservice.MyListener;
import entities.Oeuvre;


/**
 * FXML Controller class
 *
 * @author pc
 */
public class AfficherOeuvreController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    
    
    private Oeuvre oeuvre;
    private MyListener myListener;
    @FXML
    private Label valido;
    @FXML
    private AnchorPane anchorpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        if (oeuvre.getIsvalid()==1){
            valido.setText("Quantité : "+ String.valueOf(oeuvre.getQuantite()));
        }
        if (oeuvre.getIsvalid()==0){
            valido.setText("en attente");
            valido.setTextFill(Color.rgb(0xa6, 0x59, 0x59));
        }
        if (oeuvre.getIsvalid()==2){
            valido.setText("n'est pas validé");
            anchorpane.setStyle("-fx-background-color: #C3C0C0");
        }

        img.setImage(new Image(newFile2.toURI().toString()));
        img.setFitHeight(150);
        img.setFitWidth(150);
    } 



}
