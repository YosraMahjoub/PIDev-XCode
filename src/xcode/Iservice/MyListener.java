/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.Iservice;


import javafx.scene.input.MouseEvent;
import xcode.entity.Oeuvre;

/**
 *
 * @author pc
 */
public interface MyListener {
      public void onClickListener(MouseEvent event ,Oeuvre oeuvre);
    
}
