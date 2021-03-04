/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.entity;

import java.util.Objects;

/**
 *
 * @author Mega-PC
 */
public class Oeuvrage {
    private int oeuvrage_id;
    private int user_id;
    private String nom;
    private String domaine;
    private float prix;
    private float quantite;
    private String description;
    
    public Oeuvrage(){
        
    }

    public Oeuvrage(int oeuvrage_id, int user_id, String nom, String domaine, float prix, float quantite, String description) {
        this.oeuvrage_id = oeuvrage_id;
        this.user_id = user_id;
        this.nom = nom;
        this.domaine = domaine;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
    }

    public Oeuvrage(int user_id, String nom, String domaine, float prix, float quantite, String description) {
        this.user_id = user_id;
        this.nom = nom;
        this.domaine = domaine;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
    }

    public int getOeuvrage_id() {
        return oeuvrage_id;
    }

    public void setOeuvrage_id(int oeuvrage_id) {
        this.oeuvrage_id = oeuvrage_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        final Oeuvrage other = (Oeuvrage) obj;
        if (this.oeuvrage_id != other.oeuvrage_id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Oeuvrage{" + "oeuvrage_id=" + oeuvrage_id + ", user_id=" + user_id + ", nom=" + nom + ", domaine=" + domaine + ", prix=" + prix + ", quantite=" + quantite + ", description=" + description + '}';
    }
    
    


}
