/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class Connexion {
    
    private String url="jdbc:mysql://127.0.0.1:3306/xcode";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static Connexion instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private Connexion() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static Connexion getInstance(){
       
       if(instance==null)
           instance=new Connexion();
       return instance;
   }
    
    
    
    
}

