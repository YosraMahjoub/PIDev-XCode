/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entities.Cours;
import entities.Formation;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author HELA
 */
public interface MyListenerF {
      public void onClickListener(MouseEvent event ,Formation f);
      public void onClickListener(MouseEvent event ,Cours cours);
    
}
