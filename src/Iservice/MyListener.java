/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entities.User;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author asus
 */
public interface MyListener {
      public void onClickListener(MouseEvent event ,User u);
    
}