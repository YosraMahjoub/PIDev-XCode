/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class ConnexionDB {
   
    String url ="jdbc:mysql://localhost:3306/xcode";
    String login ="root"; 
    String pwd ="";
    Connection cnx;
    static ConnexionDB instance;
                
     private ConnexionDB(){     
        try {
            cnx= DriverManager.getConnection(url, login, pwd);
            System.out.println("cnx établie");
        } catch (SQLException ex) {
            System.out.println("error");
             }
   }
     public Connection getConnection(){
         return cnx;
     }
    public static ConnexionDB getInstance(){
        if (instance == null){
           instance = new ConnexionDB();
        }
           return instance;
        }

}
