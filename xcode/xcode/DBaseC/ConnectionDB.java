/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.DBaseC;

/**
 *
 * @author Mega-PC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mega-PC
 */
public class ConnectionDB {
    private String url="jdbc:mysql://127.0.0.1:3306/fanny";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static ConnectionDB instance;
    
    public Connection getCnx(){
        return cnx;
    }
    
    public ConnectionDB(){
        
        try {
            cnx=DriverManager.getConnection(url,login,pwd);
            System.out.println("Succès");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static ConnectionDB getInstance(){
        if(instance==null)
            instance=new ConnectionDB();
        return instance;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
