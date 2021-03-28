/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Iservice.MyListener;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffuserController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label username;
    @FXML
    private Label nbr_oeuvre;
    private MyListener myListener;
    private User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
//    public void setData(User u , MyListener mylistener){
//        this.u=u;
//    }
//    
    private void click(MouseEvent event) {
        myListener.onClickListener(event,u);
    }
    void setData(User u, MyListener myListener) {
        this.u=u;
        this.myListener=myListener;
        username.setText(u.getUsername());
        
    }
}
