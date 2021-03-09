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
    private static User currentuser;    
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement ps;
    
    
    
    Connection conn = Connexion.getInstance().getCnx();
    
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
    public void UpdateGérer(User user , int id) {
        String req= "Update  user set `nom`=?, `prenom`=?, `username`=?, `adresse`=?, `num_tel`=?, `email`=?, `bio`=? where `user_id`=?";
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
            ps.setInt(8,id);


            
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
                ps.executeUpdate("delete from user where `user_id`=" + user.getUser_id());
            } 
            alert.show();
            

        } catch (SQLException ex) {
            System.out.println("Impossible de supprimer un utilisateur"+ex.getMessage());
            //OptionPane.showMessageDialog(null,"Impossible de supprimer une categorie\n"+ex.getMessage());
        }  
    }

    @Override
    public ObservableList<User> displayAll() {
        ObservableList<User> list=FXCollections.observableArrayList(); 
        
        try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from user");
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
                User user = new User(id, userName, nom, prenom, email);
                list.add(user);
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        return list;
    }

    @Override
    public User displayById(int id) {
        List<User> listeUser = new ArrayList<User>();
        try{
        String req = "Select * from user where user_id = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        

        if (rs.next()) {
            User obj = new User();
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

           
            return obj;
        }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return null;
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
     
    public User displayEP(String email , String password)  {
        //List<User> listeUser = new ArrayList<User>();
        try{
        String req = "Select * from user where email = ? and password =?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setString(1, email);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        

        if (rs.next()) {
            User obj = new User();
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

           
            return obj;
        }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return null;
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
            ps.setString(9,user.getImage());

            
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible de modifier un utilisateur"+ex.getMessage());
        }
    }
    public void updateMDP(User user, int id) {
        String req="UPDATE user SET password=? WHERE  `user_id`= ?";
        PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
            
             
            ps.setString(1,user.getPassword());
           
            
            ps.setInt(2,id);

            
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible de modifier un utilisateur"+ex.getMessage());
        }
    }
    public User getUserByEmail(String email){
        try {
            PreparedStatement pt= cnx.prepareStatement("select * from user where email = ?");
            pt.setString(1, email);
            ResultSet rs = pt.executeQuery();
            if(rs.next()){
                User u = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),  rs.getString(10), rs.getString(11));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   
   
    }
    public static User getCurrentUser() {
        return currentuser;
    }

    public static void setCurrentUser(User currentuser) {
        UserService.currentuser = currentuser;
}
    public ObservableList<User> displaystatut(String statut ){
        ObservableList<User> list=FXCollections.observableArrayList();      
        try{
        String req = "Select * from user where "+statut+" =?";
        PreparedStatement st = conn.prepareStatement(req);
         st.setInt(1, 1);
        
        
        ResultSet res = st.executeQuery();
        

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
                User user = new User(id,userName, nom,prenom, email);
                list.add(user);
            }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return list;
    }
    public ObservableList<User>  displayrole(){
        ObservableList<User> list=FXCollections.observableArrayList(); 
        try{
        String req = "Select * from user where role ='client'";
        PreparedStatement st = conn.prepareStatement(req);
        
        
        ResultSet res = st.executeQuery();
        

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
                User user = new User(id,userName, nom,prenom, email);
                list.add(user);
            }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return list;
    }
    public ObservableList<User> displayvalidité(int i){
        ObservableList<User> list=FXCollections.observableArrayList(); 
        try{
        String req = "Select * from user where validité = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, i);
        
        
        ResultSet res = st.executeQuery();
        

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
                User user = new User(id,userName, nom,prenom, email);
                list.add(user);
            }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return list;
    }
}


    
   
    
    

