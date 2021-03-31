/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Mega-PC
 */
public class paniertemp {
    private int User_id;
    private int oeuvrage_id;
    private int quantite;

    public paniertemp() {
    }

    public paniertemp(int User_id, int oeuvrage_id, int quantite) {
        this.User_id = User_id;
        this.oeuvrage_id = oeuvrage_id;
        this.quantite = quantite;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public int getOeuvrage_id() {
        return oeuvrage_id;
    }

    public void setOeuvrage_id(int oeuvrage_id) {
        this.oeuvrage_id = oeuvrage_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final paniertemp other = (paniertemp) obj;
        if (this.User_id != other.User_id) {
            return false;
        }
        if (this.oeuvrage_id != other.oeuvrage_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paniertemp{" + "User_id=" + User_id + ", oeuvrage_id=" + oeuvrage_id + ", quantite=" + quantite + '}';
    }

    
    
    
}
