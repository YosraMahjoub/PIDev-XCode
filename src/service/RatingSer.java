/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Formation;
import entities.RatingEntity;
import xcode_pidev.Main;
import Utils.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HELA
 */
public class RatingSer {
    
     Connection cnx;
    Statement ste;
    PreparedStatement pste;
private static RatingSer instance;
     
    
    public RatingSer()  {
        try {
            cnx = ConnexionDB.getInstance().getConnection();
            ste= cnx.createStatement();
            // pSte =cnx.prepareStatement(sql)
        } catch (SQLException ex) {
            Logger.getLogger(FormationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static RatingSer getInstance() {
        if(instance==null) 
            instance=new RatingSer();
        return instance;
    }
    
       public void rating (double v, Formation f) throws SQLException{
            
         
           String req= "insert into rating (value, user_id, formation_id) values (?,?,?);";  
          // String req1 = "insert into rating (value) values(?)";
           pste =cnx.prepareStatement(req);
           pste.setDouble(1,v);
           pste.setInt(2, Main.connectedUser.getUser_id());
           pste.setInt(3, f.getFormation_id());
           
           pste.executeUpdate();
           //pste.setInt(2, r.);
         }
       public double ratingAff (int f_id) throws SQLException{
       String req = "select avg (value) as moyenne from rating where formation_id =?";
       PreparedStatement p = cnx.prepareStatement(req);
       p.setInt(1,f_id);
       RatingEntity r = new RatingEntity();
       ResultSet rs=p.executeQuery();
       double i= rs.getInt("moyenne");
       
       return i;
       }
       
        
       
       }

