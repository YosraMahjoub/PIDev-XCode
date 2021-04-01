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
 * @author HELA
 */
public class ConnexionDB {
      private static ConnexionDB instance;
      private Connection cnx;
       String url = "jdbc:mysql://localhost:3306/xcode";
       
       
       private ConnexionDB(){
           System.out.println("Instantiation de CNX");
        try {
            cnx = DriverManager.getConnection(url, "root", "");
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println("Connexion impossible");
            System.err.println(ex);
        }
       
       }
       
       public static ConnexionDB getInstance() {
        if (instance == null) {
            instance = new ConnexionDB();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
}
