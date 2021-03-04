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
public class Facture {
    private int Commande_id;
    private String User;
    private String Oeuvrage;
    private int Quantite;
    private int Prix;

    public Facture() {
    }

    public Facture(int Quantite) {
        this.Quantite = Quantite;
    }

    
    public Facture(int Commande_id, String User, String Oeuvrage, int Quantite, int Prix) {
        this.Commande_id = Commande_id;
        this.User = User;
        this.Oeuvrage = Oeuvrage;
        this.Quantite = Quantite;
        this.Prix = Prix;
    }

    public Facture(int Commande_id, int Quantite) {
        this.Commande_id = Commande_id;
        this.Quantite = Quantite;
    }

    public Facture(String User, String Oeuvrage, int Quantite, int Prix) {
        this.User = User;
        this.Oeuvrage = Oeuvrage;
        this.Quantite = Quantite;
        this.Prix = Prix;
    }

    public int getCommande_id() {
        return Commande_id;
    }

    public void setCommande_id(int Commande_id) {
        this.Commande_id = Commande_id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getOeuvrage() {
        return Oeuvrage;
    }

    public void setOeuvrage(String Oeuvrage) {
        this.Oeuvrage = Oeuvrage;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
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
        final Facture other = (Facture) obj;
        if (this.Commande_id != other.Commande_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Facture{" + "Commande_id=" + Commande_id + ", User=" + User + ", Oeuvrage=" + Oeuvrage + ", Quantite=" + Quantite + ", Prix=" + Prix + '}';
    }
    
    
    
    
    
    
}
