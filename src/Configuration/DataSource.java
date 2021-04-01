/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DataSource {

    private static DataSource data;
    private Connection conn;
    public String url = "jdbc:mysql://localhost:3306/xcode";
    public String user = "root";
    public String password = "";

    private DataSource() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connexion établie");
        } catch (SQLException e) {
                        System.out.println("erreur de connexion");

            e.printStackTrace();
        }
    }
    
    public static DataSource getInstance(){
        if(data==null){
            return new DataSource();
        }else{
            return data;
        }
    }
     public Connection getConn() {
        return conn;
    }
    
}
