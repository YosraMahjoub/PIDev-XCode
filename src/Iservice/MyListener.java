/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;



import entities.ElementPanier;
import javafx.scene.input.MouseEvent;
import entities.Oeuvre;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import entities.User;

/**
 *
 * @author pc
 */
public interface MyListener {

      public void onClickListener(MouseEvent event ,Oeuvre oeuvre);
      public void onpressed(ActionEvent  event ,Oeuvre oeuvre);
       public void onClickListener(ElementPanier facture);


      public void onClickListener(MouseEvent event ,User u);

    
}
