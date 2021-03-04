/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.entity;


/**
 *
 * @author Mega-PC
 */
public class Commande {
    private int commande_id;
    private int user_id;
    private int oeuvrage_id;
    private int quantite;

    public Commande() {
    }

    public Commande(int commande_id, int quantite) {
        this.commande_id = commande_id;
        this.quantite = quantite;
    }
    
    

    public Commande(int commande_id, int user_id, int oeuvrage_id, int quantite) {
        this.commande_id = commande_id;
        this.user_id = user_id;
        this.oeuvrage_id = oeuvrage_id;
        this.quantite = quantite;
    }

    public Commande(int user_id, int oeuvrage_id, int quantite) {
        this.user_id = user_id;
        this.oeuvrage_id = oeuvrage_id;
        this.quantite = quantite;
    }

    public Commande(int Quantite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
        final Commande other = (Commande) obj;
        if (this.commande_id != other.commande_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "commande_id=" + commande_id + ", user_id=" + user_id + ", oeuvrage_id=" + oeuvrage_id + ", quantite=" + quantite + '}';
    }

    
    }
