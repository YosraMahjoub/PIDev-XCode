/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.entities;


import java.util.List;
import xcode.service.cmdservices;

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
        cmdservices cmds=new cmdservices();
       
        this.EP = cmds.initPanier();
    }


    public List<ElementPanier> getEP() {
        return EP;
    }

    public void setEP(List<ElementPanier> EP) {
        this.EP = EP;
    }
    
    
    public void removeEP(ElementPanier es){
        this.EP.remove(es);
    }
    
    
    
    
    
    
}
