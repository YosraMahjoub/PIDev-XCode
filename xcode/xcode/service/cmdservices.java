/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xcode.DBaseC.ConnectionDB;
import xcode.entities.Commande;
import xcode.entities.ElementPanier;
import xcode.entities.Oeuvrage;
import xcode.entities.PanierHolder;
import xcode.entities.paniertemp;


/**
 *
 * @author Mega-PC
 */
public class cmdservices {
    
    Statement ste;
   
    Connection connection=ConnectionDB.getInstance().getCnx();
    
    
   public List<ElementPanier> initPanier() {
        
         
       String req="select * from panier_temp, oeuvrage where panier_temp.user_id=1 and oeuvrage.oeuvrage_id=panier_temp.oeuvrage_id";
        List<ElementPanier> ListF =new ArrayList<>();
        
        try {
           Statement st = connection.createStatement();
        
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                ElementPanier a = new ElementPanier();
                Oeuvrage o=new Oeuvrage();
                o.setOeuvrage_id(rs.getInt("oeuvrage_id"));
                o.setUser_id(rs.getInt("user_id"));
                o.setNom(rs.getString("nom"));
                o.setDomaine(rs.getString("domaine"));
                o.setPrix(rs.getFloat("prix"));
                o.setQuantite(rs.getFloat("quantite"));
                o.setDescription(rs.getString("description"));
                o.setImage(rs.getString("image"));
                a.setOeuv(o);
                a.setQuantite(rs.getInt("quantite"));
                
                                                                              
                                        ListF.add(a);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return ListF;
    
    }
    
           
        public void createPanier(int user_id) { 
        try {
            
            float prixtot=0;
            List<ElementPanier> elementPaniers=PanierHolder.getInstance().getEP();
            
            for ( int i=0;i<elementPaniers.size();i++)
            {
                prixtot+=elementPaniers.get(i).getOeuv().getPrix()*elementPaniers.get(i).getQuantite();
            }
            
            String req = "INSERT INTO commande (user_id,PrixTot,Date) VALUES (?,?,?)";
            
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setInt(1, user_id);
            pst.setFloat(2, prixtot);
            pst.setDate(3, new Date(System.currentTimeMillis()));
            
            if(pst.executeUpdate() >0) 
            {
                System.out.println("added with success");
                ajouterPanierElems();

            }
            else
                System.out.println("failed");
            
        } catch (SQLException ex) {
            System.out.println("exception =="+ex.getMessage());
        }

    }
        
        public void ajouterPanierElems()
        {
            String req="SELECT Max(commande_id) FROM commande";
  
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
              int id_cmd=0;

            if(rs.next())
              id_cmd=rs.getInt(1);
            
               List<ElementPanier> elementPaniers=PanierHolder.getInstance().getEP();
            
            for ( int i=0;i<elementPaniers.size();i++)
            {
           
            String insReq="INSERT INTO panier VALUES (?,?,?)";
            PreparedStatement pst = connection.prepareStatement(insReq);
            pst.setInt(1, id_cmd);
            pst.setInt(2,elementPaniers.get(i).getOeuv().getOeuvrage_id() );
            pst.setInt(3,elementPaniers.get(i).getQuantite());
            
              if(pst.executeUpdate() >0) 
            {
                System.out.println("added with success");

            }
             }
        } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updateOeuvres();
                
                
        }
        public void updatePanierTemp(int user_id,int oeuvrage_id,int quantite)
        {   
            try{
                
            {
                String insreq="UPDATE panier_temp SET quantite=quantite+? WHERE oeuvrage_id=? AND user_id=?";
                PreparedStatement pst = connection.prepareStatement(insreq);
//                pst.setInt(1, elementPaniers.get(i).getQuantite());
                pst.setInt(1, quantite);
                pst.setInt(2, oeuvrage_id);
                pst.setInt(3, user_id);
                

         
                if(pst.executeUpdate() >0) 
                {
                    System.out.println("Panier Temp updated with success");

                }
            }   
                
            }catch (SQLException ex){
                Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        
        public void createPanierTemp(int user_id, int oeuvrage_id, int quantite) { 
        
            try { 
              
                
            String insReq="INSERT INTO `panier_temp`( `user_id`,`oeuvrage_id`,`quantite`) VALUES (?,?,?)";
            PreparedStatement pst = connection.prepareStatement(insReq);
            
            pst.setInt(1, user_id);
            pst.setInt(2, oeuvrage_id );
            pst.setInt(3, quantite);
            
            
              if(pst.executeUpdate() >0) 
            {
                System.out.println("added To panier temp");

            }
              
             
            
            
        }catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
        public void updateOeuvres()
        {
             try {
         
               List<ElementPanier> elementPaniers=PanierHolder.getInstance().getEP();
            
            for ( int i=0;i<elementPaniers.size();i++)
            {
           
                String insReq="UPDATE oeuvrage SET quantité=quantité-? WHERE oeuvrage_id=?";

                PreparedStatement pst = connection.prepareStatement(insReq);
                pst.setInt(1, elementPaniers.get(i).getQuantite());
                pst.setInt(2, elementPaniers.get(i).getOeuv().getOeuvrage_id());

                  if(pst.executeUpdate() >0) 
                {
                    System.out.println("updated with success");

                }
             }
        } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void deletepaniert(int user_id){
         
                 String req="DELETE FROM panier_temp WHERE user_id=?";
                 try{
                     PreparedStatement pst = connection.prepareStatement(req);
                     
                     pst.setInt(1, user_id);
                     pst.executeUpdate();
                       
                     
                     
                             
                 } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        }
        
        public void deletepaniertElem(int user_id, int oeuvrage_id){
         
                 String req="DELETE FROM panier_temp WHERE user_id=? AND oeuvrage_id=?";
                 try{
                     PreparedStatement pst = connection.prepareStatement(req);
                     
                     pst.setInt(1, user_id);
                     pst.setInt(2, oeuvrage_id);
                     pst.executeUpdate();
                       
                     
                     
                             
                 } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        }
   
        public List<Oeuvrage> afficherLO() {
        
        String req="SELECT * FROM `oeuvrage` WHERE quantité>0";
        List<Oeuvrage> listO =new ArrayList<>();
        
        try {
           Statement st = connection.createStatement();
        
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Oeuvrage o1 =new Oeuvrage();
                o1.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o1.setUser_id(rs.getInt("user_id"));
                o1.setNom(rs.getString("nom"));
                o1.setDomaine(rs.getString("domaine"));
                o1.setPrix(rs.getFloat("prix"));
                o1.setQuantite(rs.getFloat("quantité"));
                o1.setDescription(rs.getString("description"));
                o1.setImage(rs.getString("image"));
                listO.add(o1);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return listO;
    
    }
        
}
    