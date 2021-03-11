/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mega-PC
 */
public class PanierHolder {
    private List<ElementPanier>  EP;
    private static PanierHolder instance;
    
    public static PanierHolder getInstance(){
        if(instance==null)
            instance=new PanierHolder();
        return instance;
       
    }

    public PanierHolder() {
        this.EP = new ArrayList<>();
    }

    public List<ElementPanier> getEP() {
        return EP;
    }

    public void setEP(List<ElementPanier> EP) {
        this.EP = EP;
    }
    
    
    
    
    
    
}
