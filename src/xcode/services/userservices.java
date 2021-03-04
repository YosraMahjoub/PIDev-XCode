/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import xcode.DBaseC.ConnectionDB;

/**
 *
 * @author Mega-PC
 */
public class userservices {
    
    
    Statement ste;
   
    Connection connection=ConnectionDB.getInstance().getCnx();
    
    
    
    public String getNomUser(int id){
        String nom="";
        try {
            ste = connection.createStatement();
            ResultSet rs=ste.executeQuery("select nom from user where id="+id);
            while (rs.next()){
                nom=rs.getString("nom");
            }
        } catch (SQLException ex) {
             Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
            }
        return nom;
    }
}
