/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Formation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
public class AfficherForClickController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Label desc;
    /**
     * Initializes the controller class.
     */
     private MyListenerF myListener;
      private Formation f;
   
  
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
        nameLabel.setText(f.getTitre());
        priceLable.setText((f.getPrix())+"DT");
        desc.setText(f.getDescription());
      File newFile = new File("C:\\xampp\\htdocs\\Formation\\Images" + f.getImage());

      img.setImage(new Image(newFile.toURI().toString()));
        
//        try{
//        String fileName = "C:\\xampp\\htdocs\\Formation\\Images";
//         String website = "http://localhost/Formation/Images"+fileName;
//         
//         System.out.println("Downloading File From: " + website);
//         
//        URL url = new URL(website);
//         InputStream inputStream = url.openStream();
//         OutputStream outputStream = new FileOutputStream(fileName);
//         byte[] buffer = new byte[2048];
//         
//         int length = 0;
//         
//         while ((length = inputStream.read(buffer)) != -1) {
//            System.out.println("Buffer Read of length: " + length);
//            outputStream.write(buffer, 0, length);
//         }
//         
//         inputStream.close();
//         outputStream.close();
//         
//      } catch(Exception e) {
//         System.out.println("Exception: " + e.getMessage());
//      }
       
    }
}
