/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Iservice.Idao;
import entities.Relations;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import utils.ConnexionDB;

/**
 *
 * @author asus
 */
public class RelationsService implements Idao<Relations>{
     private Connection cnx;
    private Statement st;
    private PreparedStatement ps;
    Connection conn = ConnexionDB.getInstance().getConnection();
    @Override
    public void insert(Relations o) {
         String req= "INSERT INTO relations( `follower_id`, `followee_id`)"+ "VALUES (?,?)";
         PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
            ps.setInt(1,o.getFollower_id()) ;
            ps.setInt(2,o.getFollowee_id());
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible d'ajouter un utilisateur"+ex.getMessage());
        } 
    }

    @Override
    public void delete(Relations o) {
        try {
            Statement ps = conn.createStatement();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer abonnement ");
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment se désabonner ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                ps.executeUpdate("delete from relations where `follower_id`=" + o.getFollower_id()+" and  `followee_id`=" + o.getFollowee_id());
            } 
           
            

        } catch (SQLException ex) {
            System.out.println("Impossible de supprimer abonnement"+ex.getMessage());
            //OptionPane.showMessageDialog(null,"Impossible de supprimer une categorie\n"+ex.getMessage());
        }  
    }

    @Override
    public ObservableList<Relations> displayAll() {
        ObservableList<Relations> list=FXCollections.observableArrayList(); 
        
        try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from relations");
            while(res.next())
            {
                int follower_id = res.getInt("follower_id");
                int followee_id = res.getInt("followee_id");
                Relations r = new Relations(follower_id, followee_id);
                list.add(r);
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        return list;    }

    @Override
    public Relations displayById(int id) {
        return null;

    }
    
    public ObservableList<Relations> displayByfollowerId(int id) {
        ObservableList<Relations> list=FXCollections.observableArrayList();
         try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from relations where `follower_id` =" + id);
            while(res.next())
            {
                int follower_id = res.getInt("follower_id");
                int followee_id = res.getInt("followee_id");
                Relations r = new Relations(follower_id, followee_id);
                list.add(r);
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        }  return list;   
    }
     public ObservableList<Relations> displayByfolloweeId(int id) {
        ObservableList<Relations> list=FXCollections.observableArrayList();
         try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from relations where `followee_id` =" + id);
            while(res.next())
            {
                int follower_id = res.getInt("follower_id");
                int followee_id = res.getInt("followee_id");
                Relations r = new Relations(follower_id, followee_id);
                list.add(r);
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        }  return list;   
    }
     public int nbfollowerByfolloweeId(int id) {
        int nb=0;
         try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select count(follower_id) as nb from relations where `followee_id` =" + id);
            while(res.next())
            {nb = res.getInt("nb");}
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        }  return nb; 
    }

    @Override
    public void update(Relations o) {
        String req= "Update relations set `follower_id`=?, `followee_id`=?";
        PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
           ps.setInt(1,o.getFollower_id()) ;
            ps.setInt(2,o.getFollowee_id());
            ps.executeUpdate() ;
        } catch (SQLException ex) {
           System.out.println("Impossible d'ajouter un utilisateur"+ex.getMessage());
        } 
    
    }
    public int existfollow(Relations o)  {
        ObservableList<Relations> list=FXCollections.observableArrayList();
        int i=0  ;
        try{
        
        String req = "SELECT * FROM relations where follower_id=? and followee_id=?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, o.getFollower_id());
        st.setInt(2, o.getFollowee_id());
        
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            int follower_id = rs.getInt("follower_id");
            int followee_id = rs.getInt("followee_id");
            Relations r = new Relations(follower_id, followee_id);
            list.add(r);

        }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return list.size();
    }
    
    
}
