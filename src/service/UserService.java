/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Iservice.Idao;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.Connexion;


/**
 *
 * @author asus
 */
public class UserService implements Idao<User>{
    private static UserService instance;
    private Connection cnx;
    private Statement st;
    private PreparedStatement ps;
    Connexion ds1 = Connexion.getInstance();
    Connection conn= ds1.getCnx();
    
    public UserService(){
        Connexion cs=Connexion.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static UserService getInstance(){
        if(instance==null) 
            instance=new UserService();
        return instance;
    }


    @Override
    public void insert(User user) {
        String req= "INSERT INTO user( `nom`, `prenom`, `username`, `password`,  `adresse`, `num_tel`, `email`)"
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
            ps.setString(1,user.getNom()) ;
            ps.setString(2,user.getPrenom());
            ps.setString(3,user.getUsername()) ; 
            ps.setString(4,user.getPassword());
           
            ps.setString(5,user.getAdresse());
            ps.setInt(6,user.getNum_tel()) ; 
            ps.setString(7,user.getEmail()) ;
            
            //ps.setString(9,user.getImage());

            
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible d'ajouter un utilisateur"+ex.getMessage());
        } 
    
    }
    public void UpdateGérer(User user) {
        String req= "Update  user set `nom`=?, `prenom`=?, `username`=?, `adresse`=?, `num_tel`=?, `email`=?, `bio`=? where `user_id`=4"/*+user.getUser_id()+"'*/;
        PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
            ps.setString(1,user.getNom()) ;
            ps.setString(2,user.getPrenom());
            ps.setString(3,user.getUsername()) ; 
            
            
            ps.setString(4,user.getAdresse());
            ps.setInt(5,user.getNum_tel()) ; 
            ps.setString(6,user.getEmail()) ;
            
            ps.setString(7,user.getBio());

            
            ps.executeUpdate() ; 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("profil mis à jour");
            alert.show();
            
        } catch (SQLException ex) {
           System.out.println("Impossible d'ajouter un utilisateur"+ex.getMessage());
        } 
    
    }

    @Override
    public void delete(User user) {
        try {
            Statement ps = conn.createStatement();
            
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer ");
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment supprimer le compte");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                ps.executeUpdate("delete from user where `user_id`=4");
            } 
            alert.show();
            

        } catch (SQLException ex) {
            System.out.println("Impossible de supprimer un utilisateur"+ex.getMessage());
            //OptionPane.showMessageDialog(null,"Impossible de supprimer une categorie\n"+ex.getMessage());
        }  
    }

    @Override
    public List<User> displayAll() {
        List<User> listeUser = new ArrayList<User>();
        
        try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from user where ");
            while(res.next())
            {
                int id = res.getInt("user_id");
                String userName=res.getString("username");
                String email=res.getString("email"); 
                String password=res.getString("password"); 
                String roles=res.getString("role"); 
                String nom=res.getString("nom"); 
                String prenom=res.getString("prenom");
                String image=res.getString("image"); 
                User user = new User(id, userName, roles, email);
                listeUser.add(user);
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        return listeUser;
    }

    @Override
    public User displayById(int id) {
 User obj = new User();
        try{
        String req = "Select * from user where user_id = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        

        if (rs.next()) {
           
            obj.setUser_id(rs.getInt("user_id"));
            obj.setUsername(rs.getString("username"));
            obj.setEmail(rs.getString("email"));
            obj.setPassword(rs.getString("password"));
            obj.setRole(rs.getString("role"));
            obj.setNom(rs.getString("nom"));
            obj.setNum_tel(rs.getInt("num_tel"));
            obj.setPrenom(rs.getString("prenom"));
            obj.setBio(rs.getString("bio"));
            obj.setAdresse(rs.getString("adresse"));
            //obj.setImage(rs.getString("image"));

           
           
        }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return obj;
    }
    public  boolean  displayAuth(String email , String password) {
        //List<User> listeUser = new ArrayList<User>();
        boolean x =false;
        try{
        String req = "Select * from user where email = ? and password =?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setString(1, email);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if (!rs.next()) {
            infoBox("Enter Correct Email and Password", "Failed", null);
             x =false;
        }
       
        else {
            infoBox("Login Successfull", "Success", null);
             x =true;
        }
        } catch(Exception e)
        {
                   return x ;
        }
        return x;
    }
    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(titleBar);
    alert.setHeaderText(headerMessage);
    alert.setContentText(infoMessage);
    alert.showAndWait();
}
    

    @Override
    public void update(User user) {
        String req="UPDATE user SET nom=?, prenom=?,username=?,password=?,roles=?,adresse=?,num_tel=?,email=?, image=? WHERE id = "+user.getUser_id();
        PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
            ps.setString(1,user.getNom()) ;
            ps.setString(2,user.getPrenom());
            ps.setString(3,user.getUsername()) ; 
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getRole());
            ps.setString(6,user.getAdresse());
            ps.setInt(7,user.getNum_tel()) ; 
            ps.setString(8,user.getEmail()) ; 
            ps.setString(11,user.getImage());

            
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible de modifier un utilisateur"+ex.getMessage());
        }
    }
    public void updateMDP(User user) {
        String req="UPDATE user SET password=? WHERE  `user_id`=4 ";
        PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
            ps.setString(1,user.getNom()) ;
            ps.setString(2,user.getPrenom());
            ps.setString(3,user.getUsername()) ; 
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getRole());
            ps.setString(6,user.getAdresse());
            ps.setInt(7,user.getNum_tel()) ; 
            ps.setString(8,user.getEmail()) ; 
            ps.setString(11,user.getImage());

            
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible de modifier un utilisateur"+ex.getMessage());
        }
    }

   
   
    }


    
   
    
    

