/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;


import javafx.scene.input.MouseEvent;
import entities.Oeuvre;
import javafx.event.ActionEvent;

/**
 *
 * @author pc
 */
public interface MyListener {
      public void onClickListener(MouseEvent event ,Oeuvre oeuvre);
      public void onpressed(ActionEvent  event ,Oeuvre oeuvre);
    
}
