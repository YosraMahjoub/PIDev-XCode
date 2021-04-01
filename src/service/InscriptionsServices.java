/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Inscription;
import utils.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HELA
 */
public class InscriptionsServices {

    //Connection cnx;
    Statement ste;
    private static FormationServices instance;
Connection cnx = ConnexionDB.getInstance().getConnection();
    public InscriptionsServices() throws SQLException {
        
      //  ste = cnx.createStatement();
    }

//    public static FormationServices getInstance() {
//        if (instance == null) {
//            instance = new FormationServices();
//        }
//        return instance;
//    }

    public void ajouter(Inscription p) {
        try {
            PreparedStatement pre = cnx.prepareStatement("INSERT INTO `inscription` ( `user_id`, `formation_id`, `date`, `isinscrit`) VALUES ( ?, ?, ?,?);");
            pre.setInt(1, p.u1.getUser_id());
            pre.setInt(2, p.f.getFormation_id());
            pre.setDate(3, p.getDate());
            pre.setInt(4,1);
            pre.executeUpdate();
        } catch (SQLException ex) {
            
            Logger.getLogger(InscriptionsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
            
        
    

}
