/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author Mega-PC
 */
public class ElementPanier {
    private Oeuvre oeuv;
    private int quantite;

    public Oeuvre getOeuv() {
        return oeuv;
    }

    public void setOeuv(Oeuvre oeuv) {
        this.oeuv = oeuv;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public ElementPanier() {
    }

    public ElementPanier(Oeuvre oeuv, int quantite) {
        this.oeuv = oeuv;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ElementPanier{" + "oeuv=" + oeuv + ", quantite=" + quantite + '}';
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
        final ElementPanier other = (ElementPanier) obj;
        if (this.oeuv.getOeuvrage_id()!=other.getOeuv().getOeuvrage_id()) {
            return false;
        }
        return true;
    }

    
    
    
}
