/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xcode.DBaseC.ConnectionDB;
import xcode.entity.Commande;
import xcode.entity.Facture;


/**
 *
 * @author Mega-PC
 */
public class cmdservices {
    
    Statement ste;
   
    Connection connection=ConnectionDB.getInstance().getCnx();
   
    
    public void Createcmd(int user_id,int oeuvrage_id, int quantite){
        try {
        String req="INSERT INTO commande (user_id,oeuvrage_id,quantite) VALUES (?,?,?)";
        PreparedStatement pst;
        
            pst = connection.prepareStatement(req);
            pst.setInt(1, user_id);
            pst.setInt(2, oeuvrage_id);
            pst.setInt(3, quantite);
            pst.executeUpdate();
            System.out.println("Ajout effectue avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
    
    
    
   public ObservableList<Facture> getAll(){
       ObservableList<Facture> listFacture = FXCollections.observableArrayList();
        try {
            ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("select c.commande_id, u.nom, o.nom , o.prix, c.quantite, u.user_id, c.user_id, o.oeuvrage_id, c.oeuvrage_id "
                    + "from oeuvrage o, commande c, user u "
                    + "where u.user_id =c.user_id AND c.oeuvrage_id = o.oeuvrage_id AND u.user_id = '1'");
            while (rs.next()) {
                Facture a = new Facture();
                                        a.setCommande_id(rs.getInt(1));
                                        a.setUser(rs.getString(2));
                                        a.setOeuvrage(rs.getString(3));
                                        a.setQuantite(rs.getInt(4));
                                        a.setPrix(rs.getInt(5));
                                        listFacture.add(a);
            }
            ste.close();
        } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFacture;
   }
   
 
   public void Delcmd(int id) {
        try {
            String req= "DELETE FROM `commande` WHERE `commande`.`commande_id` = ? ";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void updateCommande(Commande c) {
            try {
            String req = " UODATE `commande` SET `quantite` WHERE `commande_id` = ? ";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setInt(1,c.getQuantite());
            pst.executeUpdate();
        }
            catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    

}