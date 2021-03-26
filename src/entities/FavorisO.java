/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.User;

/**
 *
 * @author pc
 */
public class FavorisO {
    private int favoris_o_id;
    public Oeuvre o;
    public User u;

    public FavorisO(int favoris_o_id, Oeuvre o, User u) {
        this.favoris_o_id = favoris_o_id;
        this.o = o;
        this.u = u;
    }

    public FavorisO() {
    }

    public int getFavoris_o_id() {
        return favoris_o_id;
    }

    public Oeuvre getO() {
        return o;
    }

    public User getU() {
        return u;
    }

    public void setFavoris_o_id(int favoris_o_id) {
        this.favoris_o_id = favoris_o_id;
    }

    public void setO(Oeuvre o) {
        this.o = o;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    
    
}
