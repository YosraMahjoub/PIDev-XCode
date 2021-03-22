/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xcode.entity.FavorisO;
import xcode.entity.Oeuvre;
import xcode.entity.User;

import xcode.utils.ConnexionDB;

/**
 *
 * @author pc
 */
public class FavorisOService {
    Connection conx = ConnexionDB.getInstance().getConnection();
    
       public void creerRo(Oeuvre o) {
        try {
            String req = "INSERT INTO `favoris_o`( `user_id`, `oeuvrage_id`) VALUES VALUES (?,?)";
            
            PreparedStatement ps = conx.prepareStatement(req);
            
            ps.setInt(1,1);
            ps.setInt(2, o.getOeuvrage_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavorisOService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }
       public void supprimerRo(User u, Oeuvre o) {
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
       public List<FavorisO> afflr() {
////            String req = "SELECT f.oeuvrage_id, `nom`, `domaine`, `prix`, `quantité`, `description`, `image` FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id GROUP by r.oeuvrage_id ORDER by rate DESC; ";
         List<FavorisO> listO =new ArrayList<>();
//        
//        try {
//           Statement ste = conx.createStatement();
//        
//            ResultSet rs = ste.executeQuery(req);
//            
//            
//            while(rs.next()){
//                FavorisO f =new FavorisO();
//                f.getO().setOeuvrage_id(rs.getInt("Oeuvrage_id"));
//                f.getO().setNom(rs.getString("nom"));
//                o1.setDoamine(rs.getString("domaine"));
//                o1.setPrix(rs.getFloat("prix"));
//                o1.setQuantite(rs.getFloat("quantité"));
//                o1.setDescription(rs.getString("description"));
//                o1.setImg(rs.getString("image"));
//                o1.setIsvalid(rs.getInt("isvalid"));
//              o1.setRate(rs.getFloat("rate"));
//              
//            }
//}catch (SQLException ex) {
//            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);   
//        }
     return listO;

}
    
}
