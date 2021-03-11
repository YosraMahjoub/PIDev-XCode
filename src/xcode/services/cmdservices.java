/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.services;

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
import xcode.entity.Commande;
import xcode.entity.ElementPanier;
import xcode.entity.Facture;
import xcode.entity.Oeuvrage;
import xcode.entity.PanierHolder;


/**
 *
 * @author Mega-PC
 */
public class cmdservices {
    
    Statement ste;
   
    Connection connection=ConnectionDB.getInstance().getCnx();
    
    
   public List<Facture> afficherPan() {
        
        String req="select u.nom, o.nom , o.prix, c.quantite, u.user_id, c.user_id, o.oeuvrage_id, c.oeuvrage_id "
                    + "from oeuvrage o, commande c, user u "
                    + "where u.user_id =c.user_id AND c.oeuvrage_id = o.oeuvrage_id AND u.user_id = '1'";
        List<Facture> listF =new ArrayList<>();
        
        try {
           Statement st = connection.createStatement();
        
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Facture a = new Facture();
                                        
                                        a.setUser(rs.getString(1));
                                        a.setOeuvrage(rs.getString(2));
                                        a.setQuantite(rs.getInt(3));
                                        a.setPrix(rs.getInt(4));
                                        listF.add(a);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return listF;
    
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
    
    public List<Oeuvrage> afficherLOBI(int id) {
        
        String req="SELECT * FROM `oeuvrage`WHERE `user_id`="+id;
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
    
   


    public List<Oeuvrage> displayAll() {
         String req="select * from `oeuvrage`";
            ObservableList<Oeuvrage> list=FXCollections.observableArrayList();
       
            try {
           
            
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
                Oeuvrage o2 =new Oeuvrage();
                o2.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o2.setUser_id(rs.getInt("user_id"));
                o2.setNom(rs.getString("nom"));
                o2.setDomaine(rs.getString("domaine"));
                o2.setPrix(rs.getFloat("prix"));
                o2.setQuantite(rs.getFloat("quantité"));
                o2.setDescription(rs.getString("description"));
                o2.setDescription(rs.getString("image"));
           list.add(o2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return list;
}
   
 

   
   public void Delcmd(Commande c) {
  
    }

    

}