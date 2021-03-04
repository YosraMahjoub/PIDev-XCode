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
public class oeuvrageservice {
    
    
    Statement ste;
    Connection connection=ConnectionDB.getInstance().getCnx();
   
 
    public int getPrixOeuvrage(int id){
        int prix=0;
  
         try {
             ste = connection.createStatement();          
             ResultSet rs=ste.executeQuery("select prix from oeuvrage where id="+id);
             while (rs.next()) {
                 prix=rs.getInt("prix");
             }
         } catch (SQLException ex) {
             Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
         }
         return prix; 
  
    } 
    
    public String getNomOeuvrage(int id){
        String nom="";
        try {
            ste = connection.createStatement();
            ResultSet rs=ste.executeQuery("select nom from oeuvrage where id="+id);
            while (rs.next()){
                nom=rs.getString("nom");
            }
        } catch (SQLException ex) {
             Logger.getLogger(cmdservices.class.getName()).log(Level.SEVERE, null, ex);
            }
        return nom;
    }
    
}
