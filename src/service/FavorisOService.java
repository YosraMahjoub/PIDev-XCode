/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.FavorisO;
import entities.Oeuvre;
import entities.User;

import utils.ConnexionDB;

/**
 *
 * @author pc
 */
public class FavorisOService {
    Connection conx = ConnexionDB.getInstance().getConnection();
    
       public void creerFo(Oeuvre o) {
        try {
            String req = "INSERT INTO `favoris_o`( `user_id`, `oeuvrage_id`)  VALUES (?,?)";
            
            PreparedStatement ps = conx.prepareStatement(req);
            
            ps.setInt(1,1);
            ps.setInt(2, o.getOeuvrage_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavorisOService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }
       public void supprimerFo(Oeuvre o) {
        try {
            String req = "DELETE FROM `favoris_o` WHERE `user_id` =? AND `oeuvrage_id`=?";
            
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1,1);
            ps.setInt(2,o.getOeuvrage_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavorisOService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
       public List<FavorisO> afflf() {
      
            String req = "SELECT f.oeuvrage_id, `nom`, `domaine`, `prix`, `quantité`, `description`, `image` FROM favoris_o f INNER join oeuvrage o on f.oeuvrage_id = o.oeuvrage_id WHERE F.user_id = 1";
            List<FavorisO> listO =new ArrayList<>();
            
             try { 
            Statement ste = conx.createStatement();
            
            ResultSet rs = ste.executeQuery(req);
            
            
            while(rs.next()){
                FavorisO f =new FavorisO();
                f.getO().setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                f.getO().setNom(rs.getString("nom"));
                f.getO().setDoamine(rs.getString("domaine"));
                f.getO().setPrix(rs.getFloat("prix"));
                f.getO().setQuantite(rs.getFloat("quantité"));
                f.getO().setDescription(rs.getString("description"));
                f.getO().setImg(rs.getString("image"));
              listO.add(f);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FavorisOService.class.getName()).log(Level.SEVERE, null, ex);
        }
return listO;
}
   public int isclicked( Oeuvre o) {
            String req = " SELECT  count(oeuvrage_id) as nb FROM favoris_o where oeuvrage_id= "+o.getOeuvrage_id()+" AND user_id=1";
             int b = 0;
          try {  
            Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            if (rs.next()) {
                  b =rs.getInt("nb");
           }
            
          } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
return b;
}
public int nbof( Oeuvre o) {
            String req = "select u.nom, count(f.user_id) as nbof from favoris_o f INNER join oeuvrage o on f.oeuvrage_id = o.oeuvrage_id INNER JOIN user u on u.user_id=o.user_id group by f.oeuvrage_id and u.user_id ;";
             int b = 0;
          try {  
            Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            if (rs.next()) {
                  b =rs.getInt("nb");
           }
            
          } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
return b;
}   
}
