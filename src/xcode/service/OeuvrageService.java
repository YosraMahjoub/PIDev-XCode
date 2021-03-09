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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xcode.entity.Oeuvre;
import xcode.utils.ConnexionDB;
import xcode.Iservice.IOeuvreService;

/**
 *
 * @author pc
 */
public class OeuvrageService implements IOeuvreService<Oeuvre>{
    
    Connection conx = ConnexionDB.getInstance().getConnection();

    @Override
    public void ajouterO(Oeuvre o) {
        String req = "INSERT INTO `oeuvrage` ( `user_id`, `nom`, `domaine`, `prix`, `quantité`, `description`, `image`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            
            
            ps.setInt(1,1);
            ps.setString(2, o.getNom());
            ps.setString(3, o.getDoamine());
            ps.setFloat(4, o.getPrix());
            ps.setFloat(5, o.getQuantite());
            ps.setString(6, o.getDescription());
            ps.setString(7, o.getImg());
           
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Override
    public void modifierO(Oeuvre o) {
        
    String req = "UPDATE `oeuvrage` SET `nom` =?,`domaine`=?,`prix`=?,`quantité`=?,`description`=? WHERE  `oeuvrage_id`= ?";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            
            ps.setString(1, o.getNom());
            ps.setString(2, o.getDoamine());
            ps.setFloat(3, o.getPrix());
            ps.setFloat(4, o.getQuantite());
            ps.setString(5, o.getDescription());
            ps.setInt(6, o.getOeuvrage_id());
            
            
          
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de l'oeuvre " +o.getNom()+" a été éffectué avec succées ");
            }
           
        } catch (SQLException ex) {
            
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    @Override
    public void supprimerO(Oeuvre o) {
        String req = "DELETE FROM `oeuvrage` WHERE `oeuvrage_id`=?";
           Oeuvre o1 = afficherO(o.getOeuvrage_id());
            if(o1!=null){
        try {
           
            
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1,o.getOeuvrage_id());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
            else System.out.println("cet oeuvrage n'existe pas");
    }

    @Override
    public List<Oeuvre> afficherLO() {
        
        String req="SELECT * FROM `oeuvrage`";
        List<Oeuvre> listO =new ArrayList<>();
        
        try {
           Statement ste = conx.createStatement();
        
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Oeuvre o1 =new Oeuvre();
                o1.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o1.setUser_id(rs.getInt("user_id"));
                o1.setNom(rs.getString("nom"));
                o1.setDoamine(rs.getString("domaine"));
                o1.setPrix(rs.getFloat("prix"));
                o1.setQuantite(rs.getFloat("quantité"));
                o1.setDescription(rs.getString("description"));
                o1.setImg(rs.getString("image"));
                listO.add(o1);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return listO;
    
    }
    
    public List<Oeuvre> afficherLOBI(int id) {
        String req="SELECT * FROM `oeuvrage`WHERE `user_id`="+id;
        List<Oeuvre> listO =new ArrayList<>();
        
        try {
           Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Oeuvre o1 =new Oeuvre();
                o1.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o1.setUser_id(rs.getInt("user_id"));
                o1.setNom(rs.getString("nom"));
                o1.setDoamine(rs.getString("domaine"));
                o1.setPrix(rs.getFloat("prix"));
                o1.setQuantite(rs.getFloat("quantité"));
                o1.setDescription(rs.getString("description"));
                o1.setImg(rs.getString("image"));
                o1.setIsvalid(rs.getInt("isvalid"));
                listO.add(o1);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return listO;
    }
    
    
     public List<Oeuvre> afficherLOV() {
        String req="SELECT * FROM `oeuvrage`WHERE `isvalid`=1";
        List<Oeuvre> listO =new ArrayList<>();
        
        try {
           Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Oeuvre o1 =new Oeuvre();
                o1.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o1.setUser_id(rs.getInt("user_id"));
                o1.setNom(rs.getString("nom"));
                o1.setDoamine(rs.getString("domaine"));
                o1.setPrix(rs.getFloat("prix"));
                o1.setQuantite(rs.getFloat("quantité"));
                o1.setDescription(rs.getString("description"));
                o1.setImg(rs.getString("image"));
                listO.add(o1);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return listO;
    }
    
    public List<Oeuvre> afficherLONV() {
        String req="SELECT * FROM `oeuvrage`WHERE `isvalid`=0";
        List<Oeuvre> listO =new ArrayList<>();
        
        try {
           Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Oeuvre o1 =new Oeuvre();
                o1.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o1.setUser_id(rs.getInt("user_id"));
                o1.setNom(rs.getString("nom"));
                o1.setDoamine(rs.getString("domaine"));
                o1.setPrix(rs.getFloat("prix"));
                o1.setQuantite(rs.getFloat("quantité"));
                o1.setDescription(rs.getString("description"));
                o1.setImg(rs.getString("image"));
                listO.add(o1);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return listO;
    }

    @Override
    public Oeuvre afficherO(int id) {
    String req="SELECT * FROM `oeuvrage`WHERE `oeuvrage_id`="+id;
       Oeuvre o2 = new Oeuvre();
        
        try {
           Statement ste = conx.createStatement();
        
            ResultSet rs = ste.executeQuery(req);
           
           while(rs.next()){
               
                o2.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o2.setUser_id(rs.getInt("user_id"));
                 o2.setNom(rs.getString("nom"));
                  o2.setDoamine(rs.getString("domaine"));
                o2.setPrix(rs.getFloat("prix"));
                o2.setQuantite(rs.getFloat("quantité"));
                o2.setDescription(rs.getString("description"));
                o2.setDescription(rs.getString("image"));
                System.out.println(o2);
                
                
            }}
        catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return o2;
    
    
    }

    @Override
    public List<Oeuvre> displayAll() {
         String req="select * from `oeuvrage`";
            ObservableList<Oeuvre> list=FXCollections.observableArrayList();
       
            try {
           
            
            Statement ste = conx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Oeuvre o2 =new Oeuvre();
                o2.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o2.setUser_id(rs.getInt("user_id"));
                 o2.setNom(rs.getString("nom"));
                 o2.setDoamine(rs.getString("domaine"));
                o2.setPrix(rs.getFloat("prix"));
                o2.setQuantite(rs.getFloat("quantité"));
                o2.setDescription(rs.getString("description"));
                o2.setDescription(rs.getString("image"));
                System.out.println(o2);
           list.add(o2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return list;
}
        public void valider(Oeuvre o) {
        String req = "UPDATE `oeuvrage` SET `isvalid` =? WHERE  `oeuvrage_id`="+o.getOeuvrage_id();
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            
            ps.setInt(1,1);
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
