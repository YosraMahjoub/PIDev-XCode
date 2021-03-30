/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Iservice.Idao;
import entities.Reclamation;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
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
public class ReclamationService   {
      
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement ps;
    
    
    
    Connection conn = ConnexionDB.getInstance().getConnection();

    public int getID(String id , String nomtable, String titre,String nom ) throws SQLException{
        int id1 = 0;
        String re = "SELECT "+id+" from "+ nomtable+ " WHERE " + titre+"=?";
        PreparedStatement st = conn.prepareStatement(re);
        st.setString(1, nom);
        ResultSet res = st.executeQuery();
        while(res.next())
            {id1= res.getInt(id);
            return id1;}
        return id1;
    }
    public void insert(Reclamation o , String id , String nomtable, String titre, String nom ) throws SQLException  {
        int id1 = 0, id2=0 ;
        String re = "SELECT "+id+" from "+ nomtable+ " WHERE " + titre+"=?";
        PreparedStatement st = conn.prepareStatement(re);
        st.setString(1, nom);
        ResultSet res = st.executeQuery();
        while(res.next())
            {id1= res.getInt(id);}
        if(nomtable == "evenement"){
           
            try{
                    String r = "SELECT reservation_id from reservation WHERE evenement_id=?";
                    PreparedStatement s = conn.prepareStatement(r);
                    s.setInt(1, id1);
                    ResultSet rea = s.executeQuery();
                    while(rea.next())
                        {  id2 = rea.getInt("reservation_id"); }
                    if(id2==0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("vous devez commander une ticket avant de réclamer");
                        alert.show();}
                    else{
                        String req= "INSERT INTO reclamation(`user_id`,`reclamation_nom`," +id +" ,`description` , `date`)"
                        + "VALUES (?,?,?,?, NOW())";
                        PreparedStatement ps;
                        try { 
                            ps =conn.prepareStatement(req); 
                            ps.setInt(1,o.getUser_id()) ;
                            ps.setString(2,o.getReclamation_nom()) ;
                            ps.setInt(3, id1);
                            ps.setString(4,o.getDescription()) ; 
                            ps.executeUpdate() ; 

                    } catch (SQLException ex) {
                       System.out.println("Impossible d'ajouter la reclamation"+ex.getMessage());
                    } }
                    
            }catch(Exception e){ System.out.println("Erreur d'affichage"+e.getMessage());}}
        
        
        else if(nomtable == "formation"){
        
            try{
                    String r1 = "SELECT inscription_id from inscription WHERE formation_id=?";
                    PreparedStatement s1 = conn.prepareStatement(r1);
                    s1.setInt(1, id1);
                    ResultSet rea1 = s1.executeQuery();
                    while(rea1.next())
                        { id2 = rea1.getInt("reservation_id"); 
                        if(id2==0){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("vous devez vous inscrire à la formation avant de réclamer");
                            alert.show();}
                        else{
                        String req= "INSERT INTO reclamation(`user_id`,`reclamation_nom`," +id +" ,`description` , `date`)"
                        + "VALUES (?,?,?,?, NOW())";
                        PreparedStatement ps;
                        try { 
                            ps =conn.prepareStatement(req); 
                            ps.setInt(1,o.getUser_id()) ;
                            ps.setString(2,o.getReclamation_nom()) ;
                            ps.setInt(3, id1);
                            ps.setString(4,o.getDescription()) ; 
                            ps.executeUpdate() ; 

                    } catch (SQLException ex) {
                       System.out.println("Impossible d'ajouter la reclamation"+ex.getMessage());
                    } }
                        }}
                   catch(Exception e){ System.out.println("Erreur d'affichage"+e.getMessage());}}
        else if(nomtable == "oeuvre"){
        
            try{
                    String r2 = "SELECT commande_id from commande WHERE oeuvrage_id=?";
                    PreparedStatement s2 = conn.prepareStatement(r2);
                    s2.setInt(1, id1);
                    ResultSet rea2 = s2.executeQuery();
                    while(rea2.next())
                        { id2 = rea2.getInt("reservation_id"); 
                        if(id2==0){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("vous devez commander l'oeuvre avant de réclamer");
                            alert.show();}
                        else{
                        String req= "INSERT INTO reclamation(`user_id`,`reclamation_nom`," +id +" ,`description` , `date`)"
                        + "VALUES (?,?,?,?, NOW())";
                        PreparedStatement ps;
                        try { 
                            ps =conn.prepareStatement(req); 
                            ps.setInt(1,o.getUser_id()) ;
                            ps.setString(2,o.getReclamation_nom()) ;
                            ps.setInt(3, id1);
                            ps.setString(4,o.getDescription()) ; 
                            ps.executeUpdate() ; 

                    } catch (SQLException ex) {
                       System.out.println("Impossible d'ajouter la reclamation"+ex.getMessage());
                    } }
                        }}
                   catch(Exception e){ System.out.println("Erreur d'affichage"+e.getMessage());}}
        
        else if(id2==0){System.out.println("noooooooon");}
        else if(id2!=0){
            System.out.println(id2);
            
        }}
    
    
    public int existreclm(int user_id, String type,int id)  {
        
        int i=0  ;
        try{
        
        String req = "SELECT COUNT(reclamation_id) AS Nb FROM reclamation where user_id=? and " + type + "=?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, user_id);
        st.setInt(2, id);
        
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            i = rs.getInt("Nb");
           
        }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return i;
    }
    public void delete(Reclamation o) {
        try {
            Statement ps = conn.createStatement();
            
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(" supprimer ");
            alert.setHeaderText(null);
            alert.setContentText("voulez vous vraiment supprimer la réclamation");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                ps.executeUpdate("delete from reclamation where `reclamation_id`=" + o.getReclamation_id());
            } 
            alert.show();
            

        } catch (SQLException ex) {
            System.out.println("Impossible de supprimer la réclamation"+ex.getMessage());
            //OptionPane.showMessageDialog(null,"Impossible de supprimer une categorie\n"+ex.getMessage());
        }  
    }

   
    public ObservableList<Reclamation> displayAll() {
        ObservableList<Reclamation> list=FXCollections.observableArrayList(); 
        String x= "";
        
        try
        {
            Statement ps = conn.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from reclamation");
            while(res.next())
            {
                int reclamation_id = res.getInt("reclamation_id");
                String reclamation_nom=res.getString("reclamation_nom"); 
                int user_id = res.getInt("user_id");
                Integer evenement_id = res.getInt("evenement_id");
                Integer formation_id = res.getInt("formation_id");
                Integer oeuvrage_id = res.getInt("oeuvrage_id");
                String description=res.getString("description"); 
                Date date=res.getDate("date"); 
                if(evenement_id !=0){x="evenement";}
                if(formation_id !=0){ x="formation";}
                if(oeuvrage_id !=0){ x="oeuvre";}
                
                Reclamation rec = new Reclamation(reclamation_id, user_id,reclamation_nom, x, description,date);
                
                list.add(rec);
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        return list;
    
    }

    
    public Reclamation displayById(int id) {
        try{
        String req = "Select * from reclamation where reclamation_id = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        while(res.next())
            {
                int reclamation_id = res.getInt("reclamation_id");
                String reclamation_nom=res.getString("reclamation_nom"); 
                int user_id = res.getInt("user_id");
                int evenement_id = res.getInt("evenement_id");
                int formation_id = res.getInt("formation_id");
                int oeuvrage_id = res.getInt("oeuvrage_id");
                String description=res.getString("description"); 
                Date date=res.getDate("date"); 
                
                Reclamation rec = new Reclamation(reclamation_id,reclamation_nom, user_id, evenement_id, formation_id, oeuvrage_id, description, date);
                return rec;
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        return null;
    }
    public Reclamation displayType(int id) {
        String x = null , nom =null;
        try{
        String req = "Select * from reclamation where reclamation_id = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        while(res.next())
            {
                int reclamation_id = res.getInt("reclamation_id");
                String reclamation_nom=res.getString("reclamation_nom"); 
                int user_id = res.getInt("user_id");
                Integer evenement_id = res.getInt("evenement_id");
                Integer formation_id = res.getInt("formation_id");
                Integer oeuvrage_id = res.getInt("oeuvrage_id");
                String description=res.getString("description"); 
                Date date=res.getDate("date"); 
                if(evenement_id !=null){x="evenement";}
                else if(formation_id !=null){ x="formation";}
                else if(oeuvrage_id !=null){ x="oeuvre";}
                
                Reclamation rec = new Reclamation(reclamation_id, user_id,reclamation_nom, x, description,date);
                return rec;
            }
        }
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        
       return null;}
        
    public String displayproduit(int id) {
        String x = null , nom =null;
        try{
        String req = "Select * from reclamation where reclamation_id = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        while(res.next())
            {
                int reclamation_id = res.getInt("reclamation_id");
                String reclamation_nom=res.getString("reclamation_nom"); 
                int user_id = res.getInt("user_id");
                Integer evenement_id = res.getInt("evenement_id");
                Integer formation_id = res.getInt("formation_id");
                Integer oeuvrage_id = res.getInt("oeuvrage_id");
                String description=res.getString("description"); 
                Date date=res.getDate("date"); 
                if(evenement_id !=null){
                   try{
                    String req1 = "Select nom from evenement where evenement_id = ?";
                    PreparedStatement sti0 = conn.prepareStatement(req1);
                    sti0.setInt(1, evenement_id);
                    ResultSet res0 = sti0.executeQuery();
                    while(res0.next())
                        { nom = res0.getString("nom"); return nom;}}
                   catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
                }
                
               else if(formation_id !=null){ 
                try{
                String req1 = "Select titre from formation where formation_id = ?";
                PreparedStatement sti1 = conn.prepareStatement(req1);
                sti1.setInt(1, formation_id);
                ResultSet res1 = sti1.executeQuery();
                    while(res1.next())
                        { nom = res1.getString("titre"); return nom;}}
                catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
               }
               else if(oeuvrage_id !=null){try{ 
                 String req1 = "Select nom from oeuvrage where oeuvrage_id = ?";
                PreparedStatement sti2 = conn.prepareStatement(req1);
                sti2.setInt(1, oeuvrage_id);
                ResultSet res2 = sti2.executeQuery();
                    while(res2.next())
                        { nom = res2.getString("nom"); return nom;}
                
                }catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
                     
                
            }
        }}
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        
       return null;}
    public int displayproduitUSer(int id) {
        String x = null , nom =null; int i=0;
        try{
        String req = "Select * from reclamation where reclamation_id = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        while(res.next())
            {
                int reclamation_id = res.getInt("reclamation_id");
                String reclamation_nom=res.getString("reclamation_nom"); 
                int user_id = res.getInt("user_id");
                Integer evenement_id = res.getInt("evenement_id");
                Integer formation_id = res.getInt("formation_id");
                Integer oeuvrage_id = res.getInt("oeuvrage_id");
                String description=res.getString("description"); 
                Date date=res.getDate("date"); 
                if(evenement_id !=0){
                   try{
                    String req1 = "Select user_id from evenement where evenement_id = ?";
                    PreparedStatement sti0 = conn.prepareStatement(req1);
                    sti0.setInt(1, evenement_id);
                    ResultSet res0 = sti0.executeQuery();
                    while(res0.next())
                        {i = res0.getInt("user_id"); return i;}}
                   catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
                }
                
               else if(formation_id !=0){ 
                try{
                String req1 = "Select user_id from formation where formation_id = ?";
                PreparedStatement sti1 = conn.prepareStatement(req1);
                sti1.setInt(1, formation_id);
                ResultSet res1 = sti1.executeQuery();
                    while(res1.next())
                        { i = res1.getInt("user_id"); return i;}}
                catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
               }
               else if(oeuvrage_id !=0){try{ 
                 String req1 = "Select user_id from oeuvrage where oeuvrage_id = ?";
                PreparedStatement sti2 = conn.prepareStatement(req1);
                sti2.setInt(1, oeuvrage_id);
                ResultSet res2 = sti2.executeQuery();
                    while(res2.next())
                        {i = res2.getInt("user_id"); return i;}
                
                }catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
                     
                
            }
        }}
          catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());
        } 
        
       return i;}
        
    public String displayuser(int id){
        String nom ="";
        ReclamationService re = new ReclamationService();
        Reclamation rec = re.displayById(id);
        int user_id = rec.getUser_id();
        try{
        String req = "Select nom from user where user_id = ?";
        PreparedStatement st = conn.prepareStatement(req);
        st.setInt(1, user_id);
        ResultSet res = st.executeQuery();
        while(res.next())
            {nom = res.getString("nom"); return nom;}
        }catch(Exception e){System.out.println("Erreur d'affichage"+e.getMessage());} 
        return nom;
    }  
    
    public ObservableList<Reclamation> displaystatut(String statut ){
        String x="";
        ObservableList<Reclamation> list=FXCollections.observableArrayList();      
        try{
        String req = "Select * from reclamation where "+statut+" =?";
        PreparedStatement st = conn.prepareStatement(req);
         st.setInt(1, 1);
        
        
        ResultSet res = st.executeQuery();
        

         while(res.next())
            {
                int id = res.getInt("reclamation_id");
                String recName=res.getString("reclamation_nom");
                int user_id=res.getInt("user_id"); 
                String description=res.getString("description"); 
                Date date=res.getDate("date"); 
                Integer evenement_id = res.getInt("evenement_id");
                Integer formation_id = res.getInt("formation_id");
                Integer oeuvrage_id = res.getInt("oeuvrage_id");
                if(evenement_id !=null){x="evenement";}
                if(formation_id !=null){ x="formation";}
                if(oeuvrage_id !=null){ x="oeuvre";}
                
                Reclamation rec = new Reclamation(id, user_id,recName, x, description,date);
                
                list.add(rec);
            }
        } catch(Exception e)
        {
           System.out.println("Erreur d'affichage"+e.getMessage());}
        return list;
    }

    
    public void update(Reclamation os) {
        String req="UPDATE reclamation SET reclamation_nom=?, description=?,date=NOW() WHERE id = "+os.getReclamation_id();
        PreparedStatement ps;
        try { 
            ps =conn.prepareStatement(req); 
            ps.setString(1,os.getReclamation_nom()) ;
            ps.setString(2,os.getDescription());
           
            
            
            ps.executeUpdate() ; 
            
        } catch (SQLException ex) {
           System.out.println("Impossible de modifier la réclamation"+ex.getMessage());
        }
    }
    
}
